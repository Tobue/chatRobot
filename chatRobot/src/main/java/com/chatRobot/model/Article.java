package com.chatRobot.model;

/**
 * Created by Administrator on 2017/11/1.  文章实体类
 */
public class Article {

    /**
     * Article.
     */
    public static final String ARTICLE = "article";

    /**
     * Articles.
     */
    public static final String ARTICLES = "articles";

    /**
     * Key of article brand. 品牌
     */
    public static final String ARTICLE_BRAND = "articleBrand";

    /**
     * Key of article seller. 商家
     */
    public static final String ARTICLE_SELLER = "articleSeller";

    /**
     * Key of article . 原价
     */
    public static final String ARTICLE_PRICE = "articlePrice";

    /**
     * Key of article . 折后价
     */
    public static final String ARTICLE_DISCOUNT_PRICE = "articleDiscountPrice";

    /**
     * Key of article . 优惠结束时间
     */
    public static final String ARTICLE_PROMOTE_EXPIRE_TIME = "articlePromoteExpireTime";

    /**
     * Key of article reward content. 打赏区内容
     */
    public static final String ARTICLE_REWARD_CONTENT = "articleRewardContent";

    /**
     * Key of article reward point. 打赏积分
     */
    public static final String ARTICLE_REWARD_POINT = "articleRewardPoint";

    /**
     * Key of article tags. 标签
     */
    public static final String ARTICLE_TAGS = "articleTags";

    /**
     * Key of article author id.  作者 ID
     */
    public static final String ARTICLE_AUTHOR_ID = "articleAuthorId";

    /**
     * Key of article comment count. 回帖计数
     */
    public static final String ARTICLE_COMMENT_CNT = "articleCommentCount";

    /**
     * Key of article view count. 浏览计数
     */
    public static final String ARTICLE_VIEW_CNT = "articleViewCount";

    /**
     * Key of article permalink. 固定链接
     */
    public static final String ARTICLE_PERMALINK = "articlePermalink";

    /**
     * Key of article create time. 创建时间
     */
    public static final String ARTICLE_CREATE_TIME = "articleCreateTime";

    /**
     * Key of article update time.
     */
    public static final String ARTICLE_UPDATE_TIME = "articleUpdateTime";

    /**
     * Key of article latest comment time.
     */
    public static final String ARTICLE_LATEST_CMT_TIME = "articleLatestCmtTime";

    /**
     * Key of article latest commenter name.
     */
    public static final String ARTICLE_LATEST_CMTER_NAME = "articleLatestCmterName";

    /**
     * Key of article random double value.
     */
    public static final String ARTICLE_RANDOM_DOUBLE = "articleRandomDouble";

    /**
     * Key of article commentable.
     */
    public static final String ARTICLE_COMMENTABLE = "articleCommentable";

    /**
     * Key of article sync to client. 客户端同步
     */
    public static final String ARTICLE_SYNC_TO_CLIENT = "syncWithSymphonyClient";

    /**
     * Key of client article id. 客户端文章 ID
     */
    public static final String ARTICLE_CLIENT_ARTICLE_ID = "clientArticleId";

    /**
     * Key of client article permalink. 客户端文章固定链接
     */
    public static final String ARTICLE_CLIENT_ARTICLE_PERMALINK = "clientArticlePermalink";

    /**
     * Key of article editor type.编辑器类型
     */
    public static final String ARTICLE_EDITOR_TYPE = "articleEditorType";

    /**
     * Key of article status. 文章(帖子)状态
     */
    public static final String ARTICLE_STATUS = "articleStatus";

    /**
     * Key of article good count. 赞同计数
     */
    public static final String ARTICLE_GOOD_CNT = "articleGoodCnt";

    /**
     * Key of article bad count. 反对计数
     */
    public static final String ARTICLE_BAD_CNT = "articleBadCnt";

    /**
     * Key of article collection count. 收藏计数
     */
    public static final String ARTICLE_COLLECT_CNT = "articleCollectCnt";

