package com.shop.util;

import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.app.Context;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.rdb.ValuesBucket;
import ohos.data.resultset.ResultSet;
import ohos.sysappcomponents.contact.entity.Contact;
import ohos.utils.net.Uri;

public class DataBaseUtil {

    private static Uri uri = Uri.parse("dataability:///com.shop.MallDataAbility/mall_info");

    public static String getValue(String key, Context context){
        String value = null;
        //访问本地数据库，是否存在用户信息，如果存在则加载Layout_tab_lists
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);

        String[] colums = {"id","key","value"};
        DataAbilityPredicates predicates = new DataAbilityPredicates();
        predicates.equalTo("key",key);
        try {
            ResultSet rs = dataAbilityHelper.query(uri, colums, predicates);
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
            i = dataAbilityHelper.insert(uri, valuesBucket);
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
        return i;
    }
}
