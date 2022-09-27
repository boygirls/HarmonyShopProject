package com.shop;

import com.shop.slice.TabListSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class TabList extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(TabListSlice.class.getName());
    }
}
