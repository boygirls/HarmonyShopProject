package com.shop.slice;

import com.google.gson.Gson;
import com.shop.ResourceTable;
import com.shop.home.model.ResultBeanData;
import com.shop.home.model.ResultVO;
import com.shop.util.Constants;
import com.shop.util.DataBaseUtil;
import com.shop.util.HttpRequestUtil;
import com.shop.util.LoadUrlImageUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.app.dispatcher.task.TaskPriority;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.HashMap;
import java.util.Map;

public class AddAbilitySlice extends AbilitySlice {

    private Gson gson = new Gson();

    String selectSkuId = "";//用来记录选择套餐的id
    double selectSkuPrice = 0.0;//用来记录选择套餐的价格

    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");

    private ResultBeanData.ResultBean.HotInfoBean product;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_detail_add);

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
            getGlobalTaskDispatcher(TaskPriority.DEFAULT).asyncDispatch(()->{
                //收集数据
                String token = DataBaseUtil.getValue("token",this);
                String userId = DataBaseUtil.getValue("userId",this);
                String num = numText.getText();

                Map<String , Object> params = new HashMap<>();
                params.put("cartId",0);
                params.put("cartNum",num);
                params.put("productId",product);
                params.put("productPrice",selectSkuPrice);
                params.put("skuId",selectSkuId);
                params.put("skuProps","");
                params.put("userId",userId);

                String paramStr = gson.toJson(params);

                //提交数据
                String urlString = "https://cn.bing.com/images/search?view=detailV2&ccid=tDXT8m%2f5&id=19CFDD45FD858956BD09FD8ABECDA1169A1C8281&thid=OIP.tDXT8m_5oTMDAzXQU2k8wQHaHa&mediaurl=https%3a%2f%2fdemosc.chinaz.net%2fFiles%2fpic%2ficons%2f5949%2fq4.png&exph=512&expw=512&q=qq&simid=608037094656079600&FORM=IRPRST&ck=47E85E4F3F8533349B8504EAE7EB0907&selectedIndex=16";
                String s = HttpRequestUtil.sendPostRequestWithToken(this,urlString,token,paramStr);
                ResultVO resultVO = gson.fromJson(s, ResultVO.class);
                if(resultVO.getCode() == 10000){
                    System.out.println("--------------->>>>>>>>添加购物车成功！");
                }
            });
        });

    }

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
