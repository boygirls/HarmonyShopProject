package com.shop.util;


import com.shop.home.model.Shopcart;
import com.shop.user.model.User;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.app.Context;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.rdb.ValuesBucket;
import ohos.data.resultset.ResultSet;
import ohos.utils.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class DataBaseUtil {

    private static Uri uri1 = Uri.parse("dataability:///com.shop.MallDataAbility/mall_info");
    private static Uri uri2 = Uri.parse("dataability:///com.shop.MallDataAbility/user");
    private static Uri uri3 = Uri.parse("dataability:///com.shop.MallDataAbility/ShopCart");

    public static String getValue(String key, Context context){
        String value = null;
        //访问本地数据库，是否存在用户信息，如果存在则加载Layout_tab_lists
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);

        String[] colums = {"id","key","value"};
        DataAbilityPredicates predicates = new DataAbilityPredicates();
        predicates.equalTo("key",key);  //查询条件
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

    public static int getUser1(String users, Context context){
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);

        String[] colums = {"userId","username","password","token"};
        DataAbilityPredicates predicates = new DataAbilityPredicates();
        predicates.equalTo("userId",users);
        try {
            ResultSet rs = dataAbilityHelper.query(uri2, colums, predicates);
            if(rs.getRowCount() > 0){
               return 1;
            }
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

//    public static String getuser(Context context){
//        String value = null;
//        String[] colums = {"userId","username","password","token"};
//        RdbPredicates rdbPredicates = new RdbPredicates("user").equalTo("userId",1);
//            ResultSet resultSet = rdbStore.query(rdbPredicates,colums);
//            resultSet.goToNextRow();
//        return value;
//    }

    public static User getUser(User users, Context context){
        User value = new User();
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);

        String[] colums = {"userId","username","password","token"};
        DataAbilityPredicates predicates = new DataAbilityPredicates();
//        predicates.equalTo("userId",userId);
        predicates.equalTo("username",users.getUsername())
                .and().equalTo("password",users.getPassword());  //查询条件

//        predicates.equalTo("password",password);
//        predicates.equalTo("token",token);

        try {
            ResultSet rs = dataAbilityHelper.query(uri2, colums, predicates);

            if(rs.getRowCount() > 0){      //表的总数
                rs.goToFirstRow();
                value.setId(rs.getInt(0));
                value.setUsername(rs.getString(1));
                value.setPassword(rs.getString(2));
                value.setToken(rs.getString(3));
                return value;
            }
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static int setUser(int userId, String username, String password, String token,Context context){

        int i = 0;
        ValuesBucket valuesBucket = new ValuesBucket();
        valuesBucket.putString("userId",userId+"");
        valuesBucket.putString("username",username);
        valuesBucket.putString("password",password);
        valuesBucket.putString("token",token);
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);
        try{
            i = dataAbilityHelper.insert(uri2,valuesBucket);
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
        return i;
    }


    public static int setShoppingCart(Shopcart shopcart,Context context){

        int i = 0;

        ValuesBucket values = new ValuesBucket();

        //values.putString("cartId", shopcart.getCartId()+"");
        values.putString("userId", "1");
        values.putString("token",shopcart.getToken());
        values.putString("productId", shopcart.getProductId());
        values.putString("cartNum", shopcart.getCartNum());
        values.putString("productPrice", shopcart.getProductPrice()+"");
        values.putString("productName", shopcart.getProductName());
        values.putString("productImg", shopcart.getProductImg());
        values.putString("originalPrice", shopcart.getOriginalPrice()+"");
        values.putString("sellPrice", shopcart.getSellPrice()+"");

        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);
        try {
            i = dataAbilityHelper.insert(uri3,values);
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }


        return i;
    }

    public static List<Shopcart> getShoppingCart(int userId,Context context){

        List<Shopcart> list = new ArrayList<>();

        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);

        String[] colums = {"cartId","userId","token","productId","cartNum","productPrice","productName","productImg","originalPrice","sellPrice"};
        DataAbilityPredicates predicates = new DataAbilityPredicates();
        predicates.equalTo("userId",userId);//查询条件

        try {
            ResultSet rs = dataAbilityHelper.query(uri3, colums, predicates);
            int a = rs.getRowCount();
            if(rs.getRowCount() > 0){      //表的总数
                rs.goToFirstRow();
                for (int i = 0; i < rs.getRowCount();i++){
                    Shopcart shopcart = new Shopcart();
                    shopcart.setCartId(rs.getInt(0));
                    shopcart.setUserId(rs.getInt(1));
                    shopcart.setToken(rs.getString(2));
                    shopcart.setProductId(rs.getString(3));
                    shopcart.setCartNum(rs.getString(5));
                    shopcart.setProductPrice(rs.getDouble(6));
                    shopcart.setProductName(rs.getString(8));
                    shopcart.setProductImg(rs.getString(9));
                    shopcart.setOriginalPrice(rs.getDouble(10));
                    shopcart.setSellPrice(rs.getDouble(11));
                    list.add(shopcart);
                    rs.goToNextRow();
                }
                return list;
            }
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static int deleteShopCart(Context context) throws DataAbilityRemoteException {

        int value = 0;

        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);
        DataAbilityPredicates predicates = new DataAbilityPredicates();
        value = (int)dataAbilityHelper.delete(uri3,predicates);

        return value;
    }
}
