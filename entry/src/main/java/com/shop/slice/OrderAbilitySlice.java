package com.shop.slice;

import com.shop.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;


public class OrderAbilitySlice extends AbilitySlice {

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        setUIContent(ResourceTable.Layout_ability_order);

        Button btn1 = (Button) findComponentById(ResourceTable.Id_btn_exit);
        Button btn2 = (Button) findComponentById(ResourceTable.Id_order_button);

        btn1.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                Intent intent1 = new Intent();
                new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName("com.shop")
                        .withAbilityName("com.shop.slice.TabListSlice")
                        .build();
                Operation operation = (Operation) new Intent.OperationBuilder();
                intent1.setOperation(operation);
                startAbility(intent1);
            }
        });

        btn2.setClickedListener(component -> {
            Intent intent2 = new Intent();
            DirectionalLayout toastLayout = (DirectionalLayout) LayoutScatter.getInstance(this)
                    .parse(ResourceTable.Layout_layout_toast_order, null, false);
            new ToastDialog(getContext())
                    .setContentCustomComponent(toastLayout)
                    .setSize(DirectionalLayout.LayoutConfig.MATCH_CONTENT, DirectionalLayout.LayoutConfig.MATCH_CONTENT)
                    .setAlignment(LayoutAlignment.CENTER)
                    .show();
        });
    }
}
