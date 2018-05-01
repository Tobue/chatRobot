package com.chatRobot.processor;


import com.chatRobot.dao.CommonDao;
import com.chatRobot.model.Article;
import com.chatRobot.utils.ReduceHtmlText;
import org.apache.commons.lang.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30. 抓取 惠喵 的文章信息
 */
public class HuimaoProcessor implements PageProcessor {

    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    private static final String INDEX_URL = "http://www.huim.com/kuaibao";
    private static final String DESC_URL = "http://www.huim\\.com\\/detail/.*.html";


    public void process(Page page) {
        String pageUrl = page.getUrl().toString();
        if(pageUrl.contains(INDEX_URL) && !pageUrl.contains("detail/")){
            List<String> links = page.getHtml().links().regex(DESC_URL).all();
            HashSet h = new HashSet(links);
            links.clear();
            links.addAll(h);
            page.addTargetRequests(links);
        }else{ //商品详情页
            String articleLink = page.getUrl().toString();
            String articleTitle = page.getHtml().xpath("//div[@class='detial_content fl']/h4[@class='detial_tit']/text()").toString().trim();
            String articleSubTitle = page.getHtml().xpath("//div[@class='detial_content fl']/h4[@class='detial_tit']/span/text()").toString().trim();
            String updateTime = page.getHtml().xpath("//div[@class='detail_author']/span[@class='pub_time']/text()").toString().trim();
            String articleAuthor = page.getHtml().xpath("//div[@class='detail_author']/span[@class='pub_author']/text()").toString().trim();
            String articleTag = page.getHtml().xpath("//div[@class='detail_pic']/a[@class='shop_name']/text()").toString().trim();
            if(articleTag.contains("商城：")){
                articleTag = articleTag.replaceAll("商城：","")+",";
            }
            String articleImage = page.getHtml().xpath("//div[@class='detail_pic']/a[@class='pic_wrap']/img/@src").toString();
            String showBuyLink1 = page.getHtml().xpath("//div[@class='detail_active ']/a[@id='tdj_btn']/@href").toString();
            String showBuyLink = null;
            if(showBuyLink1.startsWith("/go/")){
                showBuyLink = "http://www.huim.com" + showBuyLink1;
            }else{
                showBuyLink = showBuyLink1;
            }
            String articleContent = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("//div[@class='detail_info']/div[@class='detail_dec']/p[1]").toString()).trim()+"<p/>"+"<br/>";

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
            article.setWebSiteType(Article.WEB_TYPE_HUIMAO);

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(currentTime);
            article.setCollarTime(date);

            //根据articleTitle查询数据库有没有这类的文章
            List<Article> articleList = new CommonDao().queryArticle(articleTitle);
            if(0 == articleList.size()&&updateTime.contains("今天")){
                //没有文章,往数据库存
                new CommonDao().addArticle(article);
            }
        }
    }

    public Site getSite() {
        return site;
    }
}