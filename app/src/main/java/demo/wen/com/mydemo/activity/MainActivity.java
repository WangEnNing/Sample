package demo.wen.com.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import demo.wen.com.mydemo.R;
import demo.wen.com.mydemo.activity.fragment.FriendCircleFragment;
import demo.wen.com.mydemo.activity.fragment.MessageFragment;
import demo.wen.com.mydemo.activity.fragment.MineFragment;
import demo.wen.com.mydemo.activity.fragment.NewsFragment;
import demo.wen.com.mydemo.activity.fragment.SettingFragment;
import demo.wen.com.mydemo.base.BaseActivity;
import demo.wen.com.mydemo.daynightmodeutils.ChangeModeController;
import demo.wen.com.mydemo.utils.LogUtils;
import demo.wen.com.mydemo.widget.GlideCircleTransform;
import demo.wen.com.mydemo.widget.GlideRoundTransform;

public class MainActivity extends BaseActivity {
    @Bind(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation)
    NavigationView navigation;
    private ActionBarDrawerToggle mDrawerToggle;
    int position = 0;
    private NewsFragment newsFragment;
    private MineFragment mineFragment;
    private MessageFragment messageFragment;
    private SettingFragment settingFragment;
    private FriendCircleFragment friendCircleFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //记录当前的position
        outState.putInt("position", position);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        position = savedInstanceState.getInt("position");
        SwitchTo(position);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void initView() {
        initNavigationViewHeader();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        position = 0;
                        SwitchTo(position);
                        break;
                    case R.id.item2:
                        position = 1;
                        SwitchTo(position);
                        break;
                    case R.id.item3:
                        position = 2;
                        SwitchTo(position);
                        break;
                    case R.id.item4:
                        position = 3;
                        SwitchTo(position);
                        break;
                    case R.id.item5:
                        position = 4;
                        SwitchTo(position);
                        break;
                }
                return false;
            }
        });
    }
    private void initNavigationViewHeader() {
        //设置头像，布局app:headerLayout="@layout/drawer_header"所指定的头布局
        View view = navigation.inflateHeaderView(R.layout.drawer_header);
        ImageView my_head = (ImageView) view.findViewById(R.id.my_head);
        Glide.with(this)
                .load(R.mipmap.xiaomao)
                .centerCrop()
                .placeholder(R.mipmap.xiaomao)
                .crossFade()
                .transform(new GlideCircleTransform(this))
                .into(my_head);

//        TextView userName = (TextView) view.findViewById(R.id.userName);
//        userName.setText(R.string.name);
//        //View mNavigationViewHeader = View.inflate(MainActivity.this, R.layout.drawer_header, null);
//        //navigationView.addHeaderView(mNavigationViewHeader);//此方法在魅族note 1，头像显示不全
//        //菜单点击事件
//        left_view.setNavigationItemSelectedListener(new NavigationItemSelected());
    }
    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener()
                {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
    public void initDrawer(Toolbar toolbar) {
        if (toolbar != null) {
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }
            };
            mDrawerToggle.syncState();
            mDrawerLayout.addDrawerListener(mDrawerToggle);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //切换daynight模式要立即变色的页面
        ChangeModeController.getInstance().init(this, R.attr.class);
        super.onCreate(savedInstanceState);
        //初始化frament
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState != null) {
            newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentByTag("newsFragment");
            mineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag("mineFragment");
            messageFragment = (MessageFragment) getSupportFragmentManager().findFragmentByTag("MessageFragment");
            settingFragment = (SettingFragment) getSupportFragmentManager().findFragmentByTag("SettingFragment");
            friendCircleFragment = (FriendCircleFragment) getSupportFragmentManager().findFragmentByTag("FriendCircleFragment");
            position = savedInstanceState.getInt("position");
        } else {
            newsFragment = new NewsFragment();
            mineFragment = new MineFragment();
            messageFragment = new MessageFragment();
            settingFragment = new SettingFragment();
            friendCircleFragment = new FriendCircleFragment();
            transaction.add(R.id.mian_contentLayout, newsFragment, "newsFragment");
            transaction.add(R.id.mian_contentLayout, mineFragment, "mineFragment");
            transaction.add(R.id.mian_contentLayout, messageFragment, "MessageFragment");
            transaction.add(R.id.mian_contentLayout, settingFragment, "SettingFragment");
            transaction.add(R.id.mian_contentLayout, friendCircleFragment, "FriendCircleFragment");
        }
        transaction.commit();
        LogUtils.loge("position===" + position);
        SwitchTo(position);
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        LogUtils.loge("主页菜单position" + position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //新闻
            case 0:
                bottomNavigationView.setItemBackgroundResource(android.R.color.holo_red_dark);
                transaction.show(newsFragment);
                transaction.hide(mineFragment);
                transaction.hide(messageFragment);
                transaction.hide(settingFragment);
                transaction.hide(friendCircleFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                bottomNavigationView.setItemBackgroundResource(android.R.color.holo_green_light);
                transaction.hide(newsFragment);
                transaction.show(mineFragment);
                transaction.hide(messageFragment);
                transaction.hide(settingFragment);
                transaction.hide(friendCircleFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                bottomNavigationView.setItemBackgroundResource(android.R.color.holo_blue_light);
                transaction.hide(newsFragment);
                transaction.hide(mineFragment);
                transaction.show(messageFragment);
                transaction.hide(settingFragment);
                transaction.hide(friendCircleFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 3:
                bottomNavigationView.setItemBackgroundResource(android.R.color.darker_gray);
                transaction.hide(newsFragment);
                transaction.hide(mineFragment);
                transaction.hide(messageFragment);
                transaction.show(settingFragment);
                transaction.hide(friendCircleFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 4:
                bottomNavigationView.setItemBackgroundResource(R.color.new_maincolor);
                transaction.hide(newsFragment);
                transaction.hide(mineFragment);
                transaction.hide(messageFragment);
                transaction.hide(settingFragment);
                transaction.show(friendCircleFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }
}
