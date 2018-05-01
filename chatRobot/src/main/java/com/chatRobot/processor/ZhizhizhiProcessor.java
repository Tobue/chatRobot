package com.chatRobot.processor;


import com.chatRobot.dao.CommonDao;
import com.chatRobot.model.Article;
import com.chatRobot.utils.ReduceHtmlText;
import com.chatRobot.utils.StringTrim;
import com.chatRobot.utils.TitleUtil;
import org.apache.commons.lang.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30. 抓取 值值值 的文章信息
 */
public class ZhizhizhiProcessor implements PageProcessor {

    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    private static final String INDEX_URL = "http://zhizhizhi.com";
    private static final String DESC_URL_1 = "http://zhizhizhi.com/n/.*/";
    private static final String DESC_URL_2 = "http://zhizhizhi.com/t/.*/";
    private static final String DESC_URL_3 = "http://zhizhizhi.com/haitao/.*/";


    public void process(Page page) {
        String pageUrl = page.getUrl().toString();
        //列表
        boolean ac = pageUrl.contains(INDEX_URL);
        boolean bc = ((!pageUrl.contains("http://zhizhizhi.com/n/"))&&(!pageUrl.contains("http://zhizhizhi.com/t/"))&&(!pageUrl.contains("http://zhizhizhi.com/haitao/")));
        if(ac && bc){
            List<String> links = page.getHtml().links().regex(DESC_URL_1).all();
            List<String> links_2 = page.getHtml().links().regex(DESC_URL_2).all();
            List<String> links_3 = page.getHtml().links().regex(DESC_URL_3).all();
            links.addAll(links_2);
            links.addAll(links_3);
            //去除重复的url
            HashSet h = new HashSet(links);
            links.clear();
            links.addAll(h);
            page.addTargetRequests(links);
        }else{ //商品详情页
            String articleLink = page.getUrl().toString();
            String articleTitle1 = page.getHtml().xpath("//div[@class='buy_show']/div[@class='buy_show_all']/h1/text()").toString();
            String articleTitle2 = page.getHtml().xpath("//div[@class='main_left']/div[@class='content_all']/h1/text()").toString();
            String articleTitle = "";
            if(StringUtils.isNotEmpty(articleTitle1)){
                articleTitle = articleTitle1;
            }else if(StringUtils.isNotEmpty(articleTitle2)){
                articleTitle = articleTitle2;
            }else{
                articleTitle = "";
            }
            String articleSubTitle1 = page.getHtml().xpath("//div[@class='single_newprice']/em[@class='price']/text()").toString();
            String articleSubTitle2 = page.getHtml().xpath("//div[@class='main_left']/div[@class='content_all']/h1/i[@class='dprice']/text()").toString();
            String articleSubTitle = null;
            if(StringUtils.isNotEmpty(articleSubTitle1)){
                articleSubTitle = articleSubTitle1.trim();
            }else if(StringUtils.isNotEmpty(articleSubTitle2)){
                articleSubTitle = articleSubTitle2.trim();
            }else{
                articleSubTitle = "";
            }
            String a = page.getHtml().xpath("//div[@class='buy_show_all']/div[@class='gobuy_big']/span/text()").toString();
            String b = page.getHtml().xpath("//div[@id='mall_box']/div[@class='mall_box_txt']/ul/li[1]/text()").toString();
            String articleAuthor = null;
            String updateTime = null;
            if(StringUtils.isNotEmpty(a)){
                String[] as = a.trim().split(" ");
                articleAuthor = as[0];
                updateTime = as[1];
            }else if(StringUtils.isNotEmpty(b)){
                String[] bs = b.trim().split(" ");
                articleAuthor = bs[0];
                updateTime = bs[1];
            }else{
                articleAuthor = "";
                updateTime = "";
            }

            String tag1 = page.getHtml().xpath("//div[@id='mall_box']/div[@class='mall_box_txt']/ul/li[4]/a/text()").toString();
            String s1 = page.getHtml().xpath("//div[@id='mall_box']/div[@class='tags']/span[@class='tag']").toString();
            String s2 = ReduceHtmlText.removeHtmlTag(s1);
            String trim= null;
            if(StringUtils.isNotEmpty(s2)){
                if(s2.contains("相关话题：")){
                    trim = s2.replaceAll("相关话题：", "").trim();
                }
            }
            String tagAll1 = null;
            if(StringUtils.isNotEmpty(tag1)&&StringUtils.isNotEmpty(trim)){
                tagAll1 = trim + "," + tag1 + ",";
            }else if(StringUtils.isNotEmpty(tag1)&&StringUtils.isEmpty(trim)){
                tagAll1 = tag1 + ",";
            }else if(StringUtils.isEmpty(tag1)&&StringUtils.isNotEmpty(trim)){
                tagAll1 = trim + ",";
            }

            String s3 = page.getHtml().xpath("//div[@class='t_taglist']/span[@class='tag']").toString();
            String s4 = ReduceHtmlText.removeHtmlTag(s3);
            String tagAll2 = null;
            if(StringUtils.isNotEmpty(s4)){
                tagAll2 = s4.replaceAll(" ", ",")+",";
            }

            String articleTag = null;
            if(StringUtils.isNotEmpty(tagAll1)){
                articleTag = tagAll1;
            }else if(StringUtils.isNotEmpty(tagAll2)){
                articleTag = tagAll2;
            }else{
                articleTag = "值值值,";
            }

            String a_image = page.getHtml().xpath("//a[@class='deal_click']/img[@class='attachment-medium size-medium wp-post-image']/@src").toString();
            String b_image = page.getHtml().xpath("//a[@class='post_box_lr_img deal_click']/img[@class='attachment-post-thumbnail size-post-thumbnail wp-post-image']/@src").toString();
            String articleImage = null;
            if(StringUtils.isNotEmpty(a_image)){
                articleImage = a_image;
            }else if(StringUtils.isNotEmpty(b_image)){
                articleImage = b_image;
            }else{
                articleImage = "";
            }
            String showBuyLink1 = page.getHtml().xpath("//div[@class='buy_show_all']/div[@class='gobuy_big']/a[@class='deal_click']/@href").toString();
            String showBuyLink2 = page.getHtml().xpath("//ul[@id='box_show_b']/li[@class='sc_goshop']/a[@class='imlink_gobuy deal_click']/@href").toString();
            String showBuyLink = null;
            if(StringUtils.isNotEmpty(showBuyLink1)){
                if(showBuyLink1.contains("/link-yh")){
                    showBuyLink = INDEX_URL + showBuyLink1;
                }else{
                    showBuyLink = showBuyLink1;
                }
            }else if(StringUtils.isNotEmpty(showBuyLink2)){
                if(showBuyLink2.contains("/link-yh")){
                    showBuyLink = INDEX_URL + showBuyLink2;
                }else {
                    showBuyLink = showBuyLink2;
                }
            }else{
                showBuyLink = "";
            }
            String articleContent = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("//div[@class='content_a']").toString()).trim() + "<p/>" + "<br/>";

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
            article.setWebSiteType(Article.WEB_TYPE_ZHIZHIZHI);

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(currentTime);
            article.setCollarTime(date);

            //根据articleTitle查询数据库有没有这类的文章
            List<Article> articleList = new CommonDao().queryArticle(articleTitle);
            Date currentTime2 = new Date();
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = formatter2.format(currentTime2);
            boolean da = articleList.size()==0;
            boolean de = updateTime.contains("分钟")||updateTime.contains("小时")||updateTime.contains(date2);
            if(da&&de){
                //没有文章,往数据库存
                new CommonDao().addArticle(article);
            }
        }
    }

    public Site getSite() {
        return site;
    }
}