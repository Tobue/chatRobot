package com.chatRobot.utils;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;   
   
/**  
 * Created with IntelliJ IDEA.  
 * User: lsz  
 * Date: 14-4-22  
 * Time: 下午1:17  
 * utils for http  
 */   
public class HttpUtils {
    public static String getShowBuyLink(String showBuyLink){
        //System.setProperty("phantomjs.binary.path", "G:\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        System.setProperty("phantomjs.binary.path", "/opt/giraffe/chatRobot/chrome/phantomjs-2.1.1-linux-x86_64/bin/phantomjs");
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.phantomjs();
        WebDriver driver = new PhantomJSDriver(desiredCapabilities);
        driver.get(showBuyLink);
        String currentUrl = driver.getCurrentUrl();
        driver.close();
        return currentUrl;
    }
}  