package cn.droidlover.xdroidmvp.base;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/1 10:32
 * Modified By：
 * Fixtime：2016/12/1 10:32
 * FixDescription：
 * @version
 *
 */

public abstract class ListItemCallback<T> {

    public void onItemClick(int position, T model, int tag) {}

    public void onItemLongClick(int position, T model, int tag) {}
}
