package com.chatRobot.processor;


import com.chatRobot.dao.CommonDao;
import com.chatRobot.model.Article;
import com.chatRobot.utils.HttpUtils;
import com.chatRobot.utils.ReduceHtmlText;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30. 抓取 买个便宜货 的文章信息
 */
public class MgpyhProcessor implements PageProcessor {


    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    private static final String INDEX_URL = "https://www.mgpyh.com";
    private static final String DESC_URL = "https://www.mgpyh\\.com\\/recommend/.*\\d";

    public void process(Page page) {
        String pageUrl = page.getUrl().toString();
        //列表
        if(pageUrl.contains(INDEX_URL) && !pageUrl.contains("https://www.mgpyh.com/recommend/")){
            List<String> links = page.getHtml().links().regex(DESC_URL).all();
            //去除重复的url
            HashSet h = new HashSet(links);
            links.clear();
            links.addAll(h);
            page.addTargetRequests(links);

        }else{ //商品详情页
            String articleLink = page.getUrl().toString();
            String articleTitle = page.getHtml().xpath("//div[@class='post-info-title']/span/text()").toString().trim();
            String articleSubTitle = page.getHtml().xpath("//div[@class='post-info-title']/span/em[@class='number']/text()").toString().trim();
            List<String> all = page.getHtml().xpath("//div[@class='post-info']/ul[@class='info']/li/span/text()").all();
            String articleAuthor = "爆料人:" + all.get(0);
            String updateTime = all.get(1);
            String articleTag = page.getHtml().xpath("//div[@class='post-info']/ul[@class='info']/li/span/a/text()").toString();
            String articleImage = page.getHtml().xpath("//div[@class='post-head']/img/@src").toString();
            String showBuyLink = INDEX_URL + page.getHtml().xpath("//div[@class='post-info']/div[@class='action']/a/@href").toString();
            String articleContent = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("//div[@class='post-content img-max-size']").toString()) + "<p/>" + "<br/>";

            Article article = new Article();
            article.setArticleTitle(articleTitle);
            article.setArticleSubTitle(articleSubTitle);
            article.setArticleAuthor(articleAuthor);
            article.setUpdateTime(updateTime);
            article.setArticleImage(articleImage);
            article.setArticleTag(articleTag);
            article.setArticleLink(articleLink);
            article.setShopBuyLink(showBuyLink);
            article.setArticleContent(articleContent);
            article.setArticleLanguage(Article.LANGUAGES_ZH_CN);
            article.setArticleArea(Article.AREA_CHINA);
            article.setArticleAreaType(Article.AREA_TYPE_CHINA);
            article.setArticleType(Article.ARTICLE_TYPE_C_DEAL);
            article.setArticleLanguageType(Article.ARTICLE_LANGUAGE_TYPE_C_ZH_CN);
            article.setWebSiteType(Article.WEB_TYPE_MGPYH);

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(currentTime);
            article.setCollarTime(date);

            //根据articleTitle查询数据库有没有这类的文章
            List<Article> articleList = new CommonDao().queryArticle(articleTitle);
            if(0 == articleList.size() && !updateTime.contains("日") && !updateTime.contains("周") && !updateTime.contains("月") && !updateTime.contains("年")){
                //没有文章,往数据库存
                new CommonDao().addArticle(article);
            }
        }
    }

    public Site getSite() {
        return site;
    }
}