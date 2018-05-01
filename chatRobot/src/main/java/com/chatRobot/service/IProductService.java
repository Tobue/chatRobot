package com.chatRobot.service;

import com.chatRobot.model.Product;

/**
 * Created by Administrator on 2017/11/22.
 */
public interface IProductService {
    /**
     * 添加商品
     * @param product
     */
    void addProduct(Product product);
}
