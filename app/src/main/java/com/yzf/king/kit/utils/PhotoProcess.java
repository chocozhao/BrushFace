package com.yzf.king.kit.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片的相关处理
 */
public class PhotoProcess {


    // 文件保存的总路径
    public static final String ROOT = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "eptuk"
            + File.separator;
    public static final String SIGNATURE_FILE = "signature";

    public static final String FILE_PATH = Environment
            .getExternalStorageDirectory() + "/eptuk/module/image/";
    // 电子签名保存的路径
    public static final String SIGNATURE = FILE_PATH + "signature/";
    // apk保存的路径
    public static final String APP_APK = ROOT + "apk/";
    // 广告图片保存的路径
    public static final String ADVERTISEMENT = FILE_PATH + "advertisement/";
    /**
     * 小图宽度
     */
    public static final int IMAGE_SMALL_WIDTH = 400;

    /**
     * 小图高度
     */
    public static final int IMAGE_SMALL_HEIGHT = 200;

    /**
     * 创建一个临时图片文件路径，返回文件名
     *
     * @param fullPath
     */
    public static String makeTempPhoto(String fullPath) {

        if (!(Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState()))) {
            return null; // sd卡不能读写
        }

        // 新建一个名字 图片名
        String photoName = System.currentTimeMillis() + ".png";

