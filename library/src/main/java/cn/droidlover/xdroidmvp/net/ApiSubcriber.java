package cn.droidlover.xdroidmvp.net;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import cn.droidlover.xdroidmvp.log.Logger;
import rx.Subscriber;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/26 10:32
 * Modified By：
 * Fixtime：2016/12/26 10:32
 * FixDescription：
 * @version
 *
 */

public abstract class ApiSubcriber<T extends IModel> extends Subscriber<T> {
    @Override
    public void onError(Throwable e) {
        NetError error;
        if (e != null) {
            if (!(e instanceof NetError)) {
                if (e instanceof UnknownHostException) {
                    error = new NetError(e, NetError.NoConnectError);
                } else if (e instanceof JSONException || e instanceof JsonParseException) {
                    error = new NetError(e, NetError.ParseError);
                } else if (e instanceof SocketTimeoutException) {
                    error = new NetError(e, NetError.TimeOutError);
                } else if (e instanceof ConnectException) {
                    error = new NetError(e, NetError.ConnectError);
                } else {
                    error = new NetError(e, NetError.OtherError);
                }
            } else {
                error = (NetError) e;
            }

            if (useCommonErrorHandler()
                    && XApi.getCommonProvider() != null) {
                if (XApi.getCommonProvider().handleError(error)) {        //使用通用异常处理
                    return;
                }
            }
            onFail(error);
            Logger.e(e.toString());
        }

    }

    protected abstract void onFail(NetError error);

    @Override
    public void onCompleted() {

    }

    protected boolean useCommonErrorHandler() {
        return true;
    }


}
