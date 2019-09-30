package cn.droidlover.xdroidmvp.mvp;

import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import cn.droidlover.xdroidmvp.net.NetError;

/**
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/29 10:32
 * Modified By：
 * Fixtime：2016/12/29 10:32
 * FixDescription：
 */

public interface VDelegate {
    void resume();

    void pause();

    void destory();

    void visible(boolean flag, View view);

    void gone(boolean flag, View view);

    void inVisible(View view);

    void toastShort(String msg);

    void toastLong(String msg);

    void toast(String msg, ToastType type);

    void showSnackbar(View view, String msg, String tip, View.OnClickListener listener);

    void showNoticeDialog(String msg, MaterialDialog.SingleButtonCallback callback);

    void showErrorDialog(String msg);

    void showErrorDialog(String msg, MaterialDialog.SingleButtonCallback callback);

    void showLoading(String msg);

    void showLoading();

    void dismissLoading();

    void showError(NetError error);

    String getErrorType(NetError error);

    void showAlerter(String title, String msg);

    void showAlerter(String title, String msg, View.OnClickListener listener);

    void showPushAlerter(String title, String msg, View.OnClickListener listener);

    void showDialog(String title, String msg, String positiveText, String negativeText, MaterialDialog.SingleButtonCallback singleButtonCallback, Boolean cancle);


}
