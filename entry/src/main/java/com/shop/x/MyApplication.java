package com.shop.x;

import com.huawei.hms.accountsdk.exception.ApiException;
import com.huawei.hms.accountsdk.support.account.AccountAuthManager;
import com.huawei.hms.accountsdk.support.account.tasks.OnFailureListener;
import com.huawei.hms.accountsdk.support.account.tasks.OnSuccessListener;
import com.huawei.hms.accountsdk.support.account.tasks.Task;
import com.shop.x.util.HuaweiAccountSDKProxy;
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
        // 调用示例initHuaweiAccountSDK方法，在鸿蒙应用初始化方法OnInitialize中进行华为帐号SDK初始化
        new HuaweiAccountSDKProxy().initHuaweiAccountSDK(this);

    }

    @Override
    public void onEnd() {
        super.onEnd();
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
