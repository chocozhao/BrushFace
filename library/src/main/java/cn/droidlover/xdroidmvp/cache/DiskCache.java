package cn.droidlover.xdroidmvp.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.droidlover.xdroidmvp.XDroidConf;
import cn.droidlover.xdroidmvp.kit.Codec;
import cn.droidlover.xdroidmvp.kit.Kits;

/**
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/11/28 10:32
 * Modified By：
 * Fixtime：2016/11/28 10:32
 * FixDescription：
 */

public class DiskCache implements ICache {
    private DiskLruCache cache;

    static String TAG_CACHE = "=====createTime{createTime_v}expireMills{expireMills_v}";
    static String REGEX = "=====createTime\\{(\\d{1,})\\}expireMills\\{(\\d{1,})\\}";
    private Pattern compile;

    public static final long NO_CACHE = -1L;

    private static DiskCache instance;

    private DiskCache(Context context) {
        compile = Pattern.compile(REGEX);
        try {
            File cacheDir = getDiskCacheDir(context, getCacheDir());
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            cache = DiskLruCache.open(cacheDir, Kits.Package.getVersionCode(context), 1, 10 * 1024 * 1024);        //10M
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DiskCache getInstance(Context context) {
        if (instance == null) {
            synchronized (DiskCache.class) {
                if (instance == null) {
                    instance = new DiskCache(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public void put(String key, String value) {
        put(key, value, NO_CACHE);
    }

    public void put(String key, String value, long expireMills) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) return;

        String name = getMd5Key(key);
        try {
            if (!TextUtils.isEmpty(get(name))) {     //如果存在，先删除
                cache.remove(name);
                File file = new File(value);
                if (file.exists()) {
                    file.delete();
                }
            }

            DiskLruCache.Editor editor = cache.edit(name);
            StringBuilder content = new StringBuilder(value);
            content.append(TAG_CACHE.replace("createTime_v", "" + Calendar.getInstance().getTimeInMillis()).replace("expireMills_v", "" + expireMills));
            editor.set(0, content.toString());
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(String key, Object value, long expireMills) {
        if (value instanceof Bitmap) {
            Bitmap bitmap = (Bitmap) value;
            String string = null;
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            byte[] bytes = bStream.toByteArray();
            string = Base64.encodeToString(bytes, Base64.DEFAULT);
            if (expireMills > 0) {
                put(key, string != null ? string : null, expireMills);
            } else {
                put(key, string != null ? string : null, NO_CACHE);
            }
        } else {
            if (expireMills > 0) {
                put(key, value != null ? value.toString() : null, expireMills);
            } else {
                put(key, value != null ? value.toString() : null, NO_CACHE);
            }
        }

    }


    public String get(String key) {
        try {
            String md5Key = getMd5Key(key);
            DiskLruCache.Snapshot snapshot = cache.get(md5Key);
            if (snapshot != null) {
                String content = snapshot.getString(0);

                if (!TextUtils.isEmpty(content)) {
                    Matcher matcher = compile.matcher(content);
                    long createTime = 0;
                    long expireMills = 0;
                    while (matcher.find()) {
                        createTime = Long.parseLong(matcher.group(1));
                        expireMills = Long.parseLong(matcher.group(2));
                    }
                    int index = content.indexOf("=====createTime");

                    if ((createTime + expireMills > Calendar.getInstance().getTimeInMillis())
                            || expireMills == NO_CACHE) {
                        return content.substring(0, index);
                    } else {
                        //过期
                        cache.remove(md5Key);       //删除
                        File file = new File(content.substring(0, index));
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(String key) {
        try {
            cache.remove(getMd5Key(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean contains(String key) {
        try {
            DiskLruCache.Snapshot snapshot = cache.get(getMd5Key(key));
            return snapshot != null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void clear() {
        try {
            cache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMd5Key(String key) {
        return Codec.MD5.getMessageDigest(key.getBytes());
    }

    private static File getDiskCacheDir(Context context, String dirName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + dirName);
    }

    private String getCacheDir() {
        return XDroidConf.CACHE_DISK_DIR;
    }


}
