package com.chatRobot.service.impl;

import com.chatRobot.dao.IProductDao;
import com.chatRobot.model.Product;
import com.chatRobot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/11/22.
 */
@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductDao iProductDao;

    /**
     * 添加商品
     * @param product
     */
    @Override
    public void addProduct(Product product) {
        iProductDao.addProduct(product);
    }

    /**
     * 添加爬虫商品
     * @param product
     */
    public void addByProduct(Product product){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        IProductDao bean = applicationContext.getBean(IProductDao.class);
        bean.addProduct(product);
    }
}
