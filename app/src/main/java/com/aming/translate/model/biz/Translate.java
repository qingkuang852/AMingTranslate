package com.aming.translate.model.biz;

import com.aming.translate.model.bean.BaseModel;

import retrofit2.Call;

/**
 * Created by wenming on 2016/6/26.
 */
public class Translate {

    public static Translate translate;

    private YouDaoApi youDaoApi;

    public static Translate getInstance(){
        if (translate == null){
            translate = new Translate();
        }
        return translate;
    }

    private Translate(){
        youDaoApi = RetrofitApi.getInstance().create(YouDaoApi.class);
    }


    public Call<BaseModel> getCall(String query){
        return youDaoApi.getTranslation("amingtrans","1113909198"
                ,"data","json",1.1f,query);
    }
}
