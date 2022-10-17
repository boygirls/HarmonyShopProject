package com.shop.slice;

import com.shop.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
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

        btn1.setClickedListener(component -> {
            present(new TabListSlice(),intent);
        });

        btn2.setClickedListener(component -> {
//            Intent intent2 = new Intent();
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
