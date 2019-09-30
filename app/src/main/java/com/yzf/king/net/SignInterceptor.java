package com.yzf.king.net;

import com.yzf.king.kit.AppConfig;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.Des3;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.droidlover.xdroidmvp.kit.Kits.Date;
import cn.droidlover.xdroidmvp.log.Logger;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Part;
import okhttp3.Request;
import okhttp3.Response;

public class SignInterceptor implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.method().equals("GET")) {
            request = addGetParams(request);
        } else if (request.method().equals("POST")) {
            request = addPostParams(request);
        }
        return chain.proceed(request);
    }

    private static Request addGetParams(Request request) {
        HttpUrl build = request.url().newBuilder().build();
        HttpUrl.Builder builder = build.newBuilder();
        Collection queryParameterNames = build.queryParameterNames();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(queryParameterNames);
        Collections.sort(arrayList);
        JSONObject object = new JSONObject();
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                object.put((String) arrayList.get(i), (build.queryParameterValues((String) arrayList.get(i)) == null || build.queryParameterValues((String) arrayList.get(i)).size() <= 0) ? "" : (String) build.queryParameterValues((String) arrayList.get(i)).get(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Logger.i("baseStr:" + object.toString());
        HttpUrl.Builder newBuilder;
        newBuilder = new HttpUrl.Builder()
                .scheme(build.scheme())
                .host(build.host())
                .port(build.port())
                .addEncodedPathSegments(build.url().getPath());
        HttpUrl newUrl = newBuilder.build();


        try {
            String data = Des3.encode(object.toString());
            Logger.i("data:" + data);
            return request.newBuilder().url(newUrl.newBuilder().addQueryParameter("osType", "Android").addQueryParameter("oemBranchCode", AppConfig.getTopbranchno()).addQueryParameter("token", AppUser.getInstance().getToken()).addQueryParameter("data", data).build()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return request.newBuilder().url(newUrl.newBuilder().addQueryParameter("osType", "Android").addQueryParameter("oemBranchCode", AppConfig.getTopbranchno()).addQueryParameter("token", AppUser.getInstance().getToken()).addQueryParameter("data", null).build()).build();
        }
    }

    private Request addPostParams(Request request) throws UnsupportedEncodingException {
        String str;
        if (request.body() instanceof FormBody) {
            int i;
            str = Date.getyyyyMMddHHmmss();
            Builder builder = new Builder();
            FormBody formBody = (FormBody) request.body();
            /*for (int i2 = 0; i2 < formBody.size(); i2++) {
                builder.addEncoded(formBody.encodedName(i2), formBody.encodedValue(i2));
            }*/
            Map hashMap = new HashMap();
            List arrayList = new ArrayList();
            for (i = 0; i < formBody.size(); i++) {
                arrayList.add(formBody.encodedName(i));
                hashMap.put(formBody.encodedName(i), URLDecoder.decode(formBody.encodedValue(i), "UTF-8"));
            }
            Collections.sort(arrayList);
            JSONObject object = new JSONObject();
            for (i = 0; i < arrayList.size(); i++) {
                try {
                    object.put((String) arrayList.get(i), ((String) hashMap.get(arrayList.get(i))));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            builder.addEncoded("osType", "Android").addEncoded("oemBranchCode", AppConfig.getTopbranchno()).addEncoded("token", AppUser.getInstance().getToken()).build();

            Logger.i("baseStr:" + object.toString());
            try {
                return request.newBuilder().post(builder.addEncoded("data", Des3.encode(object.toString())).build()).build();
            } catch (Exception e) {
                return request.newBuilder().post(builder.addEncoded("data", null).build()).build();

            }
        } else if (request.body() instanceof MultipartBody) {
            str = Date.getyyyyMMddHHmmss();
            MultipartBody multipartBody = (MultipartBody) request.body();
            MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
            type.addFormDataPart("token", AppUser.getInstance().getToken());
            type.addFormDataPart("osType", "android");
            type.addFormDataPart("oemBranchCode", AppConfig.getTopbranchno());

            HttpUrl build = request.url().newBuilder().build();
            HttpUrl.Builder builder = build.newBuilder();
            Collection queryParameterNames = build.queryParameterNames();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(queryParameterNames);
            Collections.sort(arrayList);
            JSONObject object = new JSONObject();
            for (int i = 0; i < arrayList.size(); i++) {
                try {
                    object.put((String) arrayList.get(i), (build.queryParameterValues((String) arrayList.get(i)) == null || build.queryParameterValues((String) arrayList.get(i)).size() <= 0) ? "" : (String) build.queryParameterValues((String) arrayList.get(i)).get(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Logger.i("baseStr:" + object.toString());
            try {
                String data = Des3.encode(object.toString());
                type.addFormDataPart("data", data);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Part addPart : multipartBody.parts()) {
                type.addPart(addPart);
            }
            return request.newBuilder().post(type.build()).build();
        } else {
            return request;
        }
    }
}