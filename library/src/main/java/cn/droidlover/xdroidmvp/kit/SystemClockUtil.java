package cn.droidlover.xdroidmvp.kit;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @Description: 高并发场景下System.currentTimeMillis()的性能问题的优化
 * @UpdateUser: chuck
 * @UpdateDate: 2018/11/30 22:23
 * @Version: 1.0
 * @UpdateRemark:
 */
public class SystemClockUtil {
    private static final String THREAD_NAME = "system.clock";
    private static final SystemClockUtil MILLIS_CLOCK = new SystemClockUtil(1);
    private final long precision;
    private final AtomicLong now;

    private SystemClockUtil(long precision) {
        this.precision = precision;
        now = new AtomicLong(System.currentTimeMillis());
        scheduleClockUpdating();
    }

    public static SystemClockUtil millisClock() {
        return MILLIS_CLOCK;
    }

    private void scheduleClockUpdating() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "System Clock");
                thread.setDaemon(true);
                return thread;
            }
        });
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                now.set(System.currentTimeMillis());
            }
        }, precision, precision, TimeUnit.MILLISECONDS);
    }

    public long now() {
        return now.get();
    }

    public static Date getDate() {
        return new Date(SystemClockUtil.millisClock().now());
    }

    public static void main(String[] args) {
        System.out.println("SystemCurrentTimeMillis Time:" + Kits.Date.getDate() + "毫秒");
        int times = 10000;
        long start = System.currentTimeMillis();
        for (long i = 0; i < times; i++) {
            SystemClockUtil.getDate();
        }
        long end = System.currentTimeMillis();
        System.out.println("SystemClock Time:" + (end - start) + "毫秒");
        long start2 = System.currentTimeMillis();
        for (long i = 0; i < times; i++) {
            Kits.Date.getDate();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("SystemCurrentTimeMillis Time:" + (end2 - start2) + "毫秒");
        AtomicLong atomicLong = new AtomicLong(SystemClockUtil.millisClock().now());
        atomicLong.longValue();

    }
}
