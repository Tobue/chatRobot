package com.chatRobot.processor;
import com.chatRobot.dao.CommonDao;
import com.chatRobot.model.Article;
import com.chatRobot.utils.ChromeDriver;
import com.chatRobot.utils.DownloadImage;
import com.chatRobot.utils.ReduceHtmlText;
import org.springframework.stereotype.Controller;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.抓取 什么值得买 的文章信息
 */
@Controller
public class SmzdmProcessor implements PageProcessor {
    private static final int UPDATETIME_LENGTH = 5;

    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    private static final String INDEX_URL = "https://www.smzdm.com";
    private static final String DESC_URL = "https://www.smzdm\\.com\\/p/.*/";

    public void process(Page page) {
        String pageUrl = page.getUrl().toString();

        if(pageUrl.contains(INDEX_URL) && !pageUrl.contains("https://www.smzdm.com/p/")){
            List<String> links = page.getHtml().links().regex(DESC_URL).all();
            HashSet h = new HashSet(links);
            links.clear();
            links.addAll(h);
            page.addTargetRequests(links);
        }else{
            String articleLink = page.getUrl().toString();
            String articleImage = String.valueOf(page.getHtml().xpath("//section[@class='wrap']/div[@class='leftWrap']/article[@class='article-details']/div[@class='article-top-box clearfix']/a/img/@src").toString());
            String articleTitle = String.valueOf(page.getHtml().xpath("//section[@class='wrap']/div[@class='leftWrap']/article[@class='article-details']/div[@class='article-top-box clearfix']/a/img/@alt").toString()).trim();
            String articleSubTitle1 = page.getHtml().xpath("//section[@class='wrap']/div[@class='leftWrap']/article[@class='article-details']/div[@class='article-top-box clearfix']/div[@class='article-right']/h1[@class='article_title']/em/span[@class='red']/text()").toString();
            String articleSubTitle = articleSubTitle1.substring(3);
            String articleAuthor1 = String.valueOf(page.getHtml().xpath("//span[@class='ellipsis author']/text()").toString());
            String articleAuthor2 = page.getHtml().xpath("//span[@class='ellipsis author']/a/text()").toString();
            String articleAuthor = "";
            if(articleAuthor2==null){
                articleAuthor = articleAuthor1;
            }else{
                articleAuthor = articleAuthor1 + articleAuthor2;
            }

            List<String> all = page.getHtml().xpath("//div[@class='article_meta']/span/text()").all();
            String updateTime = all.get(1).replaceAll("更新时间：","");

            String articleContent = "";
            List<String> stringList = page.getHtml().xpath("//div[@class='item-box item-preferential']/div").all();
            String c1 = stringList.get(0);
            if(c1.contains("baoliao-block news_content")){
                String dd = page.getHtml().xpath("//div[@class='baoliao-block news_content']/p/a/text()").toString();
                StringBuffer sb = new StringBuffer();
                List<String> deList = page.getHtml().xpath("//div[@class='baoliao-block news_content']/p/text()").all();
                for (String s : deList) {
                    sb.append(s + "<p/>" + "<br/>");
                }
                articleContent = dd + "<p/>" + "<br/>" + sb + "<p/>" + "<br/>";
            }else{
                StringBuffer content_1 = new StringBuffer();
                List<String> pList = page.getHtml().xpath("//div[@class='baoliao-block']/p/text()").all();
                for (String sd : pList) {
                    content_1.append(sd + "<p/>" + "<br/>");
                }
                String s = page.getHtml().xpath("//div[@class='baoliao-block news_content']/p").toString();
                String content_2 = ReduceHtmlText.removeHtmlTag(s);
                articleContent = content_1 + "<p/>" + "<br/>" + content_2 + "<p/>" + "<br/>";
            }


            String showBuyLink = String.valueOf(page.getHtml().xpath("//section[@class='wrap']/div[@class='leftWrap']/article[@class='article-details']/div[@class='article-top-box clearfix']/div[@class='article-right']/div[@class='article-meta-box']/div[@class='clearfix article_link no-gonglue']/div[@class='buy']/a/@href").toString());
            List<String> articleTagAll = page.getHtml().xpath("//div[@class='meta-tags']/a/text()").all();
            StringBuffer sb = new StringBuffer();
            for (String s : articleTagAll) {
                String s1 = ReduceHtmlText.removeHtmlTag(s);
                sb.append(s1+",");
            }

            Article article = new Article();
            article.setArticleTitle(articleTitle);
            article.setArticleSubTitle(articleSubTitle);
            article.setArticleAuthor(articleAuthor);
            article.setUpdateTime(updateTime);
            article.setArticleImage(articleImage);
            article.setArticleTag(String.valueOf(sb));
            article.setArticleLink(articleLink);
            article.setShopBuyLink(showBuyLink);
            article.setArticleContent(articleContent);
            article.setArticleLanguage(Article.LANGUAGES_ZH_CN);
            article.setArticleArea(Article.AREA_CHINA);
            article.setArticleAreaType(Article.AREA_TYPE_CHINA);
            article.setArticleType(Article.ARTICLE_TYPE_C_DEAL);
            article.setArticleLanguageType(Article.ARTICLE_LANGUAGE_TYPE_C_ZH_CN);
            article.setWebSiteType(Article.WEB_TYPE_SMZDM);
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(currentTime);
            article.setCollarTime(date);
            List<Article> articleList = new CommonDao().queryArticle(articleTitle);
            if(updateTime.length() == UPDATETIME_LENGTH && articleList.size()==0){
                new CommonDao().addArticle(article);
            }
        }
    }

    public Site getSite() {
        return site;
    }

}