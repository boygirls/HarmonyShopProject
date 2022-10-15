package com.shop.slice;

import com.google.gson.Gson;
import com.shop.ResourceTable;
import com.shop.home.model.ResultBeanData;
import com.shop.home.model.Shopcart;
import com.shop.util.Constants;
import com.shop.util.DataBaseUtil;
import com.shop.util.LoadUrlImageUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class AddAbilitySlice extends AbilitySlice {

    private Gson gson = new Gson();

//    String selectSkuId = "";//用来记录选择套餐的id
//    double selectSkuPrice = 0.0;//用来记录选择套餐的价格

    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");

    private ResultBeanData.ResultBean.HotInfoBean product;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_detail_add);

//        String productId = (String) intent.getParams().getParam("productId");

        product = (ResultBeanData.ResultBean.HotInfoBean) intent.getParams().getParam("product");

        HiLog.info(LABEL, "" + product);

        initPage();

        //监听购物车修改数量的按钮
        Button btn1 = (Button) findComponentById(ResourceTable.Id_num_btn1);
        Button btn2 = (Button) findComponentById(ResourceTable.Id_num_btn2);
        Text numText = (Text) findComponentById(ResourceTable.Id_num_text);
        Component.ClickedListener listener = new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                Button btn = (Button) component;
                int num = Integer.parseInt(numText.getText());
                if(btn == btn1){
                    num = num>1?num-1:num;
                }else if (btn == btn2){
                    num = num + 1;
                }
                numText.setText(num+"");
            }
        };
        btn1.setClickedListener(listener);
        btn2.setClickedListener(listener);

        Button addBtn = (Button) findComponentById(ResourceTable.Id_shopcart_add_button);
        addBtn.setClickedListener(component -> {

            //收集数据
//            User user1 = new User();
//            user1.setId(1);
//            user1.setToken("123");
//            User token = DataBaseUtil.getUser(user1,this);
//            User userId = DataBaseUtil.getUser(user1,this);
            String num = numText.getText();
            double price = Double.parseDouble(product.getCover_price());

            Shopcart shopcart = new Shopcart();
            shopcart.setUserId(1);
            shopcart.setProductId(product.getProduct_id());
            shopcart.setProductName(product.getName());
            shopcart.setProductImg(Constants.BASE_URl_IMAGE + product.getFigure());
            shopcart.setCartNum(num);
            shopcart.setCartId(1);
            shopcart.setProductPrice(price);

            // TODO 购物车剩余参数

            // 将数据存入表
            DataBaseUtil.setShoppingCart(shopcart,this);

            new ToastDialog(this).setText("添加购物车成功！").show();

//            getGlobalTaskDispatcher(TaskPriority.DEFAULT).asyncDispatch(()->{
//                //收集数据
//               String token = DataBaseUtil.getValue("token",this);
//               String userId = DataBaseUtil.getValue("userId",this);
//               String num = numText.getText();
//
//                Map<String , Object> params = new HashMap<>();
//                params.put("cartId",0);
//                params.put("cartNum",num);
//                params.put("productId",product);
//                params.put("productPrice",selectSkuPrice);
//                params.put("skuId",selectSkuId);
//                params.put("skuProps","");
//                params.put("userId",1);
//
//                String paramStr = gson.toJson(params);

                //提交数据
                //String urlString = "https://cn.bing.com/images/search?view=detailV2&ccid=tDXT8m%2f5&id=19CFDD45FD858956BD09FD8ABECDA1169A1C8281&thid=OIP.tDXT8m_5oTMDAzXQU2k8wQHaHa&mediaurl=https%3a%2f%2fdemosc.chinaz.net%2fFiles%2fpic%2ficons%2f5949%2fq4.png&exph=512&expw=512&q=qq&simid=608037094656079600&FORM=IRPRST&ck=47E85E4F3F8533349B8504EAE7EB0907&selectedIndex=16";
//                String s = HttpRequestUtil.sendPostRequestWithToken(this,urlString,token,paramStr);
//                ResultVO resultVO = gson.fromJson(s, ResultVO.class);
//                if(resultVO.getCode() == 10000){
//                    System.out.println("--------------->>>>>>>>添加购物车成功！");
//                }
//            });

        });

    }

    /**
     * 初始化页面
     */
    private void initPage() {
        Text price = (Text) findComponentById(ResourceTable.Id_tv_good_info_price);
        Text name = (Text) findComponentById(ResourceTable.Id_tv_good_info_name);
        Image img = (Image) findComponentById(ResourceTable.Id_tv_good_info_img);

        price.setText(product.getCover_price());
        name.setText(product.getName());
        String src = Constants.BASE_URl_IMAGE + product.getFigure();
        LoadUrlImageUtils.loadImage(this, src, img);
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
