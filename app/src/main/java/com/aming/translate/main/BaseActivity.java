package com.aming.translate.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by wenming on 2016/6/26.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(getLayoutId());
        initPresenter();
        findView();
    }

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    protected abstract void findView();
}
