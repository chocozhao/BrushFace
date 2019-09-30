package com.yzf.king.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baidu.location.BDLocation;
import com.google.gson.Gson;
import com.yzf.king.R;
import com.yzf.king.adapter.MerchPickAdapter;
import com.yzf.king.adapter.TeamAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetChannelMerchResult;
import com.yzf.king.model.servicesmodels.GetLocationResult;
import com.yzf.king.present.PMerchPick;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.log.Logger;
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
 * ClassName：MerchPickActivity
 * Description:mpos商户选择页面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/3/6 17:42
 * Modified By：
 * Fixtime：2019/3/6 17:42
 * FixDescription：
 */
public class MerchPickActivity extends XActivity<PMerchPick> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private static final String KEY = "current_theme";
    @BindView(R.id.city_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.city_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;


    private List<HotCity> hotCities;
    private int anim;
    private int theme;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    String city = null;
    String channelId;
    MerchPickAdapter adapter;
    BDLocation location = new BDLocation();
    private LocatedCity locatedCity;

    @Override
    public void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt(KEY);
            setTheme(theme > 0 ? theme : R.style.DefaultCityPickerTheme);
        }
        channelId = getIntent().getStringExtra("channelId");
        initView();
//        getP().getLocationInfo(AppUser.getInstance().getMerchId());

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_city_pick;
    }

    @Override
    public PMerchPick newP() {
        return new PMerchPick();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        initAdapter();
        anim = R.style.DefaultCityPickerAnimation;
        hotCities = new ArrayList<>();
        hotCities.add(new HotCity("广州", "广东", "101280101"));
        hotCities.add(new HotCity("北京", "北京", "101010100"));
        hotCities.add(new HotCity("天津", "天津", "101030100"));
        hotCities.add(new HotCity("厦门", "福建", "101020100"));
        hotCities.add(new HotCity("重庆", "重庆", "101040100"));
        hotCities.add(new HotCity("福州", "福建", "101230101"));
        hotCities.add(new HotCity("泉州", "福建", "101020100"));
        hotCities.add(new HotCity("济南", "山东", "101120101"));
        hotCities.add(new HotCity("深圳", "广东", "101280601"));
        hotCities.add(new HotCity("长沙", "湖南", "101250101"));
        hotCities.add(new HotCity("无锡", "江苏", "101020100"));
        if (AppTools.isEmpty(AppUser.getInstance().getCity())) {
            locatedCity = new LocatedCity("正在定位...", null, null);
        } else {
            locatedCity = new LocatedCity(AppUser.getInstance().getCity(), location.getProvince(), location.getCityCode());
        }
        CityPicker.from(this) //activity或者fragment
                .enableAnimation(true)    //启用动画效果，默认无
                .setAnimationStyle(anim)    //自定义动画
                .setLocatedCity(locatedCity)
                .setHotCities(hotCities)    //指定热门城市
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        city = data.getName();
                        //获取Intent，添加需要返回的数据，返回Intent,关闭本页
                        Intent intent = getIntent();
                        intent.putExtra("param", city);
                        setResult(0, intent);
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        finish();
//                        showToast("取消选择");
                    }

                    @Override
                    public void onLocate() {
                        //定位接口，需要APP自身实现，这里模拟一下定位
                       /* new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //定位完成之后更新数据
                            }
                        }, 3000);*/
                    }
                })
                .show();

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
        title.setText("选择地区");
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
     * 显示双按钮对话框
     *
     * @param msg
     * @param callback
     */
    public void showNoticeDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        getvDelegate().showNoticeDialog(msg, callback);
    }


    /**
     * 显示错误信息
     *
     * @param error
     */
    public void showError(NetError error) {
        getvDelegate().showError(error);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY, theme);
    }

    public void setInfo(GetLocationResult.DataBean data) {
        CityPicker.from(MerchPickActivity.this).locateComplete(new LocatedCity(data.getCity(), data.getCountryName(), data.getRegion()), LocateState.SUCCESS);
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new MerchPickAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetChannelMerchResult.DataBean, MerchPickAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetChannelMerchResult.DataBean item, int tag, MerchPickAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case TeamAdapter.TAG_VIEW:
                            Gson gson = new Gson();
                            AppUser.getInstance().setMposMerchInfo(gson.toJson(item));
                            finish();
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.verticalLayoutManager(this);
        recyclerview.setAdapter(adapter);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                Logger.i("onRefresh");
                getP().getMposChannelMerchInfo(AppUser.getInstance().getMerchId(), AppUser.getInstance().getMposFeeBean().getTransType(), city, null, 1, pageSize, channelId);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getMposChannelMerchInfo(AppUser.getInstance().getMerchId(), AppUser.getInstance().getMposFeeBean().getTransType(), city, null, page, pageSize, channelId);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getMposChannelMerchInfo(AppUser.getInstance().getMerchId(), AppUser.getInstance().getMposFeeBean().getTransType(), city, null, 1, pageSize, channelId);
            }
        });
    }


    public void setAdapter(List<GetChannelMerchResult.DataBean> data, int page) {
        swiperefreshlayout.setRefreshing(false);
        getvDelegate().dismissLoading();
        title.setText("选择商户");
        if (page > 1) {
            adapter.addData(data);
        } else {
            adapter.setData(data);
        }
        if (data.size() < pageSize) {
            recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
            recyclerview.removeAllFootView();
        } else {
            recyclerview.setPage(page, MAX_PAGE);//必须设置setPage，否则上拉加载更多会无效
        }
    }

}
