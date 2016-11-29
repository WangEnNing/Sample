package demo.wen.com.mydemo.activity.fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;

import butterknife.Bind;
import demo.wen.com.mydemo.R;
import demo.wen.com.mydemo.activity.MainActivity;
import demo.wen.com.mydemo.base.BaseFragment;

/**
 * Created by wangenning on 2016/11/8.
 */

public class NewsFragment extends BaseFragment {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected int getLayoutResource() {
        return R.layout.news_fragment;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar.setTitle("首页");
        inflateMenu();
        ((MainActivity) getActivity()).initDrawer(toolbar);
    }

    private void inflateMenu() {
        toolbar.inflateMenu(R.menu.menu_frist);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_search:

                        break;
                    case R.id.menu_about:
                        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/WuXiaolong/DesignSupportLibrarySample"));
                        getActivity().startActivity(intent1);
                        break;
                }
                return true;
            }
        });
    }
}
