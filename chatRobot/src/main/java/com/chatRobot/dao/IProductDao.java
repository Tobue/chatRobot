package com.chatRobot.dao;

import com.chatRobot.model.Product;

/**
 * Created by Administrator on 2017/11/22.
 */
public interface IProductDao {
    /**
     * 添加商品
     * @param product
     */
    void addProduct(Product product);
}