    /**
     * Key of article watch count. 关注计数
     */
    public static final String ARTICLE_WATCH_CNT = "articleWatchCnt";

    /**
     * Key of reddit score.
     */
    public static final String REDDIT_SCORE = "redditScore";


    /**
     * Key of article city.  文章城市
     */
    public static final String ARTICLE_CITY = "articleCity";


    /**
     * Key of article city. 文章所在省份
     */
    public static final String ARTICLE_PROVINCE = "articleProvince";

    /**
     * Key of article city. 文章国家
     */
    public static final String ARTICLE_COUNTRY = "articleCountry";

    /**
     * Key of article city. 文章所在区域 . 中国 .东南亚 印度 美国 加拿大 欧洲  俄罗斯
     */
    public static final String ARTICLE_AREA = "articleArea";

    /**
     * Key of article city. 文章所用的语言
     */
    public static final String ARTICLE_LANGUAGE = "articleLanguage";



    /**
     * Key of article IP. 文章IP
     */
    public static final String ARTICLE_IP = "articleIP";

    /**
     * Key of article UA. 文章的UA
     */
    public static final String ARTICLE_UA = "articleUA";

    /**
     * Key of article stick. 置顶文章
     */
    public static final String ARTICLE_STICK = "articleStick";

    /**
     * Key of article anonymous. 匿名文章
     */
    public static final String ARTICLE_ANONYMOUS = "articleAnonymous";

    /**
     * Key of article perfect. 优选文章
     */
    public static final String ARTICLE_PERFECT = "articlePerfect";

    /**
     * Key of article anonymous view. 文章匿名浏览
     */
    public static final String ARTICLE_ANONYMOUS_VIEW = "articleAnonymousView";

    /**
     * Key of article audio URL. 文章音频链接
     */
    public static final String ARTICLE_AUDIO_URL = "articleAudioURL";

    //// Transient ////
    /**
     * Key of article revision count. 修改计数
     */
    public static final String ARTICLE_REVISION_COUNT = "articleRevisionCount";

    /**
     * Key of article latest comment. 文章的最新评论
     */
    public static final String ARTICLE_T_LATEST_CMT = "articleLatestCmt";

    /**
     * Key of previous article. 前一个文章
     */
    public static final String ARTICLE_T_PREVIOUS = "articlePrevious";

    /**
     * Key of next article. 后一个文章
     */
    public static final String ARTICLE_T_NEXT = "articleNext";

    /**
     * Key of article tag objects. 文章标签对象
     */
    public static final String ARTICLE_T_TAG_OBJS = "articleTagObjs";

    /**
     * Key of article vote. 文章投票
     */
    public static final String ARTICLE_T_VOTE = "articleVote";

    /**
     * Key of article stick flag.
     */
    public static final String ARTICLE_T_IS_STICK = "articleIsStick";

    /**
     * Key of article stick remains.
     */
    public static final String ARTICLE_T_STICK_REMAINS = "articleStickRemains";

    /**
     * Key of article preview content.
     */
    public static final String ARTICLE_T_PREVIEW_CONTENT = "articlePreviewContent";

    /**
     * Key of article thumbnail URL.
     */
    public static final String ARTICLE_T_THUMBNAIL_URL = "articleThumbnailURL";

    /**
     * Key of article view count display format. 文章浏览数显示格式
     */
    public static final String ARTICLE_T_VIEW_CNT_DISPLAY_FORMAT = "articleViewCntDisplayFormat";

    /**
     * Key of article id.
     */
    public static final String ARTICLE_T_ID = "articleId";

    /**
     * Key of article ids.
     */
    public static final String ARTICLE_T_IDS = "articleIds";

    /**
     * Key of article author. 文章作者
     */
    public static final String ARTICLE_T_AUTHOR = "articleAuthor";

    /**
     * Key of article author thumbnail URL.  作者头像URL
     */
    public static final String ARTICLE_T_AUTHOR_THUMBNAIL_URL = "articleAuthorThumbnailURL";

