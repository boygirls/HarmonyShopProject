package com.shop.slice;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.shop.ResourceTable;
import com.shop.TabListProvider;
import com.shop.home.adapter.IndexImagePageSliderProvider;
import com.shop.home.model.GoodsBean;
import com.shop.home.model.ResultBeanData;
import com.shop.type.model.TypeBean;
import com.shop.util.Constants;
import com.shop.util.LoadUrlImageUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.app.dispatcher.task.TaskPriority;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import okhttp3.Call;


import java.util.ArrayList;
import java.util.List;

public class TabListSlice extends AbilitySlice {

    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");
    private TabList tabList;
    private PageSlider pageSlider;

    // 分类页面
    private List<TypeBean.ResultBean> result;
    /**
     * 返回的数据
     */
    private ResultBeanData.ResultBean resultBean;


    // 临时数据
    private  List<GoodsBean> goodsShopCart;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_tab_lists);
        initComponent();
        SelectedListener();

        // 设置默认起始页
        tabList.selectTabAt(0);
        initIndex(pageSlider);

        initSHopcartData();
    }

    private void initSHopcartData() {

        for ( int i = 0; i < 8; i++){
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setProduct_id(i + "");
            goodsBean.setCover_price("123");
            goodsBean.setFigure("https://cn.bing.com/images/search?view=detailV2&ccid=tDXT8m%2f5&id=19CFDD45FD858956BD09FD8ABECDA1169A1C8281&thid=OIP.tDXT8m_5oTMDAzXQU2k8wQHaHa&mediaurl=https%3a%2f%2fdemosc.chinaz.net%2fFiles%2fpic%2ficons%2f5949%2fq4.png&exph=512&expw=512&q=qq&simid=608037094656079600&FORM=IRPRST&ck=47E85E4F3F8533349B8504EAE7EB0907&selectedIndex=16");
            goodsBean.setName("2");
            goodsShopCart.add(goodsBean);
        }

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
        pageSlider.setProvider(new TabListProvider(layoutFileIds, this));

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

                HiLog.info(LABEL, "" + position);
                switch (position) {
                    case 0:
                        initIndex(pageSlider);
                        break;
                    case 1:
                        initType(pageSlider);
                        break;
                    case 2:
                        initCommunity(pageSlider);
                        break;
                    case 3:
                        initShopCart(pageSlider);
                        break;
                    case 4:
                        initUser(pageSlider);
                        break;
                }

            }


            @Override
            public void onUnselected(TabList.Tab tab) {
            }

            @Override
            public void onReselected(TabList.Tab tab) {
            }
        });


        // 列表切换事件监听
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
                if (tabList.getSelectedTabIndex() != i) {
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

    // 初始化首页
    private void initIndex(PageSlider slider) {

        getGlobalTaskDispatcher(TaskPriority.DEFAULT).asyncDispatch(() -> {
            String url = Constants.HOME_URL;
            OkHttpUtils
                    .get()
                    .url(url)
                    .build()
                    .execute(new StringCallback() {
                        /**
                         * 当请求失败的时候回调
                         * @param call
                         * @param e
                         * @param id
                         */
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            HiLog.info(LABEL, "请求失败" + e.toString());
                        }

                        /**
                         * 当联网成功的时候回调
                         * @param response 请求成功的数据
                         * @param id
                         */
                        @Override
                        public void onResponse(String response, int id) {
                            //解析数据转换为Java对象
                            ResultBeanData resultBeanData = JSON.parseObject(response, ResultBeanData.class);
                            resultBean = resultBeanData.getResult();
                            // 处理请求到数据
                            processData();
                            HiLog.info(LABEL, "数据请求完成");
                        }
                    });
        });

        //DirectionalLayout componentById = (DirectionalLayout) slider.findComponentById(ResourceTable.Id_product);
//        componentById.setClickedListener(component -> {
//            Intent intent = new Intent();
//            intent.setParam("product", "2");
//            this.present(new DetailAbilitySlice(), intent);
//        });
    }


    private void initCommunity(PageSlider slider) {
    }
    /*初始化购物车列表*/
    private void initShopCart(PageSlider slider) {




        //绑定“结算”按钮的点击事件
        Button btn = (Button) pageSlider.findComponentById(ResourceTable.Id_account_button);
        btn.setClickedListener(component -> {
            Intent intent = new Intent();
            present(new OrderAbilitySlice(),intent);
        });
    }

    private void initUser(PageSlider slider) {
    }


    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品", "办公文具", "数码周边", "游戏专区"};
    /**
     * 分类页面初始化
     * @param slider
     */
    private void initType(PageSlider slider) {
        getUITaskDispatcher().asyncDispatch(() -> {
            TableLayout cont = (TableLayout) findComponentById(ResourceTable.Id_type_Container);
            for (int i = 0; i <titles.length; i++) {
                DirectionalLayout cpt = (DirectionalLayout) LayoutScatter.getInstance(this).parse(ResourceTable.Layout_type_text, null, false);
                Text text = (Text) cpt.findComponentById(ResourceTable.Id_item_index);
                text.setText(titles[i]);
                cont.addComponent(cpt);
            }
        });


        getGlobalTaskDispatcher(TaskPriority.DEFAULT).asyncDispatch(()->{
            OkHttpUtils
                    .get()
                    .url(Constants.SKIRT_URL)
                    .id(100)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int i) {
                            HiLog.info(LABEL, "请求失败" + e.toString());
                        }
                        @Override
                        public void onResponse(String response, int id) {
                            //两位请求成功
                            switch (id) {
                                case 100:
                                    if (response != null) {
                                        //解析数据
                                        Gson gson = new Gson();
                                        TypeBean typeBean = gson.fromJson(response, TypeBean.class);
                                        result = typeBean.getResult();
                                        // 处理类型页得到的数据
                                        typeProcessData();
                                    }
                                    break;
                                case 101:
                                    HiLog.info(LABEL,"type页面初始化有问题");
                                    break;
                            }
                        }
                    });
        });
    }

    /**
     * 处理类型页面得到的数据
     */
    private void typeProcessData() {
    }


    /**
     * 处理首页请求到的数据
     */
    private void processData() {

            // 轮播图
            getUITaskDispatcher().asyncDispatch(() -> {
                List<String> imageUris = new ArrayList<>();
                for (int i = 0; i < resultBean.getBanner_info().size(); i++) {
                    imageUris.add(resultBean.getBanner_info().get(i).getImage());
                }

                IndexImagePageSliderProvider indexImagePageSliderProvider = new IndexImagePageSliderProvider(imageUris, this);

                PageSlider page = (PageSlider) findComponentById(ResourceTable.Id_index_image_pageSlider);

                page.setPageSwitchTime(2000);

                page.setProvider(indexImagePageSliderProvider);
            });

            // TODO 为完成点击事件
            // 渲染分类列表
            getUITaskDispatcher().asyncDispatch(() -> {

                List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info = resultBean.getChannel_info();

                TableLayout tableLayout = (TableLayout) findComponentById(ResourceTable.Id_index_types);

                for (int i = 0; i < channel_info.size(); i++) {

                    DirectionalLayout dir = (DirectionalLayout) LayoutScatter.getInstance(this).parse(ResourceTable.Layout_index_type, null, false);

                    Image img = (Image) dir.findComponentById(ResourceTable.Id_index_channel_img);
                    Text text = (Text) dir.findComponentById(ResourceTable.Id_index_channel_text);

                    // 拼接url
                    String imgSrc = Constants.BASE_URl_IMAGE + channel_info.get(i).getImage();
                    LoadUrlImageUtils.loadImage(this, imgSrc, img);

                    text.setText(channel_info.get(i).getChannel_name());

                    tableLayout.addComponent(dir);
                }

            });

            // 加载商品数据
            getUITaskDispatcher().asyncDispatch(() -> {

                List<ResultBeanData.ResultBean.HotInfoBean> hot_info = resultBean.getHot_info();
                TableLayout product = (TableLayout) findComponentById(ResourceTable.Id_index_product_list);

                for (ResultBeanData.ResultBean.HotInfoBean item : hot_info) {
                    DirectionalLayout dir = (DirectionalLayout) LayoutScatter.getInstance(this).parse(ResourceTable.Layout_index_hot_info, null, false);

                    Text product_id = (Text) dir.findComponentById(ResourceTable.Id_index_hot_info_product_id);
                    Image img = (Image) dir.findComponentById(ResourceTable.Id_index_hot_info_img);
                    Text name = (Text) dir.findComponentById(ResourceTable.Id_index_hot_info_name);
                    Text price = (Text) dir.findComponentById(ResourceTable.Id_index_hot_info_price);

                    // 拼接url
                    String imgSrc = Constants.BASE_URl_IMAGE + item.getFigure();
                    LoadUrlImageUtils.loadImage(this, imgSrc, img);

                    product_id.setText(item.getProduct_id());

                    name.setText(item.getName());

                    price.setText("￥" + item.getCover_price());

                    product.addComponent(dir);

                }

            });

    }

}
