package com.shop.x.push;

import com.huawei.hms.push.ohos.HmsMessageService;
import com.huawei.hms.push.ohos.ZBaseException;
import com.huawei.hms.push.ohos.ZRemoteMessage;
import com.shop.x.push.utils.LogUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.rpc.IRemoteObject;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class HmsMessageServiceAbility extends HmsMessageService {
    @Override
    public void onMessageReceived(ZRemoteMessage message) {

        // 打印消息的内容字段
        LogUtil.i("get token, %{public}s" + message.getToken());
        LogUtil.i("get data, %{public}s" + message.getData());

        ZRemoteMessage.Notification notification = message.getNotification();
        if (notification != null) {
            LogUtil.i( "get title, %{public}s" + notification.getTitle());
            LogUtil.i( "get body, %{public}s" +  notification.getBody());
        }
    }

    @Override
    public void onNewToken(String token) {
        LogUtil.i( "DemoHmsMessageServiceAbility::onNewToken token:" + token);
    }

    @Override
    public void onTokenError(Exception exception) {
        if (exception instanceof ZBaseException) {
            LogUtil.i("DemoHmsMessageServiceAbility::onTokenError errCode:" +
                    ((ZBaseException) exception).getErrorCode());
        }
    }

}