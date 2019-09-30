package com.yzf.king;


import android.app.Application;
import android.content.Context;
import android.support.annotation.Keep;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.yzf.king.kit.AppConfig;

/**
 * ClaseName：SophixStubApplication
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/9/16 18:55
 * Modified By：
 * Fixtime：2019/9/16 18:55
 * FixDescription：阿里热修复 参考文档：https://help.aliyun.com/document_detail/57064.html?spm=a2c4g.11186623.6.552.70385482VaKlwR
 **/

/**
 * Sophix入口类，专门用于初始化Sophix，不应包含任何业务逻辑。
 * 此类必须继承自SophixApplication，onCreate方法不需要实现。
 * 此类不应与项目中的其他类有任何互相调用的逻辑，必须完全做到隔离。
 * AndroidManifest中设置application为此类，而SophixEntry中设为原先Application类。
 * 注意原先Application里不需要再重复初始化Sophix，并且需要避免混淆原先Application类。
 * 如有其它自定义改造，请咨询官方后妥善处理。
 */

/**
 * mode: 补丁模式, 0:正常请求模式 1:扫码模式 2:本地补丁模式
 * code: 补丁加载状态码, 详情查看PatchStatusCode类说明
 * info: 补丁加载详细说明, 详情查看PatchStatusCode类说明
 * handlePatchVersion: 当前处理的补丁版本号, 0:无 -1:本地补丁 其它:后台补丁
 * <p>
 * <p>
 * code 0: 准备开始拉取最新补丁
 * code: 1 补丁加载成功
 * code: 2 sdk初始化失败 加了setUnsupportedModel
 * code: 6 服务端没有最新可用的补丁
 * code: 8 补丁下载失败
 * code: 9 补丁下载成功
 * code: 11 RSASECRET错误，官网中的密钥是否正确请检查
 * code: 12 当前应用已经存在一个旧补丁, 应用重启尝试加载新补丁
 * code: 13 补丁加载失败, 导致的原因很多种, 比如UnsatisfiedLinkError等异常, 此时应该严格检查logcat异常日志
 * code: 14 找不到任何可加载的补丁文件 一种情况是确实没有，一种情况是需要考虑补丁文件可能放在sdcard等外部存储器中，sdk22以上版本机器需要6.0权限。
 * code: 16 APPSECRET错误，官网中的密钥是否正确请检查
 * code: 18 一键清除补丁
 * code: 19 连续两次queryAndLoadNewPatch()方法调用不能短于3s
 * errorCode:-101 异常
 * <p>
 */
public class SophixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";
    // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    //aesKey必须是16位数字或字母的组合，是和补丁工具设置里面AES Key保持完全一致, 补丁才能正确被解密进而加载
    final String aesKey = "";
    final String appKey = "27889632";
    final String appSecret = "e06b7ad5a59485acc76a4e1c2ffe2f6f";
    final String rsa = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCRqZbwMvnrqwMlBCjsLOnFNz946QOlIufeVzJuIakF2TjllEOgenFaAP+16ObZ403LNy8zjbAtajBHVVuzPrAfayIvLTIX39iBkNP7yIZBww1EtzcdXBvAtSla6aC1xkjUuWxXbj1BXINo9nD1RqPGZOJbrR/MbBJTF33UPlv6hjmBxqk4FIh5tJ1iXf1Hkejj/AptUvc2v18LlovINIs4q3Epn9dQ/39poV719HorEeshzrF1gUFMefuxfDO2b2k00FXzVL5HIoeJzlNVlOxLN0iPn0d0nImRzEoVXU+3vFCJS05SKWiTE8S1oeuJ5bPcNcCRww8l7xmPI953ZnIRAgMBAAECggEAXGVJtBVxyFaSyREpcB5RLuMLpQORZ1eL4tJGz+sUDvyqBZ6Vgx8H9cJ6cYZJoN55QzU/AKGYpkYcV7NCVqGmEFfhuDEf/FrmN9GJVcXtu5shOHxLPplllUiU8fEOGx+qpRz1J0RrIKGxTYw9PKhRTJBDsUlQyVeoDALzswTXTUKJ+8nZAJopOsDoyTV2WjWwhAKKa3TEB12pofpCMM18rcprM7hX2FDc07n6kDYtC5rAWW2ZcIOGbgPI54VfcXCnEULvzmdee42fX7Eci3UD0p7FEvYYnlWa1wIilBU58PLKGSHOF7XbGoMesnBvn8Pt+ecHRn/ewH5rMWIjbFh10QKBgQDxJ3Oj68r9eRS3KS2xONjsRUAQJA/l1YEP1Y0Kx9Iik6jNgv8ASsIGYplB73CdlLFsP96pY48TH+MYR/5B8JHGqiQRM16y6xR+KJCgbjP/BSsidDSQ0qYM2xTHHueqFo1GjAnbjbPu0dtxFeHQKQiJmFkW7dTCT29cAotr1bYcNQKBgQCaoTSc86PNyblUQAj0mHFmbll1Bilo/Bd56F180dsBvYgLBtb12RQAwvRkqwZ0q4/rdKMa2HdWkXiBK13w2Bya3O7uE/52l3yDnF5UUUVBsQ9YyLlsVQJlW7lt5oq+n9rxdKsxyir1BRy0w+XbH4Q5hROd5lIj+GFIwVHHNe2h7QKBgQCQLTQYFssfzBo0f2wP/prCd4ybmdPfZ0xoORbAEUNy5LERXXYzqH5j7uMN5qd/rvx8aZsnzpwlpPTS+YfryHZ/Pg2eMwY1FhMEciA1qm1v1Jk7WhprU2VvXjQH8FQ0/pKsUOE74HZpHBKEg76kA0RVbz4kVUDUg0Vizyqn5/ee2QKBgDCRAV1qiMBR44u6sRVh6/woHKDM1qTKyPTZLsPNheP+C4SrGUkObWHv2aLktJpgaEujzrbX1mOyPQ8U9+R1fEaHrePJE9tbqRgNPPNuszn2ysMwCE0mRiJftNX32wwUWUgNRNfWj7d1a5a7EK7KL1WBnq+xb4sssCGR1oWjQofhAoGBAJXBkmXblvYv85N4pK7/OwiGAVNn9Y0bPIhthOLQCB7rwrzOCV4NLhbxOKzCIlo2sNWMYPhNa1TTAXbjCqM20m84HiQ9mtRI4cZwCz9ZlJ8zCReczcu78nO6kRLgAkHHtHH+ilLb5j3ylmrgnjlL7UycFI0Mu+ZdnLoNw1sUwl78";

    //这里的SophixEntry写入你需要初始化的Application
    @Keep
    @SophixEntry(App.class)
    static class RealApplicationStub {}
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
//         MultiDex.install(this);
        initSophix();
    }
    private void initSophix() {
        String appVersion = "1.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAesKey(null)
                .setAppVersion(appVersion)
                .setSecretMetaData(appKey, appSecret, rsa)
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.i(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            Log.i(TAG, "sophix preload patch success. restart app to make effect.");
                        }
                    }
                }).initialize();
    }

}
