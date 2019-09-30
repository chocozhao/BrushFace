package cn.droidlover.xdroidmvp.event;

/**
 * ProjectName：paypos
 * Description：
 * Author：JensenWei
 * QQ: 515162675
 * Createtime：2016/7/13 16:34
 * Modified By：
 * Fixtime：2016/7/13 16:34
 * FixDescription：
 */
public class IEvent implements IBus.IEvent {

    String id;

    Object object;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setMessage(String id,Object object)
    {
        setId(id);
        setObject(object);
    }
}