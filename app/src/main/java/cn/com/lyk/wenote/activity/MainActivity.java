package cn.com.lyk.wenote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.com.lyk.wenote.R;
import cn.com.lyk.wenote.adapter.SectionsPagerAdapter;
import cn.com.lyk.wenote.fragment.FolderFragment;
import cn.com.lyk.wenote.fragment.LabelFragment;
import cn.com.lyk.wenote.fragment.RecentFragment;
import cn.com.lyk.wenote.utils.PermissionUtil;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        TabLayout.OnTabSelectedListener {
    @ViewInject(R.id.view_pager)
    private ViewPager viewPager;
    @ViewInject(R.id.mTabLayout)
    private TabLayout mTabLayout;
    @ViewInject(R.id.fab)
    private FloatingActionButton fab;
    private List<Fragment> fragments = new ArrayList<Fragment>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        //检查是否开启文件管理权限
        PermissionUtil.checkVersion(MainActivity.this);
        //初始化数据
        initView();

    }

    private void initView() {

        //初始化ViewPager
        initViewPager();
    }

    @Event(value = {R.id.fab})
    private void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                Intent intent=new Intent(this,AddNoteActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void initViewPager() {
        fragments.add(new RecentFragment());
        fragments.add(new FolderFragment());
        fragments.add(new LabelFragment());
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragments));
        mTabLayout.addOnTabSelectedListener(this);
        viewPager.addOnPageChangeListener(this);


    }

    //ViewPager继承类
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //viewPager滑动之后显示触发
        mTabLayout.getTabAt(position).select();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //TabLayout继承类
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //TabLayout里的TabItem被选中的时候触发
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
