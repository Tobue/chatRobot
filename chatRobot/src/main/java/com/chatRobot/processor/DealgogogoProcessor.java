package com.chatRobot.processor;


import com.chatRobot.dao.CommonDao;
import com.chatRobot.model.Article;
import com.chatRobot.utils.ReduceHtmlText;
import com.chatRobot.utils.TagUtil;
import org.apache.commons.lang.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30. 抓取 dealgogogo 的文章信息
 */
public class DealgogogoProcessor implements PageProcessor {

    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    private static final String INDEX_URL = "http://dealgogogo.com";
    private static final String DESC_URL = "http://dealgogogo.com/goods/goodsproduct/goodID/.*.html";


    public void process(Page page) {
        String pageUrl = page.getUrl().toString();
        if(pageUrl.contains(INDEX_URL) && !pageUrl.contains("goods/goodsproduct/goodID")){
            List<String> links = page.getHtml().links().regex(DESC_URL).all();
            HashSet h = new HashSet(links);
            links.clear();
            links.addAll(h);
            page.addTargetRequests(links);
        }else{
            String articleLink = page.getUrl().toString();
            String articleTitle = page.getHtml().xpath("//h1/a[@class='event_ga_statistics event_statics_action']/span[2]/text()").toString().trim();
            String articleSubTitle = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("//h1/a[@class='event_ga_statistics event_statics_action']/span[@class='notice_item']").toString()).trim();
            String updateTime = page.getHtml().xpath("//div[@class='minfor event_statistics ga_event_statistics_content']/p[3]/text()").toString();
            if(StringUtils.isEmpty(updateTime)){
                updateTime = "";
            }
            String articleAuthor = "dealgogogo";

            String articleTag1 = page.getHtml().xpath("//div[@class='secend_area clearfix']/div[@class='mbx']/a[2]/text()").toString();
            String articleTag = null;
            if(articleTag1.contains("At Home")){
                articleTag = articleTag1.replaceAll("At Home","Home");
            }else{
                articleTag = articleTag1;
            }
            String articleImage1 = page.getHtml().xpath("//div[@class='img_wrap']/a[@class='event_ga_statistics event_statics_action']/img/@src").toString();
            String articleImage = null;
            if(articleImage1.contains("Public/sellerimg")){
                articleImage = INDEX_URL + articleImage1;
            }else{
                articleImage = articleImage1;
            }
            String showBuyLink = page.getHtml().xpath("//div[@class='mpic']/div[@class='buy']/a[@class='event_ga_statistics event_statics_action']/@href").toString();
            String articleContent = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("//div[@class='minfor event_statistics ga_event_statistics_content']").toString()).trim()+"<p/>"+"<br/>";

            Article article = new Article();
            article.setArticleTitle(articleTitle);
            article.setArticleSubTitle(String.valueOf(articleSubTitle));
            article.setArticleAuthor(articleAuthor);
            article.setUpdateTime(updateTime);
            article.setArticleImage(articleImage);
            article.setArticleTag(String.valueOf(articleTag));
            article.setArticleLink(articleLink);
            article.setShopBuyLink(showBuyLink);
            article.setArticleContent(articleContent);
            article.setArticleLanguage(Article.LANGUAGES_US);
            article.setArticleArea(Article.AREA_America);
            article.setArticleAreaType(Article.AREA_TYPE_AMERICA);
            article.setArticleType(Article.ARTICLE_TYPE_C_DEAL);
            article.setArticleLanguageType(Article.ARTICLE_LANGUAGE_TYPE_C_US);
            article.setWebSiteType(Article.WEB_TYPE_DEALGOGOGO);

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(currentTime);
            article.setCollarTime(date);
            List<Article> articleList = new CommonDao().queryArticle(articleTitle);
            if(0 == articleList.size()){
                    new CommonDao().addArticle(article);
            }
        }
    }

    public Site getSite() {
        return site;
    }

}