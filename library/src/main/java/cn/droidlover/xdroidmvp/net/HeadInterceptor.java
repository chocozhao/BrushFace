package cn.droidlover.xdroidmvp.net;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/1/7 10:32
 * Modified By：
 * Fixtime：2017/1/7 10:32
 * FixDescription：
 * @version
 *
 */

public class HeadInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .header("User-Agent", "Android");

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

}
