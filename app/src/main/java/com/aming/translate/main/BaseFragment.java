package com.aming.translate.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aming.translate.tools.MyLog;

/**
 * Created by wenming on 2016/6/27.
 */
public abstract class BaseFragment extends Fragment {

    //根控件view
    protected View rootView;
    //上下文
    protected Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView==null){
            rootView = inflater.inflate(getLayoutView(),null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent!=null){
            parent.removeView(rootView);
        }
        mContext = getActivity();
        initFindView();
        initSet();
        MyLog.v("onCreateView");
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLog.v("onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        loadDatas(bundle);
        MyLog.v("onActivityCreated");
    }

    protected abstract int getLayoutView();

    protected abstract void initFindView();

    protected abstract void initSet();

    public void loadDatas(Bundle bundle){};


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyLog.v("onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        MyLog.v("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        MyLog.v("onResume");
    }

}
