package com.shop.x.home;

import com.shop.x.home.slice.HomeSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class homeAbility extends Ability {

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(HomeSlice.class.getName());
    }
}
