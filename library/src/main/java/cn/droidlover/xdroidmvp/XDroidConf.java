package cn.droidlover.xdroidmvp;

import cn.droidlover.xdroidmvp.imageloader.ILoader;
import cn.droidlover.xdroidmvp.router.Router;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/4 10:32
 * Modified By：
 * Fixtime：2016/12/4 10:32
 * FixDescription：
 * @version
 *
 */

public class XDroidConf {
    // #log

    // #cache
    public static final String CACHE_SP_NAME = "config";
    public static final String CACHE_DISK_DIR = "cache";

    // #router
    public static final int ROUTER_ANIM_ENTER = Router.RES_NONE;
    public static final int ROUTER_ANIM_EXIT = Router.RES_NONE;

    

    // #dev model
    public static final boolean DEV = true;

    //#db
    public static final String DB_NAME = "Xdb";
}
