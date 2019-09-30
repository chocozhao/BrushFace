package com.yzf.king.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yzf.king.R;
import com.yzf.king.kit.AppConfig;

import cn.droidlover.xdroidmvp.log.Logger;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    //35fae06f2e823a1a6e2c528cb1c9e1af
    //94934e9cd2ab30b437b9bde1abc74eca
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxshare_back);
        api = WXAPIFactory.createWXAPI(this, AppConfig.APP_ID, false);
        api.handleIntent(getIntent(), this);
        Logger.i("onCreate");
    }

    @Override
    public void onReq(BaseReq arg0) {
        Logger.i("onReq");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onResp(BaseResp resp) {
        Logger.i("onResp");
        int result;

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.errcode_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                break;
            default:
                result = R.string.errcode_unknown;
                break;
        }

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        finish();
    }
}
