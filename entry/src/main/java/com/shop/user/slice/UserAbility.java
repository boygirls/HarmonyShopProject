package com.shop.user.slice;

import com.shop.user.slice.slice.UserAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class UserAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(UserAbilitySlice.class.getName());
    }
}
