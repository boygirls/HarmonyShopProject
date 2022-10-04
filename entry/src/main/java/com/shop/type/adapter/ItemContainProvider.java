package com.shop.type.adapter;

import com.shop.ResourceTable;
import com.shop.type.model.ContainItem;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;

import java.util.List;

public class ItemContainProvider extends BaseItemProvider {

    private List<ContainItem> list;
    private AbilitySlice slice;

    public ItemContainProvider(List<ContainItem> list, AbilitySlice slice) {
        this.list = list;
        this.slice = slice;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }
    @Override
    public Object getItem(int position) {
        if (list != null && position >= 0 && position < list.size()){
            return list.get(position);
        }
        return null;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public Component getComponent(int position, Component convertComponent, ComponentContainer componentContainer) {
        DirectionalLayout cpt = (DirectionalLayout) LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_type_text, null, false);
        ContainItem sampleItem = list.get(position);
        Text text = (Text) cpt.findComponentById(ResourceTable.Id_item_index);
        text.setText(sampleItem.getName());
        return cpt;
    }
}
