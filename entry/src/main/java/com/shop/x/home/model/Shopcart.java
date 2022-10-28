package com.shop.x.home.model;

import java.io.Serializable;

public class Shopcart implements Serializable {

    private int userId;     //用户id
    private String token;
    private int cartId;     //购物车id
    private String productId;       //产品id
    private String cartNum;     //购物车数量
    private double productPrice;        //产品价格
    private String productName;     //产品数量
    private String productImg;      //产品图片
    private double originalPrice;
    private double sellPrice;//套餐名称


    public Shopcart() {
    }

    @Override
    public String toString() {
        return "Shopcart{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                ", cartId=" + cartId +
                ", productId='" + productId + '\'' +
                ", cartNum='" + cartNum + '\'' +
                ", productPrice=" + productPrice +
                ", productName='" + productName + '\'' +
                ", productImg='" + productImg + '\'' +
                ", originalPrice=" + originalPrice +
                ", sellPrice=" + sellPrice +
                '}';
    }

    public Shopcart(int userId, String token, int cartId, String productId, String cartNum, double productPrice, String productName, String productImg, double originalPrice, double sellPrice, int skuStock) {
        this.userId = userId;
        this.token = token;
        this.cartId = cartId;
        this.productId = productId;
        this.cartNum = cartNum;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productImg = productImg;
        this.originalPrice = originalPrice;
        this.sellPrice = sellPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCartNum() {
        return cartNum;
    }

    public void setCartNum(String cartNum) {
        this.cartNum = cartNum;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

}
