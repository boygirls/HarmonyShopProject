package com.shop.slice;

import com.shop.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class DetailAbilitySlice extends AbilitySlice {

    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        setUIContent(ResourceTable.Layout_ability_detail);

        String product = (String) intent.getParams().getParam("product");

        Text componentById = (Text) findComponentById(ResourceTable.Id_textDetail);

        componentById.setText(product);

        HiLog.info(LABEL, "" + product);
    }
}
