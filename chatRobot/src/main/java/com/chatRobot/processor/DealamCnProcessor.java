package com.chatRobot.processor;


import com.chatRobot.dao.CommonDao;
import com.chatRobot.model.Article;
import com.chatRobot.utils.ReduceHtmlText;
import com.chatRobot.utils.StringTrim;
import org.apache.commons.lang.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/10/30. 抓取 找丢网 的文章信息
 */
public class DealamCnProcessor implements PageProcessor {

    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    private static final String INDEX_URL = "http://cn.dealam.com";
    private static final String DESC_URL = "http://cn.dealam.com/deal/.*.html";


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
            if(articleTitle.contains("原价")){
                int startIndex = articleTitle.indexOf("$");
                int endIndex = articleTitle.indexOf("原");
                articleSubTitle = articleTitle.substring(startIndex,endIndex);
            }else{
                articleSubTitle = "";
            }
            String updateTime = page.getHtml().xpath("//div[@class='floatl editor_panel']/div[2]/b/text()").toString();
            String articleAuthor = page.getHtml().xpath("//div[@class='floatl editor_panel']/div[@class='a_name']/text()").toString();
            String articleTag1 = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("//ul[@class='tag-list']").toString()).replaceAll("\\d+", "").trim().replaceAll("标签：","").trim();
            String articleTag2 = page.getHtml().xpath("//ul[@class='deal_list_head']/li/a[@class='item']/text()").toString();
            String articleTag = null;
            if(StringUtils.isNotEmpty(articleTag1)){
                String[] split = articleTag1.split(" ");
                StringBuffer sb = new StringBuffer();
                if(split.length!=1){
                    for (String s : split) {
                        String s1 = s.replaceAll("[()+=|{}']", "");
                        if(StringUtils.isNotEmpty(s1)){
                            if(s1.length()!=1){
                                sb.append(s+",");
                            }
                        }
                    }
                    articleTag = sb.toString();
                }else{
                    articleTag = articleTag1;
                }
            }else if(StringUtils.isNotEmpty(articleTag2)){
                articleTag = articleTag2;
            }else{
                articleTag = "";
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
            article.setArticleLanguage(Article.LANGUAGES_ZH_CN);
            article.setArticleArea(Article.AREA_CHINA);
            article.setArticleAreaType(Article.AREA_TYPE_CHINA);
            article.setArticleType(Article.ARTICLE_TYPE_C_DEAL);
            article.setArticleLanguageType(Article.ARTICLE_LANGUAGE_TYPE_C_ZH_CN);
            article.setWebSiteType(Article.WEB_TYPE_DEALSCN);

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