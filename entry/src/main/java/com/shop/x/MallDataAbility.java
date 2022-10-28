package com.shop.x;

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
    private static final String SHOPPING_CART = "ShopCart";

    private RdbStore rdbStore;

    private StoreConfig config = StoreConfig.newDefaultConfig("Mall.db");
    private RdbOpenCallback openCallback = new RdbOpenCallback() {
        @Override
        public void onCreate(RdbStore rdbStore) {
            //如数据表不存在需创建数据表
            rdbStore.executeSql("create table if not exists mall_info(id integer primary key autoincrement,key text not null unique,value text not null)");
            rdbStore.executeSql("create table if not exists user(" +
                    "userId integer primary key autoincrement," +
                    "username text not null, " +
                    "password text not null, " +
                    "token text not null)");

            rdbStore.executeSql("create table if not exists ShopCart(" +
                    "cartId integer primary key autoincrement, " +
                    "userId text not null, " +
                    "token text not null, " +
                    "productId text not null, " +
                    "cartNum text not null, " +
                    "productPrice text not null, " +
                    "productName text not null, " +
                    "productImg text not null, " +
                    "originalPrice text not null, " +
                    "sellPrice text not null)");
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

//            OrmPredicates ormPredicates = DataAbilityUtils.createOrmPredicates(predicates, user.class);
//            ResultSet resultSet = OrmContext.query(ormPredicates,columns);
//                if(resultSet == null){
//                    HiLog.info(LABEL_LOG,"resultSet is null");
//                }

            RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, USER);
            ResultSet resultSet = rdbStore.query(rdbPredicates, columns);
            return resultSet;

        } else if (SHOPPING_CART.equals(lastPath)) {

            RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, SHOPPING_CART);
            ResultSet resultSet = rdbStore.query(rdbPredicates, columns);
            return resultSet;
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

            int index = (int) rdbStore.insert(USER, value);

            //DataAbilityHelper.creator(this).notifyChange(uri);

            return index;
        }else if (SHOPPING_CART.equals(lastPath)) {

            int index = (int) rdbStore.insert(SHOPPING_CART, value);

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