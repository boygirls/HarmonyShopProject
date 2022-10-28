package com.shop.x.push.slice;

import com.huawei.hms.push.common.ApiException;
import com.huawei.hms.push.ohos.HmsInstanceId;
import ohos.aafwk.ability.AbilitySlice;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class TokenAbilitySlice extends AbilitySlice {

    private static final HiLogLabel LABEL_LOG = new HiLogLabel(HiLog.LOG_APP, 0xD001234,
            "TokenAbilitySlice");
    private void getToken() {
        // 创建新线程
        new Thread("getToken") {
            @Override
            public void run() {
                try {
                    // 从agconnect-services.json文件中读取client/app_id
                    String appId = "1006449490917337920";
                    // 输入token标识"HCM"
                    String tokenScope = "HCM";
                    // 获取Push Token
                    String token = HmsInstanceId.getInstance(getAbility().getAbilityPackage(),
                            TokenAbilitySlice.this).getToken(appId, tokenScope);
                } catch (ApiException e) {
                    // 获取Push Token失败时，打印错误码
                    HiLog.error(LABEL_LOG, "get token failed, the error code is %{public}d", e.getStatusCode());
                }
            }
        }.start();
    }
}