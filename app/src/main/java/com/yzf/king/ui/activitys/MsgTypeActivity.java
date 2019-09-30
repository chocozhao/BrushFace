package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.adapter.MsgTypeAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetPushMsgCountResult;
import com.yzf.king.model.servicesmodels.GetPushMsgJGResults;
import com.yzf.king.present.PMsgType;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 　　┏┓　　　┏┓+ +
 * 　┏┛┻━━━┛┻┓ + +
 * 　┃　　　　　　　┃
 * 　┃　　　━　　　┃ ++ + + +
 * ████━████ ┃+
 * 　┃　　　　　　　┃ +
 * 　┃　　　┻　　　┃
 * 　┃　　　　　　　┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽镇楼
 * 　　　┃　　　┃    代码无bug
 * 　　　┃　　　┃　　+
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * ClassName：MsgTypeActivity
 * Description:  消息中心
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/4/19 15:44
 * Modified By：
 * Fixtime：2019/4/19 15:44
 * FixDescription：
 */
public class MsgTypeActivity extends XActivity<PMsgType> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.msgtype_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.msgtype_multiplestatusview)
    MultipleStatusView multiplestatusview;

    MsgTypeAdapter adapter;
    String type;
    GetPushMsgJGResults.DataBean dataBean;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_msg_type;
    }

    @Override
    public int getOptionsMenuId() {
        return R.menu.menu_msgtype;
    }

    @Override
    public PMsgType newP() {
        return new PMsgType();
    }

    @Override
    public void onResume() {
        super.onResume();
        getP().getPushMsgCount(AppUser.getInstance().getMerchId());
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        initAdapter();
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("消息中心");
    }

    /**
     * 标题栏监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        switch (item.getItemId()) {
            case R.id.menu_msgtype:
                getvDelegate().showLoading();
                getP().updateMsg(AppUser.getInstance().getMerchId());
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * activity跳转
     */
    public void JumpActivity(Class<?> activity, boolean isfinish) {
        getvDelegate().dismissLoading();
        Router.newIntent(context)
                .to(activity)
                .launch();
        if (isfinish) {
            Router.pop(context);
        }
    }

    public void JumpActivity(Class<?> activity, String type, String title) {
        getvDelegate().dismissLoading();
        Router.newIntent(context)
                .to(activity)
                .putString(MsgActivity.TYPE, type)
                .putString(MsgActivity.TITLE, title)
                .launch();
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        getvDelegate().toastShort(msg);
    }

    /**
     * 显示单按钮对话框
     *
     * @param msg
     */
    public void showErrorDialog(String msg) {
        getvDelegate().showErrorDialog(msg);
    }

    /**
     * 显示错误信息
     *
     * @param error
     */
    public void showError(NetError error) {
        getvDelegate().showError(error);
    }

    public void showErrorView(String msg) {
        multiplestatusview.showError(msg);
    }

    public void showErrorView(NetError error) {
        multiplestatusview.showError(getvDelegate().getErrorType(error));
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new MsgTypeAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetPushMsgCountResult.DataBean, MsgTypeAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, GetPushMsgCountResult.DataBean model, int tag, MsgTypeAdapter.ViewHolder holder) {
                    super.onItemClick(position, model, tag, holder);
                    switch (tag) {
                        case MsgTypeAdapter.TAG_VIEW:
                            JumpActivity(MsgActivity.class, model.getType(), model.getTypeName());
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        recyclerview.verticalLayoutManager(this);
        //加一条线
        recyclerview.addItemDecoration(new MyDividerItemDecoration(context, MyDividerItemDecoration.HORIZONTAL_LIST));
        recyclerview.setAdapter(adapter);
    }


    public void setAdapter(List<GetPushMsgCountResult.DataBean> data) {
        if (data == null) {
            multiplestatusview.showEmpty("暂无数据");
        } else {
            multiplestatusview.showContent();//显示内容视图
            adapter.setData(data);
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
    }

    public void refresh() {
        getvDelegate().dismissLoading();
        getP().getPushMsgCount(AppUser.getInstance().getMerchId());
    }

}
