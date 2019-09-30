package cn.droidlover.xdroidmvp.event;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

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

public class RxBusImpl implements IBus {

    private final Subject<IEvent, IEvent> bus = new SerializedSubject<>(PublishSubject.<IEvent>create());

    @Override
    public void register(Object object) {

    }

    @Override
    public void unregister(Object object) {

    }

    @Override
    public void post(IEvent event) {
        bus.onNext(event);
    }

    @Override
    public void postSticky(IEvent event) {

    }

    public <T extends IEvent> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }
}
