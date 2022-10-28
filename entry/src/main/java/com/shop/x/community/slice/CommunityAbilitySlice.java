package com.shop.x.community.slice;

import com.shop.x.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

public class CommunityAbilitySlice extends AbilitySlice {

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_community);
        Button componentById = (Button) findComponentById(ResourceTable.Id_connTest);

        componentById.setClickedListener(component -> {

            System.out.println("输出内容");
            TaskDispatcher globalTaskDispatcher = getGlobalTaskDispatcher(TaskPriority.DEFAULT);
            globalTaskDispatcher.asyncDispatch(()->{
                System.out.println("测试点击内容");
            });
        });
    }

    @Override
    protected void onActive() {
        super.onActive();
    }

    @Override
    protected void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
