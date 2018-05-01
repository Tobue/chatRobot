package com.chatRobot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chatRobot.entity.User;
import com.chatRobot.model.Article;
import com.chatRobot.service.IArticleService;
import com.chatRobot.utils.ChromeDriver;
import com.chatRobot.utils.HttpUtils;
import com.chatRobot.utils.LoginUtil;
import org.apache.bcel.generic.RETURN;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/11/3.
 */
@Controller
@RequestMapping("/article")
@Component
public class ArticleController {
    @Resource
    private IArticleService iArticleService;

    /**
     * 查询所有文章
     *
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "showAllArticle", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> showAll(HttpServletResponse response, Model model) {
        List<Article> articles = iArticleService.showAll();
        System.out.println("nihao");
        System.out.println("大家好!!!!!!");
        return articles;
    }

    /**
     * 查询网站类型文章
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "showArticle", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> showArticle(HttpServletRequest request, HttpServletResponse response, int articleType) {
        String type = request.getParameter("articleType");
        List<Article> articles = iArticleService.showArticle(articleType);
        return articles;
    }

    /**
     * 添加文章
     *
     * @param article
     */
    @RequestMapping(value = "addArticle")
    @ResponseBody
    public Map<String, Object> addArticle(Article article) {
        Map<String, Object> rtn = new HashMap<>();
        if (StringUtils.isBlank((CharSequence) article)) {
            rtn.put("success", false);
            rtn.put("message", "添加的数据为空");
            return rtn;
        }
        try {
            iArticleService.addArticle(article);
            rtn.put("success", true);
            rtn.put("message", "添加成功");
        } catch (Exception e) {
            rtn.put("success", false);
            rtn.put("message", "添加失败");
        }
        return rtn;
    }