    /**
     * Key of article author name. 作者名字
     */
    public static final String ARTICLE_T_AUTHOR_NAME = "articleAuthorName";

    /**
     * Key of article author URL. 文章作者URL
     */
    public static final String ARTICLE_T_AUTHOR_URL = "articleAuthorURL";

    /**
     * Key of article author intro. 文章作者介绍
     */
    public static final String ARTICLE_T_AUTHOR_INTRO = "articleAuthorIntro";

    /**
     * Key of article comments. 文章评论
     */
    public static final String ARTICLE_T_COMMENTS = "articleComments";

    /**
     * Key of article nice comments. 文章好的评论
     */
    public static final String ARTICLE_T_NICE_COMMENTS = "articleNiceComments";

    /**
     * Key of article participants. 文章参与者
     */
    public static final String ARTICLE_T_PARTICIPANTS = "articleParticipants";

    /**
     * Key of article participant name.文章参与名字
     */
    public static final String ARTICLE_T_PARTICIPANT_NAME = "articleParticipantName";

    /**
     * Key of article participant thumbnail URL.
     */
    public static final String ARTICLE_T_PARTICIPANT_THUMBNAIL_URL = "articleParticipantThumbnailURL";

    /**
     * Key of article participant thumbnail update time.
     */
    public static final String ARTICLE_T_PARTICIPANT_THUMBNAIL_UPDATE_TIME = "articleParticipantThumbnailUpdateTime";

    /**
     * Key of article participant URL.
     */
    public static final String ARTICLE_T_PARTICIPANT_URL = "articleParticipantURL";

    /**
     * Key of is broadcast. 广播
     */
    public static final String ARTICLE_T_IS_BROADCAST = "articleIsBroadcast";

    /**
     * Key of article title with Emoj. 表情
     */
    public static final String ARTICLE_T_TITLE_EMOJI = "articleTitleEmoj";

    /**
     * Key of article title with Emoji unicode.
     */
    public static final String ARTICLE_T_TITLE_EMOJI_UNICODE = "articleTitleEmojUnicode";

    /**
     * Key of article heat.  文章的热度
     */
    public static final String ARTICLE_T_HEAT = "articleHeat";

    /**
     * Key of article ToC.
     */
    public static final String ARTICLE_T_TOC = "articleToC";

    // Anonymous constants
    /**
     * Article anonymous - public.
     */
    public static final int ARTICLE_ANONYMOUS_C_PUBLIC = 0;

    /**
     * Article anonymous - anonymous.
     */
    public static final int ARTICLE_ANONYMOUS_C_ANONYMOUS = 1;

    // Perfect constants
    /**
     * Article perfect - not perfect.
     */
    public static final int ARTICLE_PERFECT_C_NOT_PERFECT = 0;

    /**
     * Article perfect - perfect.
     */
    public static final int ARTICLE_PERFECT_C_PERFECT = 1;

    // Anonymous view constants
    /**
     * Article anonymous view - use global.
     */
    public static final int ARTICLE_ANONYMOUS_VIEW_C_USE_GLOBAL = 0;

    /**
     * Article anonymous view - not allow.
     */
    public static final int ARTICLE_ANONYMOUS_VIEW_C_NOT_ALLOW = 1;

    /**
     * Article anonymous view - allow.
     */
    public static final int ARTICLE_ANONYMOUS_VIEW_C_ALLOW = 2;

    // Status constants
    /**
     * Article status - valid.
     */
    public static final int ARTICLE_STATUS_C_VALID = 0;

    /**
     * Article status - invalid.
     */
    public static final int ARTICLE_STATUS_C_INVALID = 1;

    /**
     * Key of article type. 文章(帖子) 区域类型
     */
    public static final String ARTICLE_AREA_TYPE = "articleAreaType";
    /**
     * Key of article type. 文章(帖子) 语言类型
     */
    public static final String ARTICLE_LANGUAGE_TYPE = "articleLanguageType";

