package com.chatRobot.model;

/**
 * Created by Administrator on 2017/11/22.商品实体类
 */
public class Product {
    private int productId;
    private String crawlerTime;
    private String productTitle;
    private String productPrice;
    private String productImage;
    private String productLink;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCrawlerTime() {
        return crawlerTime;
    }

    public void setCrawlerTime(String crawlerTime) {
        this.crawlerTime = crawlerTime;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", crawlerTime='" + crawlerTime + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productLink='" + productLink + '\'' +
                '}';
    }
}
