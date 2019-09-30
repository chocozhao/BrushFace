package cn.droidlover.xdroidmvp.cache;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/11/27 10:32
 * Modified By：
 * Fixtime：2016/11/27 10:32
 * FixDescription：
 * @version
 *
 */

public interface ICache {
    void put(String key, Object value,long expireMills );

    Object get(String key);

    void remove(String key);

    boolean contains(String key);

    void clear();

}