    /**
     * Key of article type. 文章(帖子)类型
     */
    public static final String ARTICLE_TYPE = "articleType";

    /**
     * 区域类型
     */
    // Type constants
    public static final int AREA_TYPE_AMERICA = 0;
    public static final int AREA_TYPE_CHINA = 1;
    public static final int AREA_TYPE_INDIA = 2;
    public static final int AREA_TYPE_SOUTHEASTASIA = 3;
    public static final int AREA_TYPE_EUROPE = 4;
    public static final int AREA_TYPE_AUSTRALIA = 5;
    public static final int AREA_TYPE_CANADA = 6;
    public static final int AREA_TYPE_WESTASIA = 7;
    public static final int AREA_TYPE_AFRICA = 8;
    public static final int AREA_TYPE_SOUTHAMERICA = 9;
    public static final int AREA_TYPE_JAPAN = 10;
    // Type constants
    /**
     * Article type - normal. 文章类型---帖子.小黑屋.同城广播.思绪.书.导购
     */
    public static final int ARTICLE_TYPE_C_NORMAL = 0;
    public static final int ARTICLE_TYPE_C_DISCUSSION = 1;
    public static final int ARTICLE_TYPE_C_CITY_BROADCAST = 2;
    public static final int ARTICLE_TYPE_C_THOUGHT = 3;
    public static final int ARTICLE_TYPE_C_BOOK = 4;
    public static final int ARTICLE_TYPE_C_DEAL = 5;


    /**
     * 语言类型
     */
    public static final int ARTICLE_LANGUAGE_TYPE_C_US = 0;
    public static final int ARTICLE_LANGUAGE_TYPE_C_ZH_CN = 1;
    public static final int ARTICLE_LANGUAGE_TYPE_C_ZH_TW= 2;
    public static final int ARTICLE_LANGUAGE_TYPE_C_FR = 3;
    public static final int ARTICLE_LANGUAGE_TYPE_C_DE= 4;
    public static final int ARTICLE_LANGUAGE_TYPE_C_IT= 5;
    public static final int ARTICLE_LANGUAGE_TYPE_C_JA= 6;
    public static final int ARTICLE_LANGUAGE_TYPE_C_KO= 7;

    /**
     * 爬取网站文章的语言类型
     */
    public static final String LANGUAGES_US = "en_US";
    public static final String LANGUAGES_ZH_CN = "zh_CN";
    public static final String LANGUAGES_ZH_TW = "zh_TW";
    public static final String LANGUAGES_FR = "fr";
    public static final String LANGUAGES_DE = "de";
    public static final String LANGUAGES_IT= "it";
    public static final String LANGUAGES_JA = "ja";
    public static final String LANGUAGES_KO = "ko";

    /**
     * 爬取网站文章的区域
     */
    public  static final String  AREA_CHINA = "China";
    public  static final String  AREA_America= "America";
    public  static final String  AREA_Canada = "Canada";
    public  static final String  AREA_India = "India";
    public  static final String  AREA_SoutheastAsia = "Southeast Asia";
    public  static final String  AREA_Europe = "Europe";
    public  static final String  AREA_Australia = "Australia";
    public  static final String  AREA_WestAsia = "West Asia";
    public  static final String  AREA_Africa = "Africa";
    public  static final String  AREA_SouthAmerica = "South America";
    public  static final String  AREA_Japan= "Japan";

