package cn.droidlover.xdroidmvp.mvp;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/29 10:32
 * Modified By：
 * Fixtime：2016/12/29 10:32
 * FixDescription：
 * @version
 *
 */

public interface IPresent<V> {
    void attachV(V view);

    void detachV();
}
