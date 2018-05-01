package com.chatRobot.dao;

import com.chatRobot.model.Article;

import com.github.abel533.mapper.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/3.
 */
public interface IArticleDao {
    /**
     * 添加文章
     * @param article
     */
    void addArticle(Article article);

    /**
     * 列表显示所有文章
     * @return
     */
    List<Article> showAll();

    /**
     * 更新文章
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 根据id删除文章
     * @param id
     */
    void delete(int id);

    /**
     * 查询网站类型文章
     * @param articleType
     * @return
     */
    List<Article> showArticle(int articleType);

    /**
     * 发布文章
     * @param articleId
     */
    Article issueArticle(int articleId);

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<Article> queryPageArticle(Map<String, Object> map);

    /**
     * 查询属于这个文章类型总数
     * @param webSiteType
     * @return
     */
    int queryArticleCount(int webSiteType);

    /**
     * 根据文章标题查询文章列表
     * @param articleTitle
     * @return
     */
    List<Article> queryArticleTitle(String articleTitle);

    List<Article> queryPageIssueArticle(Map<String, Object> map);

    int queryIssueArticleCount(Map<String, Object> demap);

    /**
     * 批量删除
     * @param ids
     */
    void batchDalete(int[] ids);

    /**
     * 根据时间删除文章(让每天的数据都是最新的)
     * @param collarTime
     */
    void deleteAll(String collarTime);

    /**
     * 定时删除数据
     */
    void deleteTime();
}
