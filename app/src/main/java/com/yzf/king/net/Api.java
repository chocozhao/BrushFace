package com.yzf.king.net;

import com.yzf.king.kit.AppConfig;

import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：Api
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/10 18:02
 * Modified By：
 * Fixtime：2017/5/10 18:02
 * FixDescription：
 */
public class Api {

    private static APPService APPService;

    public static APPService getAPPService() {
        if (APPService == null) {
            synchronized (Api.class) {
                if (APPService == null) {
                    APPService = XApi.getInstance().getRetrofit(AppConfig.BASE_URL, true).create(APPService.class);
                }
            }
        }
        return APPService;
    }


}
