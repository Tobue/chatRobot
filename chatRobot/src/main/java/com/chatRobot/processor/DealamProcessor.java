package com.chatRobot.processor;


import com.chatRobot.dao.CommonDao;
import com.chatRobot.model.Article;
import com.chatRobot.utils.ReduceHtmlText;
import org.apache.commons.lang.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30. 抓取 dealam 的文章信息
 */
public class DealamProcessor implements PageProcessor {

    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    private static final String INDEX_URL = "http://www.dealam.com";
    private static final String DESC_URL = "http://www.dealam.com/deal/.*.html";


    public void process(Page page) {
        String pageUrl = page.getUrl().toString();
        if(pageUrl.contains(INDEX_URL) && !pageUrl.contains("/deal/")){
            List<String> links = page.getHtml().links().regex(DESC_URL).all();
            HashSet h = new HashSet(links);
            links.clear();
            links.addAll(h);
            page.addTargetRequests(links);
        }else{
            String articleLink = page.getUrl().toString();
            String articleTitle = page.getHtml().xpath("//div[@class='title_pane']/h1[@class='deal-list-title']/a/@title").toString().trim();
            String articleSubTitle = null;
            if(articleTitle.contains("was")){
                int startIndex = articleTitle.indexOf("$");
                int endIndex = articleTitle.indexOf("was");
                articleSubTitle = articleTitle.substring(startIndex,endIndex);
            }else{
                articleSubTitle = "";
            }
            String updateTime = page.getHtml().xpath("//div[@class='ext_store_left'][2]/div[@class='ext_store_left_item2']/text()").toString();
            if(StringUtils.isEmpty(updateTime)){
                updateTime = "";
            }
            String articleAuthor = "";
            String articleTag1 = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("//ul[@class='deal_list_head']").toString()).trim();
            String[] split = articleTag1.split(" ");
            StringBuffer articleTag = new StringBuffer();
            List<String> list = new ArrayList<>();
            for (String s : split) {
                if(StringUtils.isNotEmpty(s)){
                    list.add(s);
                }
            }
            HashSet h =  new HashSet(list);
            list.clear();
            list.addAll(h);
            for (String s : list) {
                if(s.length()!=1){
                    articleTag.append(s + ",");
                }
            }

            String articleImage = page.getHtml().xpath("//table[@class='d_img']/tbody/tr[1]/td[@class='img_bdr']/a/img/@src").toString();
            String showBuyLink = INDEX_URL+page.getHtml().xpath("//table[@class='d_img']/tbody/tr[2]/td/a/@href").toString()+"?stat=2.3.21.x1";
            String articleContent = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("div[@class='title_and_body']").toString()).trim()+"<p/>"+"<br/>";

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
            article.setWebSiteType(Article.WEB_TYPE_DEALAM);

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