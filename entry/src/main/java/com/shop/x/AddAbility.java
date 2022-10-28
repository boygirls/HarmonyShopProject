package com.shop.x;

import com.shop.x.slice.AddAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class AddAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(AddAbilitySlice.class.getName());
    }
}
