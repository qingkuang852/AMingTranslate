package com.aming.translate.model.biz;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wenming on 2016/6/26.
 */
public class RetrofitApi {

    public static RetrofitApi retrofitApi;

    private Retrofit retrofit;

    private RetrofitApi(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitApi getInstance(){
        if (retrofitApi == null){
            synchronized (RetrofitApi.class){
                if (retrofitApi == null) {
                    retrofitApi = new RetrofitApi();
                }
            }
        }
        return retrofitApi;
    }

    public <T> T create(Class<T> tClass){
        return retrofit.create(tClass);
    }
}
