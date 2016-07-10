package com.aming.translate.presenter;

import android.util.Log;

import com.aming.translate.db.Favorite;
import com.aming.translate.db.TranslateRecord;
import com.aming.translate.model.bean.BaseModel;
import com.aming.translate.model.biz.QueryView;
import com.aming.translate.model.biz.Translate;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wenming on 2016/6/26.
 */
public class QueryPresenter {
    private QueryView mMainView;

    private BaseModel mBaseModel;

    public QueryPresenter(QueryView mainView){
        mMainView = mainView;
    }

    public void search(String query){
        Translate.getInstance().getCall(query).enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                mMainView.searchComplete(response.body().toString());
                mBaseModel = response.body();
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                Log.v("fag", "fail" + t.toString());
            }
        });
    }

    public void addToFavorite(){
        Favorite favorite = new Favorite();
        favorite.setQuery(mBaseModel.query);
        favorite.setTranslation(mBaseModel.getTranslation());
        if (mBaseModel.basic!=null){
            favorite.setPhonetic(mBaseModel.basic.phonetic);
            favorite.setExplain(mBaseModel.basic.toString());
        }
        if (mBaseModel.web!=null){
            favorite.setWebTranslation(mBaseModel.getWebTranslation());
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        favorite.setDate(date);
        favorite.save();
    }

}
