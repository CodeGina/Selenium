package com.example.demo;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DemoApplicationTests {

    WebDriver driver;

    @BeforeMethod
    public void openChrome(){
        // 根据Chrome的版本下载chromedriver
        System.setProperty("webdriver.chrome.driver","D:\\IdeaProject\\demo\\drivers\\chromedriver.exe");
        // 实例化
        driver = new ChromeDriver();
    }

    @Test
    public void demo(){
        System.out.println("h惚红火火恍恍惚惚");
    }

    @Test
    public void ChromeDemo() throws InterruptedException {
        // 打开百度页面
        driver.get("http://www.baidu.com");
        Thread.sleep(3000);
        // 后退
        driver.navigate().back();
        Thread.sleep(3000);
        // 刷新
        driver.navigate().refresh();
        // 前进
        driver.navigate().forward();
        Thread.sleep(3000);
        // 关闭当前窗口
        // driver.close();
    }

    @Test
    public void winDemo() throws InterruptedException {
        // 实例化窗口。设置窗口大小
        Dimension dimension = new Dimension(300,300);
        driver.manage().window().setSize(dimension);
        Thread.sleep(3000);
        // 窗口最大化
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    @Test
    public void demo1(){
        // 打开百度页面
        driver.get("http://www.baidu.com");
        // 获取当前的url
        String url = driver.getCurrentUrl();
        System.out.println(url);
        Assert.assertEquals("https://www.baidu.com/",url);
    }

    @AfterMethod
    public void closeChrome() {
        // 完全关闭，结束进程
        driver.quit();
    }

}
