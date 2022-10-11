package com.shop.util;

import com.shop.user.model.user;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.app.Context;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.rdb.ValuesBucket;
import ohos.data.resultset.ResultSet;
import ohos.utils.net.Uri;

public class DataBaseUtil {

    private static Uri uri1 = Uri.parse("dataability:///com.shop.MallDataAbility/mall_info");
    private static Uri uri2 = Uri.parse("dataability:///com.shop.MallDataAbility/user");
    private static Uri uri3 = Uri.parse("dataability:///com.shop.MallDataAbility/Shopcart");

    public static String getValue(String key, Context context){
        String value = null;
        //访问本地数据库，是否存在用户信息，如果存在则加载Layout_tab_lists
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);

        String[] colums = {"id","key","value"};
        DataAbilityPredicates predicates = new DataAbilityPredicates();
        predicates.equalTo("key",key);
        //predicates.equalTo("id","2");
        try {
            ResultSet rs = dataAbilityHelper.query(uri1, colums, predicates);
            if(rs.getRowCount() > 0){
                rs.goToFirstRow();
                value = rs.getString(0);
            }
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static int setValue(String key,String value,Context context){
        int i = 0;
        ValuesBucket valuesBucket = new ValuesBucket();
        valuesBucket.putString("key",key);
        valuesBucket.putString("value",value);
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);
        try {
            i = dataAbilityHelper.insert(uri1, valuesBucket);
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int initUser(user users, Context context){

        int i = 0;
        ValuesBucket valuesBucket = new ValuesBucket();
        valuesBucket.putString("userId",users.getId()+ "");
        valuesBucket.putString("username",users.getUsername());
        valuesBucket.putString("password",users.getPassword());
        valuesBucket.putString("token",users.getToken());
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);
        try{
            i = dataAbilityHelper.insert(uri2,valuesBucket);
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
        return i;
    }
}
