package com.shop.home.model;

public class Shopcart {

    private int cartId;
    private String productId;
    private String skuId;
    private String userId;
    private String cartNum;
    private String cartTime;
    private double productPrice;
    private String skuProps;
    private String productName;
    private String productImg;
    private double originalPrice;
    private double sellPrice;
    private String skuName;
    private int skuStock;

    public Shopcart() {
    }

    public Shopcart(int cartId, String productId, String skuId, String userId, String cartNum, String cartTime, double productPrice, String skuProps, String productName, String productImg, double originalPrice, double sellPrice, String skuName, int skuStock) {
        this.cartId = cartId;
        this.productId = productId;
        this.skuId = skuId;
        this.userId = userId;
        this.cartNum = cartNum;
        this.cartTime = cartTime;
        this.productPrice = productPrice;
        this.skuProps = skuProps;
        this.productName = productName;
        this.productImg = productImg;
        this.originalPrice = originalPrice;
        this.sellPrice = sellPrice;
        this.skuName = skuName;
        this.skuStock = skuStock;
    }

    @Override
    public String toString() {
        return "Shopcart{" +
                "cartId=" + cartId +
                ", productId='" + productId + '\'' +
                ", skuId='" + skuId + '\'' +
                ", userId='" + userId + '\'' +
                ", cartNum='" + cartNum + '\'' +
                ", cartTime='" + cartTime + '\'' +
                ", productPrice=" + productPrice +
                ", skuProps='" + skuProps + '\'' +
                ", productName='" + productName + '\'' +
                ", productImg='" + productImg + '\'' +
                ", originalPrice=" + originalPrice +
                ", sellPrice=" + sellPrice +
                ", skuName='" + skuName + '\'' +
                ", skuStock=" + skuStock +
                '}';
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

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCartNum() {
        return cartNum;
    }

    public void setCartNum(String cartNum) {
        this.cartNum = cartNum;
    }

    public String getCartTime() {
        return cartTime;
    }

    public void setCartTime(String cartTime) {
        this.cartTime = cartTime;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getSkuProps() {
        return skuProps;
    }

    public void setSkuProps(String skuProps) {
        this.skuProps = skuProps;
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

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public int getSkuStock() {
        return skuStock;
    }

    public void setSkuStock(int skuStock) {
        this.skuStock = skuStock;
    }

}
