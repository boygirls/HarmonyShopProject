package com.shop.x;

import com.shop.x.slice.WelcomeAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class WelcomeAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(WelcomeAbilitySlice.class.getName());
    }
}
