package com.example.administrator.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.administrator.demo.R;
import com.example.administrator.demo.bean.DemoBean;
import com.example.administrator.demo.http.HttpService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.send_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                testRxjava();
//                sendRequest();
            }
        });

    }

    private void testRxjava() {
//        // 第二个参数选择背压策略，这里选择为抛弃策略
//        final Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
//            @Override
//            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
//                if (!emitter.isCancelled()){
//                    emitter.onNext("Hello");
//                    emitter.onNext("Hi");
//                    emitter.onNext("Aloha");
//                    emitter.onComplete();
//                }
//            }
//        },BackpressureStrategy.DROP);
//
//        final Subscription subscription = new Subscription() {
//            @Override
//            public void request(long n) {
//                flowable.blockingNext();
//            }
//
//            @Override
//            public void cancel() {
//
//            }
//        };
//
//        Subscriber<String> subscriber = new Subscriber<String>() {
//            @Override
//            public void onSubscribe(Subscription s) {
//                Log.d(Contans.TAG," onSubscribe Item: " + s);
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d(Contans.TAG,"onNext Item: " + s);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                Log.d(Contans.TAG,"err: " + t.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(Contans.TAG,"Rxjava 发送完成");
//            }
//        };
//        flowable.subscribe(subscriber);
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                    emitter.onNext("Hello");
                    emitter.onNext("Hi");
                    emitter.onNext("Aloha");
                    emitter.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void sendRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())//将后台返回的json数据转成Json对象
                .build();
        HttpService service = retrofit.create(HttpService.class);

        Call<List<DemoBean>> demoBean = service.listDemoBean("octocat");
        demoBean.enqueue(new Callback<List<DemoBean>>() {
            @Override
            public void onResponse(Call<List<DemoBean>> call, Response<List<DemoBean>> response) {
                List<DemoBean> body = response.body();
                for (int i = 0; i < body.size(); i++) {
                    Log.d("wwx339202", "return" + i + ":" + body.get(i));
                }

            }

            @Override
            public void onFailure(Call<List<DemoBean>> call, Throwable t) {
                Log.d("wwx339202", "失败");
            }
        });
    }
}
