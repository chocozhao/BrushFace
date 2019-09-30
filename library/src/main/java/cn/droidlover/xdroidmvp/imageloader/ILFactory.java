package cn.droidlover.xdroidmvp.imageloader;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/11/28 10:32
 * Modified By：
 * Fixtime：2016/11/28 10:32
 * FixDescription：
 * @version
 *
 */
public class ILFactory {

    private static ILoader loader;


    public static ILoader getLoader() {
        if (loader == null) {
            synchronized (ILFactory.class) {
                if (loader == null) {
                    loader = new GlideLoader();
                }
            }
        }
        return loader;
    }


}
