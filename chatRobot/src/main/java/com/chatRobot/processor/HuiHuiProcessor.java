package com.chatRobot.processor;

import com.chatRobot.dao.CommonDao;
import com.chatRobot.model.Article;
import com.chatRobot.utils.HttpUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30. 抓取 惠惠网 的文章信息
 */
public class HuiHuiProcessor implements PageProcessor {


    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(4000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    private static final String INDEX_URL = "http://www.huihui.cn";
    private static final String DESC_URL = "http://www.huihui\\.cn\\/deals/.*[0-9]\\d{7}$";



    public void process(Page page) {
        String pageUrl = page.getUrl().toString();
        //列表
        if(pageUrl.contains(INDEX_URL) && !pageUrl.contains("http://www.huihui.cn/deals")){
            List<String> links = page.getHtml().links().regex(DESC_URL).all();
            //去除重复的url
            HashSet h = new HashSet(links);
            links.clear();
            links.addAll(h);
            page.addTargetRequests(links);
        }else{ //商品详情页
            String articleLink = page.getUrl().toString();
            String articleTitle = page.getHtml().xpath("//div[@class='header1']/h1/a/text()").toString();
            if(articleTitle==null){
                articleTitle = page.getHtml().xpath("//div[@class='header2']/h1/a/text()").toString().trim();
            }

            String articleSubTitle = page.getHtml().xpath("//div[@class='header1']/h4/text()").toString();
            if(articleSubTitle==null){
                articleSubTitle = page.getHtml().xpath("div/[@class='header2']/h4/text()").toString().trim();
            }
            String articleAuthor1 = page.getHtml().xpath("//div[@class='list-info clearfix']/span[@class='title']/text()").toString();
            String articleAuthor2 = page.getHtml().xpath("//div[@class='list-info clearfix']/span[@class='hico-doc hico-faceimg']/text()").toString();
            String articleAuthor = articleAuthor1 + articleAuthor2;
            String updateTime2 = page.getHtml().xpath("//div[@class='list-info clearfix']/span[@class='hico-doc hico-date']/text()").toString();
            String updateTime =  updateTime2;

            String articleImage = page.getHtml().xpath("//div[@class='header1']/span[@class='pic']/img/@src").toString();
            if (articleImage == null) {
                articleImage = page.getHtml().xpath("//div[@class='Strategy-pic']/img/@src").toString();
            }
            String showBuyLink1 = page.getHtml().xpath("//div[@class='list-info clearfix']/a[@class='dbtn hh-icon-buy']/@href").toString();
            String showBuyLink = null;
            if(showBuyLink1!=null){
                if(showBuyLink1.contains("hui")){
                    showBuyLink = INDEX_URL + showBuyLink1 ;
                }else{
                    showBuyLink = showBuyLink1;
                }
            }else{
                showBuyLink = "http://www.tapdealing.com/";
            }

            //标签
            List<String> allTag = page.getHtml().xpath("//a[@class='hico-doc hico-cagegory js-log']/text()").all();
            StringBuffer articleTag = new StringBuffer();
            for (String s : allTag) {
                articleTag.append(s + ",");
            }
            if(allTag.size()==0){
                 allTag = page.getHtml().xpath("//a[@class='hico-doc hico-cagegory']/text()").all();
                for (String s : allTag) {
                    articleTag.append(s + ",");
                }
            }
            //文章内容
            List<String> allContent = page.getHtml().xpath("//p[@class='Strategy-indent-p editer-content']/text()").all();
            StringBuffer articleContent = new StringBuffer();
            for (String s : allContent) {
                articleContent.append(s+"<p/>" + "<br/>");
            }
            Article article = new Article();
            article.setArticleTitle(articleTitle);
            article.setArticleSubTitle(articleSubTitle);

            article.setArticleAuthor(articleAuthor);
            article.setUpdateTime(updateTime);
            article.setArticleImage(articleImage);
            article.setArticleTag(String.valueOf(articleTag));
            article.setArticleLink(articleLink);
            article.setShopBuyLink(showBuyLink);
            article.setArticleContent(String.valueOf(articleContent));
            article.setArticleLanguage(Article.LANGUAGES_ZH_CN);
            article.setArticleArea(Article.AREA_CHINA);
            article.setArticleAreaType(Article.AREA_TYPE_CHINA);
            article.setArticleType(Article.ARTICLE_TYPE_C_DEAL);
            article.setArticleLanguageType(Article.ARTICLE_LANGUAGE_TYPE_C_ZH_CN);
            article.setWebSiteType(Article.WEB_TYPE_HUIHUI);

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(currentTime);
            article.setCollarTime(date);

            //根据articleTitle查询数据库有没有这类的文章
            List<Article> articleList = new CommonDao().queryArticle(articleTitle);
            if(0 == articleList.size() && (updateTime.contains("分钟") || updateTime.contains("小时"))){
                //没有文章,往数据库存
                new CommonDao().addArticle(article);
            }
        }
    }

    public Site getSite() {
        return site;
    }

}