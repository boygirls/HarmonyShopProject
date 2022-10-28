package com.shop.x.community;

import com.shop.x.community.slice.CommunityAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class CommunityAbility extends Ability {

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(CommunityAbilitySlice.class.getName());
    }
}
