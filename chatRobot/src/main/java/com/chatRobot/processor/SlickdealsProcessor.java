package com.chatRobot.processor;


import com.chatRobot.dao.CommonDao;
import com.chatRobot.model.Article;
import com.chatRobot.utils.ReduceHtmlText;
import com.chatRobot.utils.StringTrim;
import com.chatRobot.utils.TagUtil;
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
 * Created by Administrator on 2017/10/30. 抓取 slickdeals 的文章信息
 */
public class SlickdealsProcessor implements PageProcessor {

    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    private static final String INDEX_URL = "https://slickdeals.net";
    private static final String DESC_URL = "/f/.*";


    public void process(Page page) {
        String pageUrl = page.getUrl().toString();
        //列表
        if(pageUrl.contains(INDEX_URL) && !pageUrl.contains("/f/")){
            List<String> links = page.getHtml().links().regex(DESC_URL).all();
            //去除重复的url
            HashSet h = new HashSet(links);
            links.clear();
            links.addAll(h);
            page.addTargetRequests(links);
        }else{ //商品详情页
            String articleLink = page.getUrl().toString();
            String articleTitle = page.getHtml().xpath("//div[@id='dealTitle']/h1/text()").toString().trim();
            String articleSubTitle = page.getHtml().xpath("//div[@class='detailLeftColumn']/div[@id='dealPrice']/text()").toString().trim();

            String updateTime1 = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("//div[@class='editedInfo']/span[@class='editedDateTime']").toString());
            String updateTime2 = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("//span[@class='editedDateTime xh-highlight']").toString());
            String updateTime = null;
            if(StringUtils.isNotEmpty(updateTime1)){
                updateTime = updateTime1;
            }else if(StringUtils.isNotEmpty(updateTime2)){
                updateTime = updateTime2;
            }else {
                updateTime = "";
            }
            String a = page.getHtml().xpath("//div[@id='notesAndResearch']/div[@class='boxHeaderText']/a/text()").toString();
            String b = page.getHtml().xpath("//span[@id='op109820867x']/a[@class='has_icon']/text()").toString();
            String articleAuthor = null;
            if(StringUtils.isNotEmpty(a)){
                articleAuthor = a;
            }else if(StringUtils.isNotEmpty(b)){
                articleAuthor = b;
            }else{
                articleAuthor = "";
            }
            //这个网站没有特殊标签,把标题拆成一个一个的标签
            String articleTag = TagUtil.getTag(articleTitle);

            //图片
            String articleImage = page.getHtml().xpath("//div[@class='mainImageContainer']/a/img[@id='mainImage']/@src").toString();
            String showBuyLink = page.getHtml().xpath("//div[@class='deal-actions']/div[@id='buyNowButton']/a[@id='largeBuyNow']/@href").toString();
            String articleContent1 = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("//div[@class='textDescription']").toString()).trim();
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
            article.setWebSiteType(Article.WEB_TYPE_SLICKDEALS);

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(currentTime);
            article.setCollarTime(date);

            //根据articleTitle查询数据库有没有这类的文章
            List<Article> articleList = new CommonDao().queryArticle(articleTitle);
            if(0 == articleList.size()&&updateTime.contains("Today")){
                //没有文章,往数据库存
                new CommonDao().addArticle(article);
            }
        }
    }

    public Site getSite() {
        return site;
    }
}