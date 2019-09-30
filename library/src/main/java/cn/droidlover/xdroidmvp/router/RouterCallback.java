package cn.droidlover.xdroidmvp.router;

import android.app.Activity;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/11/29 10:32
 * Modified By：
 * Fixtime：2016/11/29 10:32
 * FixDescription：
 * @version
 *
 */

public interface RouterCallback {

    void onBefore(Activity from, Class<?> to);

    void onNext(Activity from, Class<?> to);

    void onError(Activity from, Class<?> to, Throwable throwable);

}
