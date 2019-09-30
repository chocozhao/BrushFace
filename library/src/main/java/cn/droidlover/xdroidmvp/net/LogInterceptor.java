package cn.droidlover.xdroidmvp.net;


import java.io.IOException;

import cn.droidlover.xdroidmvp.log.Logger;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

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

public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logRequest(request);
        Response response = chain.proceed(request);
        return logResponse(response);
    }


    private void logRequest(Request request) {
        try {
            String url = request.url().toString();
            Headers headers = request.headers();

            Logger.i("method : " + request.method()+"   url : " + url+"  param:"+bodyToString(request));
            if (headers != null && headers.size() > 0) {
                Logger.v("headers : " + headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                    if (isText(mediaType)) {
                        Logger.v( "params : " + bodyToString(request));
                    } else {
                        Logger.v( "params : " + " maybe [file part] , too large too print , ignored!");
                    }
                }
            }
        } catch (Exception e) {
            Logger.e(e.toString());
        }
    }

    private Response logResponse(Response response) {
        try {
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            ResponseBody body = clone.body();
            if (body != null) {
                MediaType mediaType = body.contentType();
                if (mediaType != null) {
                    if (isText(mediaType)) {
                        String resp = body.string();
                        Logger.json(resp);

                        body = ResponseBody.create(mediaType, resp);
                        return response.newBuilder().body(body).build();
                    } else {
                        Logger.i("data : " + " maybe [file part] , too large too print , ignored!");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    private boolean isText(MediaType mediaType) {
        return mediaType != null && ("text".equals(mediaType.subtype()) || "json".equals(mediaType.subtype()) || "xml".equals(mediaType.subtype()) || "html".equals(mediaType.subtype()) || "webviewhtml".equals(mediaType.subtype()) || "x-www-form-urlencoded".equals(mediaType.subtype()));

    }

    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            RequestBody requestBody=copy.body();
            if(requestBody!=null)
            {
                copy.body().writeTo(buffer);
                return buffer.readUtf8();
            }else {
                return null;
            }

        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }
}
