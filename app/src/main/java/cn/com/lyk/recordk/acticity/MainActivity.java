package cn.com.lyk.recordk.acticity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import cn.com.lyk.recordk.R;
import cn.com.lyk.recordk.adapter.SectionsPagerAdapter;
import cn.com.lyk.recordk.fragment.CategoryFragment;
import cn.com.lyk.recordk.fragment.HomeFragment;
import cn.com.lyk.recordk.fragment.MineFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar
        .OnTabSelectedListener, ViewPager.OnPageChangeListener {
    private BottomNavigationBar bottomNavigationBar;
    private ViewPager viewPager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据
        initView();
    }

    private void initView() {
        //初始化底部导航栏数据
        initBottomNavigationBar();
        //初始化ViewPager
        initViewPager();
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new MineFragment());

        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(this);

        viewPager.setCurrentItem(0);

    }

    private void initBottomNavigationBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.icon_main_home_selected, "记账")
                        .setInactiveIconResource(R.drawable.icon_main_home_normal)
                        .setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_main_category_selected, "分类")
                        .setInactiveIconResource(R.drawable.icon_main_category_normal)
                        .setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_main_mine_selected, "我的")
                        .setInactiveIconResource(R.drawable.icon_main_mine_normal)
                        .setActiveColorResource(R.color.orange))
                .initialise();
    }

    //BottomNavigationBar继承类
    @Override
    public void onTabSelected(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    //ViewPager继承类
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
