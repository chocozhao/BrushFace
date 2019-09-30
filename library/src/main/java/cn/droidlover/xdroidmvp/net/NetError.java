package cn.droidlover.xdroidmvp.net;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/24 10:32
 * Modified By：
 * Fixtime：2016/12/24 10:32
 * FixDescription：
 * @version
 *
 */

public class NetError extends Exception {
    private Throwable exception;
    private int type = NoConnectError;

    public static final int ParseError = 0;   //数据解析异常
    public static final int NoConnectError = 1;   //无连接异常
    public static final int AuthError = 2;   //用户验证异常
    public static final int NoDataError = 3;   //无数据返回异常
    public static final int BusinessError = 4;   //业务异常
    public static final int OtherError = 5;   //其他异常
    public static final int ConnectError = 6;   //连接后台失败
    public static final int TimeOutError = 7;   //其他异常

    public NetError(Throwable exception, int type) {
        this.exception = exception;
        this.type = type;
    }

    public NetError(String detailMessage, int type) {
        super(detailMessage);
        this.type = type;
    }

    @Override
    public String getMessage() {
        if (exception != null) return exception.getMessage();
        return super.getMessage();
    }

    public int getType() {
        return type;
    }
}
