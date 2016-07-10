package com.aming.translate.view.aty;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.aming.translate.R;
import com.aming.translate.main.BaseActivity;
import com.aming.translate.view.frag.QueryFragment;

import org.litepal.tablemanager.Connector;

public class RecordActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record;
    }

    @Override
    protected void initPresenter() {
        SQLiteDatabase db = Connector.getDatabase();
    }

    @Override
    protected void findView() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content, new QueryFragment());
        ft.commit();
    }
}
