package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetPushMsgJGResults;
import com.yzf.king.present.PMsgDetail;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XActivity;

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
 * ClassName：AboutActivity
 * Description: 关于我们界面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/18 11:34
 * Modified By：
 * Fixtime：2017/5/18 11:34
 * FixDescription：
 */
public class MsgDetailActivity extends XActivity<PMsgDetail> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.msgdetail_title_tv)
    TextView msgdetailTitleTv;
    @BindView(R.id.msgdetail_time_tv)
    TextView msgdetailTimeTv;
    @BindView(R.id.msgdetail_content_tv)
    TextView msgdetailContentTv;

    private GetPushMsgJGResults.DataBean dataBean;


    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = (GetPushMsgJGResults.DataBean) getIntent().getSerializableExtra("dataBean");
        getP().updateMsg(dataBean);
        initToolbar();
        msgdetailTitleTv.setText(dataBean.getMsgTitle());
        msgdetailTimeTv.setText(Kits.Date.getYmdhms(Long.valueOf(dataBean.getMsgTime())));
        msgdetailContentTv.setText(dataBean.getMsgContent());
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        String titles = dataBean.getMsgTitle();
        if (AppTools.isEmpty(titles)) {
            titles = "消息";
        }
        title.setText(titles);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_msgdetail;
    }

    @Override
    public PMsgDetail newP() {
        return new PMsgDetail();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
