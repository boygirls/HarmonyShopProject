package com.shop.slice;

import com.shop.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TabList;
import ohos.agp.components.Text;

public class TabListSlice extends AbilitySlice {
    private TabList tabList;
    //private Text tabContent;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_tab_list);
        initComponent();
        addTabSelectedListener();
    }
    private void initComponent() {
        //tabContent = (Text) findComponentById(ResourceTable.Id_tab_content);
        tabList = (TabList) findComponentById(ResourceTable.Id_tab_list);
        initTab();
    }

    private void initTab() {
        if (tabList.getTabCount() == 0) {
            tabList.addTab(createTab("首页"));
            tabList.addTab(createTab("分类"));
            tabList.addTab(createTab("社区"));
            tabList.addTab(createTab("购物车"));
            tabList.addTab(createTab("我的"));

            tabList.setFixedMode(true);
            tabList.getTabAt(0).select();
            //tabContent.setText("Select the " + tabList.getTabAt(0).getText());
        }
    }

    private TabList.Tab createTab(String text) {
        TabList.Tab tab = tabList.new Tab(this);
        tab.setText(text);
        tab.setMinWidth(64);
        tab.setPadding(12, 0, 12, 0);
        return tab;
    }
    private void addTabSelectedListener() {
        tabList.addTabSelectedListener(new TabList.TabSelectedListener() {
            @Override
            public void onSelected(TabList.Tab tab) {
               // tabContent.setText("Select the " + tab.getText());
            }

            @Override
            public void onUnselected(TabList.Tab tab) {
            }

            @Override
            public void onReselected(TabList.Tab tab) {
            }
        });
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