        File dirFile = new File(fullPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        File spFile = new File(fullPath + ".nomedia");
        if (!spFile.exists()) {
            try {
                spFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return photoName;
    }

    /**
     * delete temp photo
     */
    public static void deleteTempPhoto(String fullPath, String name) {
        File file = new File(fullPath + name);

        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * delete photo
     */
    public static void deletePhoto(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 保存图片到本地,使用自定义名称 fullPath:文件路径（不包括文件名） photoName:保存的文件名
     */
    public static boolean savePhotoToLocal(Bitmap bmp, String fullPath,
                                           String photoName) {

        if (!(Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState()))) {
            return false; // sd卡不能读写
        }

        if (bmp == null) {
            return false;
        }

        File dirFile = new File(fullPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        File pictureFile = new File(fullPath + photoName);
        BufferedOutputStream bos = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(pictureFile));
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos); // 质量压缩90%
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * 从本地读取图片
     */
    public static Bitmap getPhotoFromLocal(String fullPath, String photoName) {
        Bitmap bmp = null;
        File file = new File(fullPath + photoName);

        if (file.exists()) {
            try {
                bmp = BitmapFactory.decodeFile(fullPath + photoName);
            } catch (Exception e) {
                e.printStackTrace();
                bmp = null;
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }

        }

        return bmp;
    }

    /**
     * decode file from path
     */
    public static Bitmap decodeFileFromPath(String filePath) {
        Bitmap bmp = decodeBitmapFromPath(filePath);// 大图，未做质量压缩
        try {
            bmp = qualityCompressImage(bmp);// 质量压缩
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // try {
        // int t = bmp.getRowBytes() * bmp.getHeight() / 1024;
        // Log.v("PhotoProcess--decodefile",
        // "---get---bitmap--size--quality--compress--" + t + "k");
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        return bmp;
    }

    /**
     * decode bitmap form path(big)
     */
    private static Bitmap decodeBitmapFromPath(String filePath) {
        Bitmap bmp;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, opts);
        opts.inSampleSize = ImageZoomUtils.computeSampleSize(opts, 768,
                768 * 1024);
        opts.inJustDecodeBounds = false;
        opts.inInputShareable = true;
        opts.inPurgeable = true;

        try {
            bmp = BitmapFactory.decodeFile(filePath, opts);
        } catch (Exception e) {
            bmp = null;
        } catch (OutOfMemoryError e) {
            bmp = null;
            e.printStackTrace();
        }

        if (bmp != null) {
            bmp = rotatePhoto(bmp, filePath);
        }

        // try {
        // int t = bmp.getRowBytes() * bmp.getHeight() / 1024;
        // Log.v("PhotoProcess--decodefile", "---get---bitmap--size--" + t +
        // "k");
        // Log.v("PhotoProcess--decodefile", "--conpute--sample--size=" +
        // opts.inSampleSize + "---");
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        return bmp;
    }

    /**
     * 图片压缩
     */
    public static Bitmap zoomBitmap(String fromPath, int hight, int width,
                                    String topath) { // JPEG格式
        int new_hight = 800;
        int new_width = new_hight * width / hight;
        Bitmap bm = null;
        try {
            bm = ImageZoomUtils.getBitmapFromFile(fromPath, new_width,
                    new_hight, topath);
        } catch (OutOfMemoryError e) {
            // Toast.makeText(this, "图片过大", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // LogUtils.e(this,TAG, "图片压缩失败");
            e.printStackTrace();
        }
        return bm;
    }

    /**
     * 生成缩略图,用于会话显示
     */
    private static Bitmap getSmallPhoto(String path) {
        Bitmap bitmap;

        try {
            bitmap = decodeBitmapFromPath(path);
        } catch (Exception e) {
            bitmap = null;
        } catch (OutOfMemoryError e) {
            bitmap = null;
        }

        if (bitmap == null) {
            return null;
        }

        Bitmap bmpSmall = bitmap;
        if (bitmap.getWidth() > IMAGE_SMALL_WIDTH
                && bitmap.getHeight() > IMAGE_SMALL_HEIGHT) {
            double rate1 = (double) bitmap.getWidth()
                    / (double) IMAGE_SMALL_WIDTH + 0.1;

            double rate2 = (double) bitmap.getHeight()
                    / (double) IMAGE_SMALL_HEIGHT + 0.1;
            double rate = rate1 > rate2 ? rate1 : rate2;

            int newWidth = (int) ((double) bitmap.getWidth() / rate);
            int newHeight = (int) ((double) bitmap.getHeight() / rate);

            // 获取压缩图后的图像
            try {
                bmpSmall = zoomImage(bitmap, newWidth, newHeight);
            } catch (Exception e) {
                bmpSmall = null;
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }

            if (!bitmap.isRecycled()) {// 内存回收
                bitmap.recycle();
            }
        }

        // try {
        // int t = bmpSmall.getRowBytes() * bmpSmall.getHeight() / 1024;
        // Log.v("PhotoProcess--decodefile", "---get--small--bitmap--size--" + t
        // + "k");
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        return bmpSmall;
    }

    /**
     * 生成图片 context:除type==3外，其他传入null即可 path : 图片本地路径 type :
     * 1:大图，2：公共模块图片(小图)，3：相册展示图片(小图,根据屏幕大小调整)
     */
    public static Bitmap getPhoto(Context context, String path, int type) {
        Bitmap bmp = null;

        switch (type) {
            case 1:// 大图
                bmp = decodeFileFromPath(path);
                break;
            case 2:// 公共模块图片(小图)
                bmp = getSmallPhoto(path, IMAGE_SMALL_HEIGHT, IMAGE_SMALL_HEIGHT);
                break;
            case 3:// 相册展示图片(小图)
                double width = SystemInfo.getInstance(context).getWindowInfo()
                        .getWidth() / 4.0;// 每行显示4张图片
                bmp = getSmallPhoto(path, (int) width, (int) width);
                break;
            default:
                break;
        }

        return bmp;
    }

    /**
     * 生成缩略图 path : 图片路径 width: 缩略图宽度 height:缩略图高度
     */
    private static Bitmap getSmallPhoto(String path, int width, int height) {
        Bitmap bitmap;
        try {
            bitmap = decodeBitmapFromPath(path);
        } catch (Exception e) {
            bitmap = null;
        } catch (OutOfMemoryError e) {
            bitmap = null;
        }
        if (bitmap == null) {
            return null;
        }
        Bitmap bmpSmall = bitmap;
        // 获取宽高相等图片（用于公共模块，相册）
        if (width == height) {
            try {
                bmpSmall = getCutPhoto(bitmap, width);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bmpSmall;
        }
        // 等比缩放图片
        if (bitmap.getWidth() > width && bitmap.getHeight() > height) {
            double rate1 = (double) bitmap.getWidth() / (double) width + 0.1;

            double rate2 = (double) bitmap.getHeight() / (double) height + 0.1;
            double rate = rate1 > rate2 ? rate1 : rate2;

            int newWidth = (int) ((double) bitmap.getWidth() / rate);
            int newHeight = (int) ((double) bitmap.getHeight() / rate);
            // 获取压缩图后的图像
            try {
                bmpSmall = zoomImage(bitmap, newWidth, newHeight);
            } catch (Exception e) {
                bmpSmall = null;
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }

            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }

        // try {
        // int t = bmpSmall.getRowBytes() * bmpSmall.getHeight() / 1024;
        // Log.v("PhotoProcess--decodefile", "---get--small--bitmap--size--" + t
        // + "k");
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        return bmpSmall;
    }

    /**
     * 截取图片 length:截取长度
     */
    private static Bitmap getCutPhoto(Bitmap bitmap, int length) {
        if (bitmap == null) {
            return null;
        }

        Bitmap bmp = null;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        try {
            // 截取等高的图片
            if (width > height) {
                bmp = Bitmap.createBitmap(bitmap, (width - height) / 2, 0,
                        height, height);
            } else if (width < height) {
                bmp = Bitmap.createBitmap(bitmap, 0, 0, width, width);
            }

        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }

        Bitmap newBmp = null;// 缩放后图片

        if (bmp != null)// 截图成功
        {
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }

            try {
                newBmp = zoomImage(bmp, length, length);// 缩放图片
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }

            if (newBmp != null) {
                if (!bmp.isRecycled()) {
                    bmp.recycle();
                }

            } else {
                newBmp = bmp;
            }
        } else// 截图失败或没有截图（width == height）
        {
            try {
                newBmp = zoomImage(bitmap, length, length);// 缩放图片
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }

            if (newBmp != null) {
                if (!bitmap.isRecycled()) {
                    bitmap.recycle();
                }

            } else {
                newBmp = bitmap;
            }
        }

        return newBmp;
    }

    /**
     * 生成会话用户头像 80 * 80
     */
    private static Bitmap getHeadPhoto(String path) {

        Bitmap bitmap;
        try {
            bitmap = decodeBitmapFromPath(path);
        } catch (Exception e) {
            bitmap = null;
        } catch (OutOfMemoryError e) {
            bitmap = null;
        }

        if (bitmap == null) {
            return null;
        }

        Bitmap bmpSmall = bitmap;
        if (bitmap.getWidth() > 80 || bitmap.getHeight() > 80) {
            double rate1 = (double) bitmap.getWidth() / (double) 80 + 0.1;

            double rate2 = (double) bitmap.getHeight() / (double) 80 + 0.1;
            double rate = rate1 > rate2 ? rate1 : rate2;

            int newWidth = (int) ((double) bitmap.getWidth() / rate);
            int newHeight = (int) ((double) bitmap.getHeight() / rate);

            // 获取压缩图后的图像
            try {
                bmpSmall = zoomImage(bitmap, newWidth, newHeight);
            } catch (Exception e) {
                bmpSmall = null;
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }

            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }

        return bmpSmall;
    }

    /**
     * base64编码处理: file to base64
     *
     * @param srcUrl 文件路径
     */
    public static String fileToBase64String(String srcUrl) {
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        String base64Str = null;
        try {
            fis = new FileInputStream(srcUrl);
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1892];
            int count;

            while ((count = fis.read(buffer)) > -1) {
                baos.write(buffer, 0, count);
            }

            base64Str = new String(Base64.encode(baos.toByteArray(),
                    Base64.DEFAULT));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return base64Str;
    }

    /**
     * bitmap to base64
     */
    public static String bitmapToBase64Strng(Bitmap bmp) {
        String str = null;
        ByteArrayOutputStream baos = null;

        if (bmp != null) {
            try {
                baos = new ByteArrayOutputStream();
                int rate = 100;
                if ((bmp.getRowBytes()) * (bmp.getHeight()) / 1024 > 500) {// 大于500k质量压缩为95%
                    rate = 90;
                }
                bmp.compress(Bitmap.CompressFormat.JPEG, rate, baos);
                byte[] bmpBytes = baos.toByteArray();
                str = Base64.encodeToString(bmpBytes, Base64.DEFAULT);
                Log.e("PhotoProcess", "---bmpBytes--" + bmpBytes.length / 1024 + "k");

                baos.flush();
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
                if (baos != null) {

                    try {
                        baos.flush();
                        baos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                str = null;
            }
        }

        return str;
    }

    /**
     * base64 to bitmap
     */
    public static Bitmap base64StringToBitmap(String base64Str) {
        Bitmap bitmap = null;
        if (base64Str == null || base64Str.equals("")) {
            return null;
        }

        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(base64Str, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * 图片圆角处理
     */
    private static Bitmap getRoundBitmap(Bitmap bmp) {
        Bitmap bgBitmap;
        bgBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
                Config.ARGB_8888); // 创建新位图
        Canvas canvas = new Canvas(bgBitmap); // 创建的位图作为画板
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = 20; // 设置圆角半径
        paint.setAntiAlias(true);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// 绘制圆角矩形
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));// 设置图像叠加模式
        canvas.drawBitmap(bmp, rect, rect, paint); // 绘制图像
        return bgBitmap;
    }

    /**
     * rotate photo
     */
    private static Bitmap rotatePhoto(Bitmap bmp, String path) {
        int degree = getPhotoDegree(path);
        if (degree == 0) {
            return bmp;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap resizedBmp = null;
        try {
            resizedBmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
                    bmp.getHeight(), matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }

        if (resizedBmp == null) {
            return bmp;
        } else {
            if (!bmp.isRecycled()) {
                bmp.recycle();
            }

            return resizedBmp;
        }
    }

    /**
     * get photo degree
     */
    private static int getPhotoDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return degree;
    }

    /**
     * @param bitMap   源图片资源
     * @param filePath 图片输出路径
     * @return void
     */
    public static void imageZoom(Bitmap bitMap, String filePath) {

        // 原图宽高比输出参数还小则图片大小保持不变
        if (bitMap.getWidth() > IMAGE_SMALL_WIDTH
                && bitMap.getWidth() > IMAGE_SMALL_HEIGHT) {

            // 等比缩放，为等比缩放计算输出的图片宽度及高度
            double rate1 = (double) bitMap.getWidth()
                    / (double) IMAGE_SMALL_WIDTH + 0.1;

            double rate2 = (double) bitMap.getHeight()
                    / (double) IMAGE_SMALL_HEIGHT + 0.1;

            // 根据缩放比率大的进行缩放控制
            double rate = rate1 > rate2 ? rate1 : rate2;

            int newWidth = (int) ((double) bitMap.getWidth() / rate);
            int newHeight = (int) ((double) bitMap.getHeight() / rate);

            // 获取压缩图后的图像
            bitMap = zoomImage(bitMap, newWidth, newHeight);
        }

        File file = new File(filePath);

        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bitMap.compress(Bitmap.CompressFormat.JPEG, 90, bos);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * 图片缩放
     *
     * @param bgimage   源图片资源
     * @param newWidth  缩放后宽度
     * @param newHeight 缩放后高度
     * @return Bitmap
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {

        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();

        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();

        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);

        return bitmap;

    }

    // /**
    // * 获取会话中使用的所有用户头像
    // * @param usertype 1个人，2群组
    // * @param context
    // * @param userId 发送者Id
    // * @param userInfo 个人接收者信息
    // * @param groupInfo 群接收群组信息
    // * @return 头像Bitmap map
    // */
    // public static Map<String, Bitmap> getHeadImage(int userType, Context
    // context, String userId, UserInfo userInfo, GroupInfo groupInfo, boolean
    // threadStop){
    // Map<String, Bitmap> headImages = new HashMap<String, Bitmap>();
    // FileCache fileCache = new FileCache(context, 6);
    // File file = null;
    //
    // UserInfo userInfoFrom = null; //发送者信息
    // String headUrlFrom = "";
    // Bitmap headBmpFrom = null;
    //
    // try {
    // userInfoFrom = DbUtil.getDatabase(context,
    // userId).getUserInfoById(userId);
    // } catch (Exception e) {
    // e.printStackTrace();
    // userInfoFrom = null;
    // }
    //
    // if(userInfoFrom != null){
    // headUrlFrom = userInfoFrom.getPhotoUrl();
    //
    // if(!headUrlFrom.equals("")){
    // file = fileCache.getFile(StaticValue.DOWN_PATH + headUrlFrom);
    // }
    //
    // if(file != null && file.exists()){
    // Bitmap bmp = null;
    // try {
    // bmp = getHeadPhoto(file.getPath());
    // headBmpFrom = ImageUtil.toRoundCorner(bmp, 5);
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // if(headBmpFrom != null && !headBmpFrom.isRecycled()){
    // headBmpFrom.recycle();
    // headBmpFrom = null;
    // }
    // }
    //
    // if(bmp != null && !bmp.isRecycled()){
    // bmp.recycle();
    // bmp = null;
    // }
    // file = null;
    // }
    //
    // if(headBmpFrom != null){
    //
    // try {
    // headImages.put(userId, headBmpFrom);
    // } catch (Exception e) {
    // e.printStackTrace();
    // if(headBmpFrom != null && !headBmpFrom.isRecycled()){
    // headBmpFrom.recycle();
    // headBmpFrom = null;
    // }
    // }
    // headBmpFrom = null;
    // }
    // }
    //
    // file = null;
    // if(userType == 1){//个人
    //
    // //个人接收者信息
    // if(userInfo == null){
    // return headImages;
    // }
    // String headUrlTo = "";
    // Bitmap headBmpTo = null;
    // headUrlTo = userInfo.getPhotoUrl();
    //
    // if(!headUrlTo.equals("")){
    // file = fileCache.getFile(StaticValue.DOWN_PATH + headUrlTo);
    // }
    //
    // if(file != null && file.exists()){
    // Bitmap bmp = null;
    // try {
    // bmp = getHeadPhoto(file.getPath());
    // headBmpTo = ImageUtil.toRoundCorner(bmp, 5);
    // } catch (Exception e) {
    // e.printStackTrace();
    // if(headBmpTo != null && !headBmpTo.isRecycled()){
    // headBmpTo.recycle();
    // headBmpTo = null;
    // }
    // }
    //
    // if(bmp != null && !bmp.isRecycled()){
    // bmp.recycle();
    // bmp = null;
    // }
    // file = null;
    // }
    //
    // if(headBmpTo != null){
    //
    // try {
    // headImages.put(userInfo.getId(), headBmpTo);
    // } catch (Exception e) {
    // e.printStackTrace();
    // if(headBmpTo != null && !headBmpTo.isRecycled()){
    // headBmpTo.recycle();
    // headBmpTo = null;
    // }
    // }
    // headBmpTo = null;
    // }
    // }
    // else if(userType == 2){//群组
    // //群组接收者信息
    // if(groupInfo == null){
    // return headImages;
    // }
    //
    // String groupId = groupInfo.getId();
    // List<GroupMemberInfo> memberList = null;
    // try {
    // memberList = DbUtil.getDatabase(context,
    // userId).getMemberIdsByGroupId(groupId);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    //
    // if(memberList == null || memberList.size() < 1){
    // return headImages;
    // }
    //
    // GroupMemberInfo memberInfo = null;
    // UserInfo gUserInfo = null;
    // String headUrlTo = "";
    // Bitmap headBmpTo = null;
    //
    // for(int i = 0; i < memberList.size(); i++){
    // if(threadStop){ //退出时，终止循环
    // return null;
    // }
    //
    // try {
    // memberInfo = memberList.get(i);
    // } catch (Exception e) {
    // }
    //
    // if(memberInfo != null){
    //
    // try {
    // if(memberInfo.getMemberId().equals(userId)){//发送者跳过
    // continue;
    // }
    //
    // gUserInfo = DbUtil.getDatabase(context,
    // userId).getUserInfoById(memberInfo.getMemberId());
    // } catch (Exception e) {
    // e.printStackTrace();
    // gUserInfo = null;
    // continue;
    // }
    //
    // memberInfo = null;
    //
    // if(gUserInfo != null){
    // headUrlTo = gUserInfo.getPhotoUrl();
    // if(!headUrlTo.equals("")){
    // file = fileCache.getFile(StaticValue.DOWN_PATH + headUrlTo);
    // }
    //
    // if(file != null && file.exists()){
    // Bitmap bmp = null;
    // try {
    // bmp = getHeadPhoto(file.getPath());
    // headBmpTo = ImageUtil.toRoundCorner(bmp, 5);
    // } catch (Exception e) {
    // e.printStackTrace();
    // if(headBmpTo != null && !headBmpTo.isRecycled()){
    // headBmpTo.recycle();
    // headBmpTo = null;
    // }
    // file = null;
    // continue;
    // }
    //
    // if(bmp != null && !bmp.isRecycled()){
    // bmp.recycle();
    // bmp = null;
    // }
    // file = null;
    // }
    //
    // if(headBmpTo != null){
    //
    // try {
    // headImages.put(gUserInfo.getId(), headBmpTo);
    // } catch (Exception e) {
    // e.printStackTrace();
    // if(headBmpTo != null && !headBmpTo.isRecycled()){
    // headBmpTo.recycle();
    // headBmpTo = null;
    // }
    // }
    // headBmpTo = null;
    // }
    // gUserInfo = null;
    // }
    // }
    // }
    // }
    //
    // return headImages;
    // }

    /**
     * calculate insamplesize
     */
    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    /**
     * 图片质量压缩
     */
    private static Bitmap qualityCompressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;

        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10;
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片

        if (!image.isRecycled()) {
            image.recycle();
        }

        return bitmap;
    }
}
