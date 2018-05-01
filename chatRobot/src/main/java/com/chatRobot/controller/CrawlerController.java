package com.chatRobot.controller;

import com.chatRobot.processor.*;
import org.apache.commons.collections.iterators.AbstractMapIteratorDecorator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * Created by Administrator on 2017/11/18.
 */
@Controller
@RequestMapping("/crawler")
@Component
public class CrawlerController {

    /**
     * 什么值得买
     */
    @RequestMapping("/smzdm")
    @Scheduled(cron = "0 0 6,9,11,13,15,16,17 * * ?")
    public void smzdmRun(){
        Spider spider = null;
        for (int i = 1; i < 10; i++) {
            String url = "https://www.smzdm.com/p" +i;
            spider = Spider.create(new SmzdmProcessor());
            spider.addUrl(url);
            spider.thread(8);
            spider.run();
        }
        spider.stop();
    }

    /**
     * 买个便宜货
     */
    @RequestMapping("/mgpyh")
    @Scheduled(cron = "0 0 6,9,11,13,15,16,17 * * ?")
    public void mgpyhRun() {
        Spider spider = null;
        for (int i = 1; i < 10; i++) {
            String url = "https://www.mgpyh.com/post/?page=" + i;
            spider = Spider.create(new MgpyhProcessor());
            spider.addUrl(url);
            spider.thread(8);
            spider.run();
        }
        spider.stop();
    }

    /**
     * 惠惠网
     */
    @RequestMapping("/huihui")
    @Scheduled(cron = "0 0 6,9,11,13,15,16,17 * * ?")
    public void huihuiRun(){
        Spider spider = null;
        for (int i = 1; i < 10; i++) {
            String url = "http://www.huihui.cn/all?page=" + i;
            spider = Spider.create(new HuiHuiProcessor());
            spider.addUrl(url);
            spider.thread(5);
            spider.run();
        }
        spider.stop();

    }

    /**
     * 逛丢网
     */
    @RequestMapping("/gdui")
    @Scheduled(cron = "0 0 6,9,11,13,15,16,17 * * ?")
    public void gduiRun(){
        Spider spider = null;
        for (int i = 1; i < 10; i++) {
            String url = "https://guangdiu.com/index.php?p=" + i;
            spider = Spider.create(new GDuiProcessor());
            spider.addUrl(url);
            spider.thread(1);
            spider.run();
        }
        spider.stop();
    }

    /**
     * slickdeals
     */
    @RequestMapping("/slickdeals")
    @Scheduled(cron = "0 0 6,9,11,13,15,16,17 * * ?")
    public void slickdealsRun(){
        Spider spider = null;
        for (int i = 1; i < 20; i++) {
             String url = "https://slickdeals.net/?page=" + i;
             spider = Spider.create(new SlickdealsProcessor());
             spider.addUrl(url);
             spider.thread(8);
             spider.run();
        }
        spider.stop();
    }

    /**
     * 值值值
     */
    @RequestMapping("/zhizhizhi")
    @Scheduled(cron = "0 0 6,9,11,13,15,16,17 * * ?")
    public void zhizhizhiRun(){
        Spider spider= null;
        for (int i = 1; i < 10; i++) {
            String url = "http://zhizhizhi.com/page/"+i+"/";
            spider = Spider.create(new ZhizhizhiProcessor());
            spider.addUrl(url);
            spider.thread(8);
            spider.run();
        }
        spider.stop();
    }

    /**
     * 惠喵
     */
    @RequestMapping("/huimao")
    @Scheduled(cron = "0 0 6,9,11,13,15,16,17 * * ?")
    public void huimaoRun(){
        Spider spider = null;
        for (int i = 1; i < 15; i++) {
            String url = "http://www.huim.com/kuaibao/page/"+i;
            spider = Spider.create(new HuimaoProcessor());
            spider.addUrl(url);
            spider.thread(8);
            spider.run();
        }
        spider.stop();
    }

    /**
     * dealsea
     */
    @RequestMapping("/dealsea")
    @Scheduled(cron = "0 0 6,9,11,13,15,16,17 * * ?")
    public void dealseaRun(){
        Spider spider = null;
        for (int i = 2; i < 10; i++) {
            String url = "https://dealsea.com/?page="+i;
            spider = Spider.create(new DealseaProcessor());
            spider.addUrl(url);
            spider.thread(8);
            spider.run();
        }
        spider.stop();
    }

    /**
     * dealnews
     */
    @RequestMapping("/dealnews")
    @Scheduled(cron = "0 0 6,9,11,13,15,16,17 * * ?")
    public void dealnewsRun(){
        String url = "https://www.dealnews.com";
        Spider spider = Spider.create(new DealnewsProcessor());
        spider.addUrl(url);
        spider.thread(5);
        spider.run();
        spider.stop();
    }


    /**
     * dealgogogo
     */
    @RequestMapping("/dealgogogo")
    @Scheduled(cron = "0 0 6,9,11,13,15,16,17 * * ?")
    public void dealgogogoRun(){
        Spider spider = null;
        for (int i = 1; i < 10; i++) {
            String url = "http://dealgogogo.com/?pageNum="+i;
            spider = Spider.create(new DealgogogoProcessor());
            spider.addUrl(url);
            spider.thread(8);
            spider.run();
        }
        spider.stop();
    }

    /**
     * 找丢网  http://cn.dealam.com/?stat=2.1.25.6&p=6
     */
    @RequestMapping("/dealamCn")
    @Scheduled(cron = "0 0 6,9,11,13,15,16,17 * * ?")
    public void dealamCnRun(){
        Spider spider = null;
        for (int i = 1; i < 10; i++) {
            String url = "http://cn.dealam.com/?stat=2.1.25.6&p="+i;
            spider = Spider.create(new DealamCnProcessor());
            spider.addUrl(url);
            spider.thread(8);
            spider.run();
        }
        spider.stop();
    }

    /**
     * dealam  http://cn.dealam.com/?stat=2.1.25.6&p=6
     */
    @RequestMapping("/dealam")
    @Scheduled(cron = "0 0 6,9,11,13,15,16,17 * * ?")
    public void dealamRun(){
        Spider spider = null;
        for (int i = 1; i < 10; i++) {
            String url = "http://www.dealam.com/?stat=12.2.25.2&p="+i;
            spider = Spider.create(new DealamProcessor());
            spider.addUrl(url);
            spider.thread(8);
            spider.run();
        }
        spider.stop();
    }
}
