package com.shop;

import com.zhy.http.okhttp.OkHttpUtils;
import ohos.aafwk.ability.AbilityPackage;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class MyApplication extends AbilityPackage {
    @Override
    public void onInitialize() {
        super.onInitialize();
        /**
         * 初始化OkhttpUtils
         */
        initOkhttpClient();

    }


    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

}
