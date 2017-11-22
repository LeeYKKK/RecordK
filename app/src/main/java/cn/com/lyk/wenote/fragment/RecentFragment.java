package cn.com.lyk.wenote.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.lyk.wenote.R;

/**
 * Created by lyk on 2017/10/24.
 */
public class RecentFragment extends Fragment {
    private String mTitle;
    private static final String BUNDLE_TITLE="title";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        if(bundle!=null){
            mTitle=bundle.getString(BUNDLE_TITLE);
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent, container, false);
    }
    public static RecentFragment newInstance(String title){
        Bundle bundle=new Bundle();
        bundle.putString(BUNDLE_TITLE,title);
        RecentFragment recentFragment=new RecentFragment();
        recentFragment.setArguments(bundle);
        return recentFragment;
    }
}
