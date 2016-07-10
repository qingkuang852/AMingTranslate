package com.aming.translate.model.biz;

import com.aming.translate.model.bean.BaseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wenming on 2016/6/26.
 */
public interface YouDaoApi {

    @GET("openapi.do")
    Call<BaseModel> getTranslation(@Query("keyfrom") String keyfrom,@Query("key") String key,
                                   @Query("type") String type,@Query("doctype") String doctype,
                                   @Query("version") float version,@Query("q") String q);
}
