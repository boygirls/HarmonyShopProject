package com.shop;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.content.Intent;
import ohos.data.DatabaseHelper;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.dataability.DataAbilityUtils;
import ohos.data.rdb.*;
import ohos.data.resultset.ResultSet;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.utils.net.Uri;

public class MallDataAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");

    private static final String MALL_INFO = "mall_info";
    private static final String USER = "user";
    private static final String SHOPPING_CART = "Shopcart";

    private RdbStore rdbStore;
    private StoreConfig config = StoreConfig.newDefaultConfig("Mall.db");

    private RdbOpenCallback openCallback = new RdbOpenCallback() {
        @Override
        public void onCreate(RdbStore rdbStore) {
            //如数据表不存在需创建数据表
            rdbStore.executeSql("create table if not exists mall_info(id integer primary key autoincrement,key text not null unique,value text not null)");
            rdbStore.executeSql("create table if not exists user(" +
                    "id integer primary key autoincrement," +
                    "username text not null, " +
                    "password text not null, " +
                    "token text not null)");

//            "create table if not exists " + Const.DB_TAB_NAME + " (userId integer primary key autoincrement, "
//                    + Const.DB_COLUMN_NAME + " text not null, " + Const.DB_COLUMN_AGE + " integer)");

            rdbStore.executeSql("create table if not exists Shopcart(" +
                    " userId integer primary key autoincrement, " +
                    " cartId , " +
                    " productId , " +
                    " skuId , " +
                    " cartNum , " +
                    " cartTime , " +
                    " productPrice , " +
                    " skuProps , " +
                    " productName , " +
                    " productImg , " +
                    " originalPrice , " +
                    " sellPrice , " +
                    " skuName , " +
                    " skuStock ");
        }

        public void onUpgrade(RdbStore rdbStore, int i, int i1) {
        }
    };

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        HiLog.info(LABEL_LOG, "MallDataAbility onStart");
        //初始化数据库连接
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        rdbStore = databaseHelper.getRdbStore(config, 1, openCallback);

    }

    @Override
    public ResultSet query(Uri uri, String[] columns, DataAbilityPredicates predicates) {

        String lastPath = uri.getLastPath();
        if (MALL_INFO.equals(lastPath)) {

            RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, "mall_info");
            ResultSet resultSet = rdbStore.query(rdbPredicates, columns);
            return resultSet;

        } else if (USER.equals(lastPath)) {

            RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, USER);
            return rdbStore.query(rdbPredicates, columns);

        } else if (SHOPPING_CART.equals(lastPath)) {

            RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, SHOPPING_CART);
            return rdbStore.query(rdbPredicates, columns);
        }
        return null;
    }

    @Override
    public int insert(Uri uri, ValuesBucket value) {
        String lastPath = uri.getLastPath();
        if (MALL_INFO.equals(lastPath)) {
            int i = (int) rdbStore.insert("mall_info", value);
            return i;
        } else if (USER.equals(lastPath)) {

            ValuesBucket values = new ValuesBucket();

            values.putString("id", value.getString("id"));
            values.putString("username", value.getString("username"));
            values.putInteger("password", value.getInteger("password"));

            int index = (int) rdbStore.insert(USER, values);

            DataAbilityHelper.creator(this).notifyChange(uri);

            return index;
        }else if (SHOPPING_CART.equals(lastPath)) {

            ValuesBucket values = new ValuesBucket();

            values.putString("userId", value.getString("userId"));
            values.putString("cartId", value.getString("cartId"));
            values.putString("productId", value.getString("productId"));
            values.putString("skuId", value.getString("skuId"));
            values.putString("cartNum", value.getString("cartNum"));
            values.putString("cartTime", value.getString("cartTime"));
            values.putString("productPrice", value.getString("productPrice"));
            values.putString("skuProps", value.getString("skuProps"));
            values.putString("productName", value.getString("productName"));
            values.putString("productImg", value.getString("productImg"));
            values.putString("originalPrice", value.getString("originalPrice"));
            values.putString("sellPrice", value.getString("sellPrice"));
            values.putString("skuName", value.getString("skuName"));
            values.putString("skuStock", value.getString("skuStock"));

            int index = (int) rdbStore.insert(SHOPPING_CART, values);

            DataAbilityHelper.creator(this).notifyChange(uri);

            return index;
        }
        return -1;
    }

    @Override
    public int delete(Uri uri, DataAbilityPredicates predicates) {

        String lastPath = uri.getLastPath();
        if (MALL_INFO.equals(lastPath)) {
            RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, "mall_info");
            int i = rdbStore.delete(rdbPredicates);
            return i;

        } else if (USER.equals(lastPath)) {

            RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, USER);
            int index = rdbStore.delete(rdbPredicates);
            HiLog.info(LABEL_LOG, "%{public}s", "delete");
            DataAbilityHelper.creator(this).notifyChange(uri);
            return index;
        } else if (SHOPPING_CART.equals(lastPath)) {

            RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, SHOPPING_CART);
            int index = rdbStore.delete(rdbPredicates);
            HiLog.info(LABEL_LOG, "%{public}s", "delete");
            DataAbilityHelper.creator(this).notifyChange(uri);
            return index;
        }

        return -1;
    }

    @Override
    public int update(Uri uri, ValuesBucket value, DataAbilityPredicates predicates) {

        String lastPath = uri.getLastPath();
        if (MALL_INFO.equals(lastPath)) {
            RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, "mall_info");
            int i = rdbStore.update(value, rdbPredicates);
            return i;

        } else if (USER.equals(lastPath)) {

            RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, USER);
            int index = rdbStore.update(value, rdbPredicates);
            HiLog.info(LABEL_LOG, "%{public}s", "update");
            DataAbilityHelper.creator(this).notifyChange(uri);
            return index;
        } else if (SHOPPING_CART.equals(lastPath)) {

            RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, SHOPPING_CART);
            int index = rdbStore.update(value, rdbPredicates);
            HiLog.info(LABEL_LOG, "%{public}s", "update");
            DataAbilityHelper.creator(this).notifyChange(uri);
            return index;
        }

        return -1;

    }
}