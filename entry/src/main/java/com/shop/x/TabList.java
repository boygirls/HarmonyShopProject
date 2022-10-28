package com.shop.x;

import com.shop.x.slice.LoginAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class TabList extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(LoginAbilitySlice.class.getName());
    }
}
