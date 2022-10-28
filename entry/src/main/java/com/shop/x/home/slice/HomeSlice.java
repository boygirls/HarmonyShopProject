package com.shop.x.home.slice;

import com.alibaba.fastjson.JSON;
import com.shop.x.ResourceTable;
import com.shop.x.home.model.ResultBeanData;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class HomeSlice extends AbilitySlice {
    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");
    /**
     * 返回的数据
     */
    private ResultBeanData.ResultBean resultBean;


    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_home);

        initData();
    }

    private void initData() {


    }

    /**
     * 处理数据
     *
     * @param json
     */
    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        if (resultBean != null) {
            //有数据
            //设置适配器将数据扔进视图
            HiLog.info(LABEL,resultBean.toString());


        } else {
            //没有数据
        }

    }


    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
