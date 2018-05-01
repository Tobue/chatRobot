package com.chatRobot.utils;

import com.chatRobot.controller.ArticleController;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import sun.rmi.runtime.Log;


import java.io.File;
import java.io.IOException;


/**
 * chromeDriver是谷歌的浏览器驱动，用来适配Selenium,有图形页面存在，在调试爬虫下载运行的功能的时候会相对方便
 * @author zhuangj
 * @date 2017/11/14
 */
public class ChromeDriver {

    private static ChromeDriverService service;

    public static String getChromeDriver(String showBuyLink){
       /* System.setProperty("webdriver.chrome.driver","C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
        service = new ChromeDriverService.Builder().usingDriverExecutable(new File("G:\\chromedriver.exe")).usingAnyFreePort().build();*/
        System.setProperty("webdriver.chrome.driver","/opt/giraffe/chatRobot/chrome/chrome-linux/google-chrome-stable_current_amd64.deb");
        service = new ChromeDriverService.Builder().usingDriverExecutable(new File("/opt/giraffe/chatRobot/chrome/chromedriver_linux32/chromedriver")).usingAnyFreePort().build();
        WebDriver driver = null;
        String currentUrl = null;
        try {
            service.start();
            driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
            driver.get(showBuyLink);
            currentUrl = driver.getCurrentUrl();
            System.out.println(currentUrl);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            service.stop();
        }
        return currentUrl;
    }

}