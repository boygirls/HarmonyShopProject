package com.shop.home.adapter;

import com.shop.ResourceTable;
import com.shop.home.model.ResultBeanData;
import com.shop.util.Constants;
import com.shop.util.LoadUrlImageUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.List;

public class IndexImagePageSliderProvider extends PageSliderProvider {


    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");

    private List<String> list;

    private AbilitySlice abilitySlice;

    public IndexImagePageSliderProvider(List<String> list, AbilitySlice abilitySlice) {
        this.list = list;
        this.abilitySlice = abilitySlice;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object createPageInContainer(ComponentContainer componentContainer, int i) {

        // 获取轮播图模板
        DirectionalLayout banner = (DirectionalLayout) LayoutScatter.getInstance(abilitySlice).parse(ResourceTable.Layout_index_banner, null, false);
        Image img = (Image) banner.findComponentById(ResourceTable.Id_index_img_banner);

        // 获取轮播图数据
        String imgSrc = Constants.BASE_URl_IMAGE + list.get(i);
        // 渲染数据
        LoadUrlImageUtils.loadImage(abilitySlice, imgSrc, img);

        // 将图片模板绑定到PageSlider
        componentContainer.addComponent(banner);

        return banner;
    }

    @Override
    public void destroyPageFromContainer(ComponentContainer componentContainer, int i, Object o) {
        componentContainer.removeComponent((Component) o);
    }

    @Override
    public boolean isPageMatchToObject(Component component, Object o) {
        return true;
    }
}
