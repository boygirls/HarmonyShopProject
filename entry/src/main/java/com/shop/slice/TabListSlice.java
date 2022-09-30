package com.shop.slice;

import com.shop.ResourceTable;

import com.shop.TabListProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.PageSlider;
import ohos.agp.components.TabList;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;


import java.util.ArrayList;

public class TabListSlice extends AbilitySlice {

    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");
    private TabList tabList;
    private PageSlider pageSlider;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_tab_lists);
        initComponent();
        SelectedListener();

        // 设置默认起始页
        tabList.selectTabAt(0);

        HiLog.warn(LABEL, "Failed to visit %{private}s, reason:%{public}d.");
    }

    private void initComponent() {
        pageSlider = (PageSlider) findComponentById(ResourceTable.Id_page_slider);
        tabList = (TabList) findComponentById(ResourceTable.Id_tab_list);
        initTab();
        initPageSlider();
    }

    private void initPageSlider() {
        ArrayList<Integer> layoutFileIds = new ArrayList<>();
        layoutFileIds.add(ResourceTable.Layout_ability_home);
        layoutFileIds.add(ResourceTable.Layout_ability_type);
        layoutFileIds.add(ResourceTable.Layout_ability_community);
        layoutFileIds.add(ResourceTable.Layout_ability_shopcart);
        layoutFileIds.add(ResourceTable.Layout_ability_user);
        pageSlider.setProvider(new TabListProvider(layoutFileIds,this));

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
        }
    }

    private TabList.Tab createTab(String text) {
        TabList.Tab tab = tabList.new Tab(this);
        tab.setText(text);
        tab.setMinWidth(64);
        tab.setPadding(12, 0, 12, 0);
        return tab;
    }
    private void SelectedListener() {
        tabList.addTabSelectedListener(new TabList.TabSelectedListener() {
            @Override
            public void onSelected(TabList.Tab tab) {
                // 获取点的菜单的索引
                int position = tab.getPosition();
                pageSlider.setCurrentPage(position);
            }

            @Override
            public void onUnselected(TabList.Tab tab) {
            }

            @Override
            public void onReselected(TabList.Tab tab) {
            }
        });
        pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
            @Override
            public void onPageSliding(int i, float v, int i1) {

            }

            @Override
            public void onPageSlideStateChanged(int i) {

            }

            @Override
            public void onPageChosen(int i) {
                // 参数i表示当前pageSlider的索引
                if(tabList.getSelectedTabIndex() != i){
                    tabList.selectTabAt(i);
                }
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
