package com.shop.slice;

import com.shop.ResourceTable;
import com.shop.home.model.ResultBeanData;
import com.shop.util.Constants;
import com.shop.util.LoadUrlImageUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class DetailAbilitySlice extends AbilitySlice {

    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");

    private ResultBeanData.ResultBean.HotInfoBean product;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        setUIContent(ResourceTable.Layout_ability_detail);
        // 根据key获取数据
        product = (ResultBeanData.ResultBean.HotInfoBean) intent.getParams().getParam("product");

        HiLog.info(LABEL, "" + product);

        initPage();
    }

    private void initPage() {
        Text price = (Text) findComponentById(ResourceTable.Id_tv_good_info_price);
        Text name = (Text) findComponentById(ResourceTable.Id_tv_good_info_name);
        Image img = (Image) findComponentById(ResourceTable.Id_tv_good_info_img);

        price.setText(product.getCover_price());
        name.setText(product.getName());
        String src = Constants.BASE_URl_IMAGE + product.getFigure();
        LoadUrlImageUtils.loadImage(this,src,img);

        // 提交按钮
        Button submit = (Button) findComponentById(ResourceTable.Id_tv_good_info_submit);

        submit.setClickedListener(component -> {
            // TODO 具体的实现逻辑

            Intent intent = new Intent();
            intent.setParam("productId",product);

            present(new AddAbilitySlice(),intent);

//            DirectionalLayout toastLayout = (DirectionalLayout) LayoutScatter.getInstance(this)
//                    .parse(ResourceTable.Layout_layout_toast, null, false);

//            new ToastDialog(getContext())
//                    .setContentCustomComponent(toastLayout)
//                    .setSize(DirectionalLayout.LayoutConfig.MATCH_CONTENT, DirectionalLayout.LayoutConfig.MATCH_CONTENT)
//                    .setAlignment(LayoutAlignment.CENTER)
//                    .show();

        });

    }
}
