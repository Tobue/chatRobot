package com.chatRobot.processor;


import com.chatRobot.dao.CommonDao;
import com.chatRobot.model.Article;
import com.chatRobot.utils.ReduceHtmlText;
import com.chatRobot.utils.StringTrim;
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
 * Created by Administrator on 2017/10/30. 抓取 dealnews 的文章信息
 */
public class DealnewsProcessor implements PageProcessor {

    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    private static final String INDEX_URL = "https://www.dealnews.com";
    private static final String DESC_URL = "https://www.dealnews\\.com\\/.*.html";


    public void process(Page page) {
        String pageUrl = page.getUrl().toString();
        if(pageUrl.contains(INDEX_URL) && !pageUrl.contains(".html")){
            List<String> links = page.getHtml().links().regex(DESC_URL).all();
            HashSet h = new HashSet(links);
            links.clear();
            links.addAll(h);
            page.addTargetRequests(links);
        }else{
            String articleLink = page.getUrl().toString();
            String articleTitle = page.getHtml().xpath("//h1[@class='headline-xxlarge ']/a[@class='article-headline']/text()").toString().trim();
            String articleSubTitle = page.getHtml().xpath("//div[@class='image-container']/div[@class='price price-pop']/text()").toString().trim();
            String updateTime = page.getHtml().xpath("//div[@class='unit lastUnit']/div[@class='update-time']/time/text()").toString();
            String articleAuthor = "dealsnews";

            //这个网站没有特殊标签,把标题拆成一个一个的标签
            String articleTag = TagUtil.getTag(articleTitle);

            String articleImage = page.getHtml().xpath("//div[@class='art-image  heart-img-container']/a[1]/img/@src").toString();
            if(StringUtils.isEmpty(articleImage)){
                articleImage = INDEX_URL;
            }
            String showBuyLink = page.getHtml().xpath("//div[@class='image-container']/a[@class='button']/@href").toString();
            String articleContent1 = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("//div[@class='unit lastUnit']/div[@class='article-body']").toString()).trim();
            String articleContent = articleContent1.replaceAll("\\s{2,}", " ")+"<p/>"+"<br/>";


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
            article.setWebSiteType(Article.WEB_TYPE_DEALNEWS);

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(currentTime);
            article.setCollarTime(date);
            List<Article> articleList = new CommonDao().queryArticle(articleTitle);
            if(0 == articleList.size()&&!updateTime.contains("day")){
                if(articleImage!=INDEX_URL&&showBuyLink!=INDEX_URL){
                    new CommonDao().addArticle(article);
                }
            }
        }
    }

    public Site getSite() {
        return site;
    }
}