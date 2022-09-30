package com.shop.slice;

import com.shop.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeAbilitySlice extends AbilitySlice {

    // 定义显示的时间
    private static final long DELAY = 3000;

    private TimerTask task;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_Welcome);
    }

    @Override
    public void onActive() {
        super.onActive();

         final Intent intent = new Intent();

        // 通过Intent中的OperationBuilder类构造operation对象，指定设备标识（空串表示当前设备）、应用包名、Ability名称
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")
                .withBundleName("com.shop")
                .withAbilityName("com.shop.TabList")
                .build();

        // 把operation设置到intent中
        intent.setOperation(operation);

        Timer timer=new Timer();
        task=new TimerTask() {
            @Override
            public void run(){
                startAbility(intent);//执行
            }
        };

        timer.schedule(task,DELAY);
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