    /**
     * 爬取网站的类型(什么值得买,买个便宜货,惠惠网,逛丢.....)
     */
    //什么值得买
    public static final int WEB_TYPE_SMZDM = 0;
    //买个便宜货
    public static final int WEB_TYPE_MGPYH = 1;
    //惠惠网
    public static final int WEB_TYPE_HUIHUI = 2;
    //逛丢
    public static final int WEB_TYPE_GDUI = 3;
    //slickdeals
    public static final int WEB_TYPE_SLICKDEALS = 4;
    //值值值
    public static final int WEB_TYPE_ZHIZHIZHI = 5;
    //惠喵
    public static  final int WEB_TYPE_HUIMAO = 6;
    //dealsea
    public static  final  int WEB_TYPE_DEALSEA = 7;
    //dealnews
    public static  final  int WEB_TYPE_DEALNEWS = 8;
    //dealgogogo
    public static  final  int WEB_TYPE_DEALGOGOGO = 9;
    //找丢网dealamCn
    public static final  int WEB_TYPE_DEALSCN = 10;
    //dealam
    public static final int WEB_TYPE_DEALAM = 11;

    /**
     * 实体类常量字符串
     */
    public static final String ARTICLE_ID = "articleId";
    public static final String COLLAR_TIME =  "collarTime";
    public static final String ARTICLE_TITLE = "articleTitle";
    public static final String ARTICLE_SUBTITLE = "articlesubtitle";
    public static final String ARTICLE_AUTHOR = "articleAuthor";
    public static final String UPDATE_TIME = "updateTime";
    public static final String ARTICLE_TAG  = "articleTag";
    public static final String SHOPBUY_LINK = "shopBuyLink";
    public static final String ARTICLE_CONTENT = "articleContent";
    public static final String ARTICLE_IMAGE = "articleImage";
    public static final String ARTICLE_AREA_ = "articleArea";
    public static final String ARTICLE_LANGUAGE_ = "articleLanguage";
    public static final String ARTICLE_LINK = "articleLink";
    public static final String ISSUE_STATE = "issueState";
    public static final String WEBSITE_TYPE = "webSiteType";

    /**
     * 文章发布状态
     */
    public static final String ARTICLE_ISSUE = "已发布";
    public static final String ARTICLE_NO_ISSUE = "未发布";
    public static final  String ARTICLE_FAIL = "发布失败";

    /**
     * 实体类
     */
    private int articleId;
    private String collarTime;
    private String articleTitle;
    private String articleSubTitle;
    private String articleAuthor;
    private String updateTime;
    private String articleTag;
    private String shopBuyLink;
    private String articleContent;
    private String articleImage;
    private String articleArea;
    private String articleLanguage;
    private int articleType;
    private String articleLink;
    private String issueState;
    private int webSiteType;

    public int getArticleLanguageType() {
        return articleLanguageType;
    }

    public void setArticleLanguageType(int articleLanguageType) {
        this.articleLanguageType = articleLanguageType;
    }

    private int articleLanguageType;

    public int getArticleAreaType() {
        return articleAreaType;
    }

    public void setArticleAreaType(int articleAreaType) {
        this.articleAreaType = articleAreaType;
    }

    private int articleAreaType;


    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getCollarTime() {
        return collarTime;
    }

    public void setCollarTime(String collarTime) {
        this.collarTime = collarTime;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleSubTitle() {
        return articleSubTitle;
    }

    public void setArticleSubTitle(String articleSubTitle) {
        this.articleSubTitle = articleSubTitle;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(String articleTag) {
        this.articleTag = articleTag;
    }

    public String getShopBuyLink() {
        return shopBuyLink;
    }

    public void setShopBuyLink(String shopBuyLink) {
        this.shopBuyLink = shopBuyLink;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public String getArticleArea() {
        return articleArea;
    }

    public void setArticleArea(String articleArea) {
        this.articleArea = articleArea;
    }



    public String getArticleLanguage() {
        return articleLanguage;
    }

    public void setArticleLanguage(String articleLanguage) {
        this.articleLanguage = articleLanguage;
    }

    public int getArticleType() {
        return articleType;
    }

    public void setArticleType(int articleType) {
        this.articleType = articleType;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    public String getIssueState() {
        return issueState;
    }

    public void setIssueState(String issueState) {
        this.issueState = issueState;
    }

    public int getWebSiteType() {
        return webSiteType;
    }

    public void setWebSiteType(int webSiteType) {
        this.webSiteType = webSiteType;
    }
}
