package cn.droidlover.xdroidmvp.net;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

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

public interface RequestHandler {
    Request onBeforeRequest(Request request, Interceptor.Chain chain);

    Response onAfterRequest(Response response, String result, Interceptor.Chain chain);
}
