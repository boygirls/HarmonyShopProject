package com.shop;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.data.DatabaseHelper;
import ohos.data.dataability.DataAbilityUtils;
import ohos.data.rdb.*;
import ohos.data.resultset.ResultSet;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.utils.net.Uri;
import ohos.utils.PacMap;

import java.io.FileDescriptor;

public class MallDataAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");

    private RdbStore rdbStore;
    private StoreConfig config = StoreConfig.newDefaultConfig("Mall.db");
    private RdbOpenCallback openCallback = new RdbOpenCallback() {
        @Override
        public void onCreate(RdbStore rdbStore) {
            //如数据表不存在需创建数据表
            rdbStore.executeSql("create table if not exists mall_info(id integer primary key autoincrement,key text not null unique,value text not null)");
        }
        public void onUpgrade(RdbStore rdbStore, int i, int i1) {}
    };

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        HiLog.info(LABEL_LOG, "MallDataAbility onStart");
        //初始化数据库连接
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        rdbStore = databaseHelper.getRdbStore(config,1,openCallback);
    }

    @Override
    public ResultSet query(Uri uri, String[] columns, DataAbilityPredicates predicates) {
        RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, "mall_info");
        ResultSet resultSet = rdbStore.query(rdbPredicates, columns);
        return resultSet;
    }

    @Override
    public int insert(Uri uri, ValuesBucket value) {
        int i = (int) rdbStore.insert("mall_info", value);
        return i;
    }

    @Override
    public int delete(Uri uri, DataAbilityPredicates predicates) {
        RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, "mall_info");
        int i = rdbStore.delete(rdbPredicates);
        return i;
    }

    @Override
    public int update(Uri uri, ValuesBucket value, DataAbilityPredicates predicates) {
        RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, "mall_info");
        int i = rdbStore.update(value,rdbPredicates);
        return i;
    }
}