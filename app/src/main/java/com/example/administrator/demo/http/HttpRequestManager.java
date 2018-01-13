package com.example.administrator.demo.http;

import com.example.administrator.demo.Constants;
import com.example.administrator.demo.api.BaseResultEntity;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/1/13.
 */

public class HttpRequestManager {

    private HttpRequestManager requestManager;

    private HttpRequestManager() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.HTTP_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        HttpService service = retrofit.create(HttpService.class);
        Call<BaseResultEntity> call = service.getAll
    }

    public HttpRequestManager getInstance(){
      if (requestManager == null){
          synchronized (HttpRequestManager.class){
              if (requestManager == null){
                  requestManager = new HttpRequestManager();
              }
          }
      }
        return  requestManager;
    };



}
