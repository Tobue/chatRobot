package com.chatRobot.processor;


import com.chatRobot.dao.CommonDao;
import com.chatRobot.model.Article;
import com.chatRobot.utils.*;
import org.apache.commons.lang.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/10/30. 抓取 逛丢网 的文章信息
 */
public class GDuiProcessor implements PageProcessor {

    private Site site = Site
            .me()
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    private static final String INDEX_URL = "https://guangdiu.com";
    private static final String DESC_URL = "detail\\.php\\?id=.*";


    public void process(Page page) {
        String pageUrl = page.getUrl().toString();
        //列表
        if(pageUrl.contains(INDEX_URL) && !pageUrl.contains("detail.php?id=")){
            List<String> links = page.getHtml().links().regex(DESC_URL).all();
            //去除重复的url
            HashSet h = new HashSet(links);
            links.clear();
            links.addAll(h);
            page.addTargetRequests(links);
        }else{ //商品详情页
            String articleLink = page.getUrl().toString();
            String articleTitle = page.getHtml().xpath("//div[@class='dtitle']/a[@class='dtitlelink']/text()").toString().trim();
            String subTitleHtml = page.getHtml().xpath("//div[@class='dtitle']/a[@class='dtitlelink']").toString();
            String articleSubTitleAll = ReduceHtmlText.removeHtmlTag(subTitleHtml);
            String articleSubTitle = TitleUtil.removeComomTitle(articleTitle, articleSubTitleAll).trim();
            if(StringUtils.isEmpty(articleSubTitle)){
                articleSubTitle = INDEX_URL;
            }
            String updateTime = page.getHtml().xpath("//div[@class='dinfo']/span[@class='dinfotext'][1]/span[@class='latesttime']/text()").toString();
            if(StringUtils.isEmpty(updateTime)){
                updateTime = page.getHtml().xpath("//div[@class='dinfo']/span[@class='dinfotext']/text()").toString();
            }
            String articleAuthor = page.getHtml().xpath("//div[@class='dinfo']/span[@class='dinfotext'][3]/text()").toString();
            if(StringUtils.isEmpty(articleAuthor)){
                articleAuthor = "";
            }
            String articleTagAll = page.getHtml().xpath("//div[@class='dinfo']/span[@class='dinfotext'][2]/text()").toString();

            String articleTag = null;
            if(articleTagAll.contains("商城：")){
                String articleTag1 = articleTagAll.replaceAll("商城：","").replaceAll("（）","");
                String substring = articleTag1.substring(0, articleTag1.length() - 1);
                articleTag = substring + ",";
            }
            //图片
            String a = page.getHtml().xpath("//a[@class='dimage']/img/@src").toString();
            String b = page.getHtml().xpath("//div[@id='czfxhdetail']/div[@class='czfxhimgdiv']/img[@class='czfxhimg']/@src").toString();
            String c = page.getHtml().xpath("//div[@class='simgahead']/div[@class='simgaheadleft']/img[@class='simgaheadleftimg']/@src").toString();
            String d = page.getHtml().xpath("//div[@class='item-img']/a[@class='J-item-track img nodelog']/img/@src").toString();
            String e = page.getHtml().xpath("//div[@id='goods-detail']/p/a/img/@src").toString();
            String f = page.getHtml().xpath("//div[@class='dimage']/img/@src").toString();
            String g = page.getHtml().xpath("//div[@id='fivefivehaitao']/div[1]/div[1]/img/@src").toString();
            String h = page.getHtml().xpath("//div[@class='Strategy-pic']/img[@class='editer-content']/@src").toString();
            String i = page.getHtml().xpath("//div[@id='dabstract']/p/img[1]/@src").toString();
            String j = page.getHtml().xpath("//div[@class='aw-comment-upload-img-list active'][1]/div[@class='htyhimg']/img/@src").toString();

            String articleImage = null;
            if(StringUtils.isNotEmpty(a)){
                articleImage = a;
            }else if(StringUtils.isNotEmpty(b)){
                articleImage = b;
            }else if(StringUtils.isNotEmpty(c)){
                articleImage = c;
            }else if(StringUtils.isNotEmpty(d)){
                articleImage = d;
            }else if(StringUtils.isNotEmpty(e)){
                articleImage = e;
            }else if(StringUtils.isNotEmpty(f)){
                articleImage = f;
            }else if(StringUtils.isNotEmpty(g)){
                articleImage = g;
            }else if(StringUtils.isNotEmpty(h)){
                articleImage = h;
            }else if(StringUtils.isNotEmpty(i)){
                articleImage = i;
            }else if(StringUtils.isNotEmpty(j)){
                articleImage = j;
            }
            else{
                articleImage = INDEX_URL;
            }

            String showBuyLink = page.getHtml().xpath("//div[@class='dtitle']/a[@class='dtitlelink']/@href").toString();
            if(showBuyLink.contains("go.php?id=")){
                showBuyLink = INDEX_URL + "/" + showBuyLink;
            }

            String articleContent1 = ReduceHtmlText.removeHtmlTag(page.getHtml().xpath("//div[@class='dabstract']").toString()).trim();
            String articleContent2 = articleContent1.replaceAll("-->","").trim() + "<p/>" + "<br/>";
            String articleContent = StringTrim.replaceBlank(articleContent2);
            Article article = new Article();
            article.setArticleTitle(articleTitle);
            article.setArticleSubTitle(String.valueOf(articleSubTitle));
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
            article.setWebSiteType(Article.WEB_TYPE_GDUI);

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(currentTime);
            article.setCollarTime(date);

            //根据articleTitle查询数据库有没有这类的文章
            List<Article> articleList = new CommonDao().queryArticle(articleTitle);
            if(0 == articleList.size()&&articleImage!=INDEX_URL&&articleSubTitle!=INDEX_URL&&(updateTime.contains("分钟")||updateTime.contains("小时"))){
                //没有文章,往数据库存
                new CommonDao().addArticle(article);
            }
        }
    }

    public Site getSite() {
        return site;
    }
}