    /**
     * 更新文章
     *
     * @param
     */
    @RequestMapping(value = "updateArticle", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateArticle(HttpServletRequest request) {
        String articleString = request.getParameter("article");
        JSONObject jsonObject = JSONObject.parseObject(articleString);
        int articleId = Integer.parseInt((String) jsonObject.get("articleId"));
        Article atr = iArticleService.issueArticle(articleId);
        Article article = new Article();
        article.setArticleId(articleId);
        article.setCollarTime(atr.getCollarTime());
        article.setArticleTitle((String) jsonObject.get("articleTitle"));
        article.setArticleSubTitle((String) jsonObject.get("articleSubTitle"));
        article.setArticleAuthor(atr.getArticleAuthor());
        article.setUpdateTime(atr.getUpdateTime());
        String articleLink = (String) jsonObject.get("articleLink");
        String articleTag = (String) jsonObject.get("articleTag");
        String[] split = articleTag.split(",");
        Map<String, Object> rtn = new HashMap<>();
        if (!articleLink.contains("dealgogogo")) {
            if (split.length <= 2) {
                rtn.put("success", false);
                rtn.put("message", "标签不能小于3个");
                return rtn;
            }
            article.setArticleTag(articleTag);
            String shopBuyLink = (String) jsonObject.get("shopBuyLink");
            if (shopBuyLink.contains("smzdm") || shopBuyLink.contains("mgpyh") || shopBuyLink.contains("huihui") || shopBuyLink.contains("guangdiu")
                    || shopBuyLink.contains("zhizhizhi") || shopBuyLink.contains("huim") || shopBuyLink.contains("slickdeals") || shopBuyLink.contains("dealsea")
                    || shopBuyLink.contains("dealnews") || shopBuyLink.contains("dealgogogo")||shopBuyLink.contains("dealam")) {
                rtn.put("success", false);
                rtn.put("message", "去购买链接不是真实链接,请点击'打开链接'获取真实链接");
                return rtn;
            }
            article.setShopBuyLink(shopBuyLink);
            article.setArticleContent((String) jsonObject.get("articleContent"));
            article.setArticleImage((String) jsonObject.get("articleImage"));
            article.setArticleArea(atr.getArticleArea());
            article.setArticleLanguage((String) jsonObject.get("articleLanguage"));
            article.setArticleType(atr.getArticleType());
            article.setArticleLink((String) jsonObject.get("articleLink"));
            article.setIssueState((String) jsonObject.get("issueState"));
            article.setArticleLanguageType(atr.getArticleLanguageType());
            article.setArticleAreaType(atr.getArticleAreaType());
            article.setWebSiteType(atr.getWebSiteType());
            article.setArticleLanguage(atr.getArticleLanguage());
            try {
                iArticleService.updateArticle(article);
                rtn.put("success", true);
                rtn.put("message", "修改成功");
            } catch (Exception e) {
                rtn.put("success", false);
                rtn.put("message", "修改失败");
            }
            return rtn;
        } else {
            article.setArticleTag(articleTag);
            String shopBuyLink = (String) jsonObject.get("shopBuyLink");
            if (shopBuyLink.contains("smzdm") || shopBuyLink.contains("mgpyh") || shopBuyLink.contains("huihui") || shopBuyLink.contains("guangdiu")
                    || shopBuyLink.contains("zhizhizhi") || shopBuyLink.contains("huim") || shopBuyLink.contains("slickdeals") || shopBuyLink.contains("dealsea")
                    || shopBuyLink.contains("dealnews") || shopBuyLink.contains("dealgogogo")) {
                rtn.put("success", false);
                rtn.put("message", "去购买链接不是真实链接,请点击'打开链接'获取真实链接");
                return rtn;
            }
            article.setShopBuyLink(shopBuyLink);
            article.setArticleContent((String) jsonObject.get("articleContent"));
            article.setArticleImage((String) jsonObject.get("articleImage"));
            article.setArticleArea(atr.getArticleArea());
            article.setArticleLanguage((String) jsonObject.get("articleLanguage"));
            article.setArticleType(atr.getArticleType());
            article.setArticleLink((String) jsonObject.get("articleLink"));
            article.setIssueState((String) jsonObject.get("issueState"));
            article.setArticleLanguageType(atr.getArticleLanguageType());
            article.setArticleAreaType(atr.getArticleAreaType());
            article.setWebSiteType(atr.getWebSiteType());
            article.setArticleLanguage(atr.getArticleLanguage());
            try {
                iArticleService.updateArticle(article);
                rtn.put("success", true);
                rtn.put("message", "修改成功");
            } catch (Exception e) {
                rtn.put("success", false);
                rtn.put("message", "修改失败");
            }
                return rtn;
             }
    }

    /**
     * 根据id删除文章
     *
     * @param
     */
    @RequestMapping(value = "deleteArticle", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request, String id) {
        Map<String, Object> rtn = new HashMap<>();
        if (StringUtils.isBlank(id)) {
            rtn.put("success", false);
            rtn.put("message", "删除失败");
            return rtn;
        }
        int anInt = Integer.parseInt(id);
        try {
            iArticleService.delete(anInt);
            rtn.put("success", true);
            rtn.put("message", "删除成功");
        } catch (Exception e) {
            rtn.put("success", false);
            rtn.put("message", "删除失败");
        }
        return rtn;
    }

    /**
     * 批量删除文章
     */
    @RequestMapping(value = "BatchDelete", method = RequestMethod.POST)
    @ResponseBody
    public void batchDelete(HttpServletRequest request, String dataAll) {
        List<Object> list = com.alibaba.fastjson.JSONObject.parseArray(dataAll);
        List<Integer> idList = new ArrayList<>();
        for (Object o : list) {
            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(String.valueOf(o));
            String articleId = jsonObject.getString("articleId");
            idList.add(Integer.valueOf(articleId));
        }
        int[] ids = new int[idList.size()];
        for (int i = 0; i < idList.size(); i++) {
            ids[i] = idList.get(i);
        }

        iArticleService.batchDalete(ids);

    }


    /**
     * 发布文章(单个)
     *
     * @return
     */
    @RequestMapping(value = "issueArticle", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> issueArticle(HttpServletRequest request) {
        String articleIdString = request.getParameter("articleId");
        Map<String, Object> rtn = new HashMap<>();
        if (StringUtils.isBlank(articleIdString)) {
            rtn.put("success", false);
            rtn.put("message", "发布失败");
            return rtn;
        }
        int articleId = Integer.parseInt(articleIdString);
        try {
            Article article = iArticleService.issueArticle(articleId);

            // 创建Httpclient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //使用配置文件
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(new File(LoginUtil.class.getResource("/").getPath() + "jdbc.properties"));
            properties.load(fis);
            String url = properties.getProperty("url.issueArticle");
            // 创建http POST请求
            HttpPost httpPost = new HttpPost(url);

            //从session里面获取用户信息
            com.chatRobot.model.User user = (com.chatRobot.model.User) request.getSession().getAttribute("user");
            String username = user.getUsername();
            String password = user.getPassword();
            String token = LoginUtil.getToken(username,password);
            if(StringUtils.isEmpty(token)){
                rtn.put("success", false);
                rtn.put("message", "发布失败");
                return rtn;
            }
            httpPost.setHeader("token", token);
            List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
            parameters.add(new BasicNameValuePair("articleId", String.valueOf(article.getArticleId())));
            parameters.add(new BasicNameValuePair("collarTime", article.getCollarTime()));
            parameters.add(new BasicNameValuePair("articleTitle", article.getArticleTitle()));
            parameters.add(new BasicNameValuePair("articleSubTitle", article.getArticleSubTitle()));
            parameters.add(new BasicNameValuePair("articleAuthor", article.getArticleAuthor()));
            parameters.add(new BasicNameValuePair("updateTime", article.getUpdateTime()));
            parameters.add(new BasicNameValuePair("articleTag", article.getArticleTag()));
            parameters.add(new BasicNameValuePair("shopBuyLink", article.getShopBuyLink()));
            parameters.add(new BasicNameValuePair("articleContent", article.getArticleContent()));
            parameters.add(new BasicNameValuePair("articleImage", article.getArticleImage()));
            parameters.add(new BasicNameValuePair("articleArea", article.getArticleArea()));
            parameters.add(new BasicNameValuePair("articleLanguage", article.getArticleLanguage()));
            parameters.add(new BasicNameValuePair("articleLink", article.getArticleLink()));

            parameters.add(new BasicNameValuePair(Article.ARTICLE_LANGUAGE_TYPE, String.valueOf(article.getArticleLanguageType())));
            parameters.add(new BasicNameValuePair(Article.ARTICLE_TYPE, String.valueOf(article.getArticleType())));
            parameters.add(new BasicNameValuePair(Article.ARTICLE_AREA_TYPE, String.valueOf(article.getArticleAreaType())));
            parameters.add(new BasicNameValuePair(Article.WEBSITE_TYPE, String.valueOf(article.getWebSiteType())));


            // 构造一个form表单式的实体
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(formEntity);

            CloseableHttpResponse response = null;
            try {
                // 执行请求
                response = httpclient.execute(httpPost);
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
                JSONObject jsonObject = JSONObject.parseObject(content);
                boolean sc = Boolean.parseBoolean(jsonObject.getString("sc"));
                String mag = jsonObject.getString("mag");
                if (sc) {
                    //更改发布的状态
                    article.setIssueState(Article.ARTICLE_ISSUE);
                    iArticleService.updateArticle(article);
                    rtn.put("success", true);
                    rtn.put("message", "发布成功");
                } else {
                    article.setIssueState("发布失败");
                    iArticleService.updateArticle(article);
                    rtn.put("success", false);
                    rtn.put("message", "发布失败," + mag);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    /**
     * 批量发布文章(多个)
     *
     * @param request
     * @param dataAll
     */
    @RequestMapping(value = "batchIssue", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> batchIssue(HttpServletRequest request, String dataAll) {
        Map<String, Object> rtn = new HashMap<>();
        //对前端传递过来的数据进行解析
        List<Object> jsonArray = JSONObject.parseArray(dataAll);
        try {
            for (Object o : jsonArray) {
                JSONObject aj = JSONObject.parseObject(String.valueOf(o));

                Article article = new Article();
                article.setArticleId(Integer.parseInt(aj.getString("articleId")));
                article.setCollarTime(aj.getString("collarTime"));
                article.setArticleTitle(aj.getString("articleTitle"));
                article.setArticleSubTitle(aj.getString("articleSubTitle"));
                article.setArticleAuthor(aj.getString("articleAuthor"));
                article.setUpdateTime(aj.getString("updateTime"));
                article.setArticleTag(aj.getString("articleTag"));
                article.setShopBuyLink(aj.getString("shopBuyLink"));
                article.setArticleContent(aj.getString("articleContent"));
                article.setArticleImage(aj.getString("articleImage"));
                article.setArticleArea(aj.getString("articleArea"));
                article.setArticleLanguage(aj.getString("articleLanguage"));
                article.setArticleType(aj.getInteger("articleType"));
                article.setArticleLink(aj.getString("articleLink"));
                article.setArticleLanguageType(Integer.parseInt(aj.getString(Article.ARTICLE_LANGUAGE_TYPE)));
                article.setArticleAreaType(Integer.parseInt(aj.getString(Article.ARTICLE_AREA_TYPE)));
                article.setWebSiteType(Integer.parseInt(aj.getString(Article.WEBSITE_TYPE)));
                try {

                    // 创建Httpclient对象
                    CloseableHttpClient httpclient = HttpClients.createDefault();
                    // 创建http POST请求

                    //使用配置文件
                    Properties properties = new Properties();
                    FileInputStream fis = new FileInputStream(new File(ArticleController.class.getResource("/").getPath() + "jdbc.properties"));
                    properties.load(fis);
                    String url = properties.getProperty("url.issueArticle");
                    HttpPost httpPost = new HttpPost(url);

                    //从session里面获取用户信息
                    com.chatRobot.model.User user = (com.chatRobot.model.User) request.getSession().getAttribute("user");
                    String username = user.getUsername();
                    String password = user.getPassword();
                    String token = LoginUtil.getToken(username,password);
                    if(StringUtils.isEmpty(token)){
                        rtn.put("success", false);
                        rtn.put("message", "发布失败");
                        return rtn;
                    }
                    httpPost.setHeader("token", token);
                    // 设置2个post请求参数
                    List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
                    parameters.add(new BasicNameValuePair("articleId", String.valueOf(article.getArticleId())));
                    parameters.add(new BasicNameValuePair("collarTime", article.getCollarTime()));
                    parameters.add(new BasicNameValuePair("articleTitle", article.getArticleTitle()));
                    parameters.add(new BasicNameValuePair("articleSubTitle", article.getArticleSubTitle()));
                    parameters.add(new BasicNameValuePair("articleAuthor", article.getArticleAuthor()));
                    parameters.add(new BasicNameValuePair("updateTime", article.getUpdateTime()));
                    parameters.add(new BasicNameValuePair("articleTag", article.getArticleTag()));
                    parameters.add(new BasicNameValuePair("shopBuyLink", article.getShopBuyLink()));
                    parameters.add(new BasicNameValuePair("articleContent", article.getArticleContent()));
                    parameters.add(new BasicNameValuePair("articleImage", article.getArticleImage()));
                    parameters.add(new BasicNameValuePair("articleArea", article.getArticleArea()));
                    parameters.add(new BasicNameValuePair("articleLanguage", article.getArticleLanguage()));
                    parameters.add(new BasicNameValuePair("articleLink", article.getArticleLink()));

                    parameters.add(new BasicNameValuePair(Article.ARTICLE_LANGUAGE_TYPE, String.valueOf(article.getArticleLanguageType())));
                    parameters.add(new BasicNameValuePair(Article.ARTICLE_TYPE, String.valueOf(article.getArticleType())));
                    parameters.add(new BasicNameValuePair(Article.ARTICLE_AREA_TYPE, String.valueOf(article.getArticleAreaType())));
                    parameters.add(new BasicNameValuePair(Article.WEBSITE_TYPE, String.valueOf(article.getWebSiteType())));
                    // 构造一个form表单式的实体
                    UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
                    // 将请求实体设置到httpPost对象中
                    httpPost.setEntity(formEntity);
                    CloseableHttpResponse response = null;
                    try {
                        // 执行请求
                        response = httpclient.execute(httpPost);
                        String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                        System.out.println(content);
                        JSONObject jsonObject = JSONObject.parseObject(content);
                        boolean sc = Boolean.parseBoolean(jsonObject.getString("sc"));
                        String mag = jsonObject.getString("mag");
                        if (sc) {
                            //更改发布的状态
                            article.setIssueState(Article.ARTICLE_ISSUE);
                            iArticleService.updateArticle(article);
                        } else {
                            article.setIssueState(Article.ARTICLE_FAIL);
                            iArticleService.updateArticle(article);
                            rtn.put("success", true);
                            rtn.put("message", "发布失败" + mag);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (response != null) {
                            response.close();
                        }
                        httpclient.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    rtn.put("success", true);
                    rtn.put("message", "发布失败" + e.getMessage());
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            rtn.put("success", false);
            rtn.put("message", "发布失败" + e.getMessage());
        }
        rtn.put("success", true);
        rtn.put("message", "发布成功");
        return rtn;
    }

    /**
     * 分页查询
     *
     * @param request
     * @param page 页码
     * @param rows  每页显示的条数  http://localhost:8080/article/queryPageArticle?webSiteType=0&page=1&rows=10
     */
    protected Logger log = Logger.getLogger(ArticleController.class);

    @RequestMapping(value = "queryPageArticle", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryPageArticle(HttpServletRequest request, int page, int rows) {
        int webSiteType = Integer.parseInt(request.getParameter("webSiteType"));
        //map集合封装传递到到dao层的参数
        Map<String, Object> map = new HashMap<>();
        map.put("page", page * rows - rows);
        map.put("rows", rows);
        map.put("webSiteType", webSiteType);
        try {

            List<Article> articles = iArticleService.queryPageArticle(map);

            //属于这个类型的文章总数
            int pageCount = iArticleService.queryArticleCount(webSiteType);
            //文章有几页
            int total = pageCount;
            Map<String, Object> result = new HashMap<>();
            result.put("total", total);
            result.put("rows", articles);
            return result;
        } catch (Exception e) {
            log.error("文章没有查到");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 发布文章的分页
     *
     * @param request
     * @param page    页码
     * @param rows    每页显示的条数
     */
    @RequestMapping(value = "queryPageIssueArticle", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryPageIssueArticle(HttpServletRequest request, int page, int rows) {
        int webSiteType = Integer.parseInt(request.getParameter("webSiteType"));
        String issueState = request.getParameter("issueState");
        //map集合封装传递到到dao层的参数
        Map<String, Object> map = new HashMap<>();
        map.put("page", page * rows - rows);
        map.put("rows", rows);
        map.put("webSiteType", webSiteType);
        map.put("issueState", issueState);
        List<Article> articles = iArticleService.queryPageIssueArticle(map);
        //属于这个类型的文章总数
        Map<String, Object> demap = new HashMap<>();
        demap.put("webSiteType", webSiteType);
        demap.put("issueState", issueState);
        int pageCount = iArticleService.queryIssueArticleCount(demap);
        //文章有几页
        int total = pageCount;
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("rows", articles);
        return result;
    }

    /**
     * 根据时间删除过期文章
     *
     * @param
     * @return
     */
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteAll(HttpServletRequest request, String collarTime) {
        Map<String, Object> map = new HashMap<>();
        try {
            iArticleService.deleteAll(collarTime + "%");
            map.put("success", true);
            map.put("message", "清除成功");
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "清除失败" + e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 3天删除一次
     */
    @Scheduled(cron = "0 0 0 1/3 * ?")
    public void deleteTime() {
        iArticleService.deleteTime();
    }

    /**
     * 定时(每天的凌晨12点)删除前三天的数据
     */
    //@Scheduled(cron = "0 0 0 * * ?")
    public void deleteBythree(){
         String time1 = getTime(-1);
         String time2 = getTime(-2);
         String time3 = getTime(-3);
         iArticleService.deleteAll(time1+ "%");
         iArticleService.deleteAll(time2+ "%");
         iArticleService.deleteAll(time3+ "%");
     }

    /**
     * 获取前几天的时间
     * @param count
     * @return
     */
    public static String getTime(int count) {
        Date date=new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,count);
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 更新链接
     * @param request
     */
   // @RequestMapping(value = "/updateBuyLink",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateBuyLink(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        String shopBuyLinkString = request.getParameter("shopBuyLink");
        String showBuyLink = HttpUtils.getShowBuyLink(shopBuyLinkString);
        //String showBuyLink = ChromeDriver.getChromeDriver(shopBuyLinkString);
        map.put("showBuyLink",showBuyLink);
        return map;
    }
}
