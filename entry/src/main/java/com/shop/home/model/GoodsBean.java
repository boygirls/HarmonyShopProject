package com.shop.home.model;

import java.io.Serializable;

/**
 * 作用：商品对象
 */
public class GoodsBean implements Serializable {
    //价格
    private String cover_price;
    //图片
    private String figure;
    //名称
    private String name;
    //产品id
    private String product_id;

    private int number = 1;

    /**
     * 是否处于编辑状态
     */
    private boolean isEditing;
    /**
     * 是否被选中
     */
    private boolean isChildSelected;

    public GoodsBean() {
    }

    public GoodsBean(String name, String cover_price, String figure, String product_id) {
        this.name = name;
        this.cover_price = cover_price;
        this.figure = figure;
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover_price() {
        cover_price.substring(0, cover_price.length() - 1);
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setIsEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }

    public boolean isChildSelected() {
        return isChildSelected;
    }

    public void setIsChildSelected(boolean isChildSelected) {
        this.isChildSelected = isChildSelected;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "name='" + name + '\'' +
                ", cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", product_id='" + product_id + '\'' +
                ", number=" + number +
                ", isEditing=" + isEditing +
                ", isChildSelected=" + isChildSelected +
                '}';
    }
}
