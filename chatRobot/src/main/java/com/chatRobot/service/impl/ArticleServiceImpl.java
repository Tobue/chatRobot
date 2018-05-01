package com.chatRobot.service.impl;

import com.chatRobot.dao.IArticleDao;
import com.chatRobot.model.Article;
import com.chatRobot.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/3.
 */
@Service
public class ArticleServiceImpl implements IArticleService{


    @Autowired
    private IArticleDao iArticleDao;

    /**
     * 添加文章
     * @param article
     */
    @Override
    public void addArticle(Article article){
       iArticleDao.addArticle(article);
    }

    /**
     *添加爬虫文章
     * @param article
     */
    public void addByArticle(Article article) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        IArticleDao bean = applicationContext.getBean(IArticleDao.class);
        bean.addArticle(article);
    }

    /**
     * 列表显示所有文章
     * @return
     */
    @Override
    public List<Article> showAll() {
        return iArticleDao.showAll();
    }

    /**
     * 更新文章
     * @param article
     */
    @Override
    public void updateArticle(Article article) {
        iArticleDao.updateArticle(article);
    }

    /**
     * 根据id删除文章
     * @param id
     */
    @Override
    public void delete(int id) {
        iArticleDao.delete(id);
    }

    /**
     * 查询网站类型文章
     * @param articleType
     * @return
     */
    @Override
    public List<Article> showArticle(int articleType) {
        return iArticleDao.showArticle(articleType);
    }

    /**
     * 发布文章
     * @param articleId
     */
    @Override
    public Article issueArticle(int articleId) {
       return iArticleDao.issueArticle(articleId);
    }

    /**
     * 分页查询
     * @param map
     * @return
     */
    @Override
    public List<Article> queryPageArticle(Map<String, Object> map) {
        return iArticleDao.queryPageArticle(map);
    }

    /**
     * 查询属于这个文章类型总数
     * @return
     */
    @Override
    public int queryArticleCount(int webSiteType) {
        return iArticleDao.queryArticleCount(webSiteType);
    }

    /**
     * 根据文章标题查询文章列表
     * @param articleTitle
     * @return
     */
    @Override
    public List<Article> queryArticleTitle(String articleTitle) {
        return iArticleDao.queryArticleTitle(articleTitle);
    }

    @Override
    public List<Article> queryPageIssueArticle(Map<String, Object> map) {
        return iArticleDao.queryPageIssueArticle(map);
    }

    @Override
    public int queryIssueArticleCount(Map<String, Object> demap) {
        return iArticleDao.queryIssueArticleCount(demap);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void batchDalete(int[] ids) {
        iArticleDao.batchDalete(ids);
    }

    /**
     * 根据时间删除过期文章
     * @param collarTime
     */
    @Override
    public void deleteAll(String collarTime) {
         iArticleDao.deleteAll(collarTime);
    }

    /**
     * 定时删除数据
     */
    @Override
    public void deleteTime() {
        iArticleDao.deleteTime();
    }

    /**
     * 根据文章标题查询文章列表(爬虫这边的数据处理)
     * @param articleTitle
     * @return
     */
    public List<Article> queryByArticleTitle(String articleTitle) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        IArticleDao bean = applicationContext.getBean(IArticleDao.class);
        return bean.queryArticleTitle(articleTitle);
    }
}
