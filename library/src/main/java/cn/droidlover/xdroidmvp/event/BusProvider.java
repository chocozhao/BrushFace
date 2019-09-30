package cn.droidlover.xdroidmvp.event;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/22 10:32
 * Modified By：
 * Fixtime：2016/12/22 10:32
 * FixDescription：
 * @version
 *
 */

public class BusProvider {

    private static RxBusImpl bus;

    public static RxBusImpl getBus() {
        if (bus == null) {
            synchronized (BusProvider.class) {
                if (bus == null) {
                    bus = new RxBusImpl();
                }
            }
        }
        return bus;
    }

}
