package com.example.administrator.demo.http;

import com.example.administrator.demo.bean.DemoBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface HttpService {
    @GET("users/{user}/repos")
    Call<List<DemoBean>> listDemoBean(@Path("user") String user);
}
