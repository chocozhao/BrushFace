package cn.droidlover.xdroidmvp.imageloader;

import android.graphics.Bitmap;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/21 10:32
 * Modified By：
 * Fixtime：2016/12/21 10:32
 * FixDescription：
 * @version
 *
 */

public abstract class LoadCallback {
    void onLoadFailed(Throwable e) {}

    public abstract void onLoadReady(Bitmap bitmap);

    void onLoadCanceled() {}
}
