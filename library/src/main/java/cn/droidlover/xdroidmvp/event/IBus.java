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

public interface IBus {

    void register(Object object);
    void unregister(Object object);
    void post(IEvent event);
    void postSticky(IEvent event);


    interface IEvent {

        String getId();

        void setId(String id);

        Object getObject();

        void setObject(Object object);

        void setMessage(String id,Object object);
    }

}
