package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JUnitTest {

    WebDriver driver;

    /**
     * frame处理
     * 1.定位frame
     * 2.driver控制权交给frame
     * 3.操作frame里面的元素
     * 4.driver控制权交给原来界面
     */
    @Test
    public void frameTest() throws InterruptedException {
        // 根据Chrome的版本下载chromedriver
        System.setProperty("webdriver.chrome.driver","D:\\IdeaProject\\demo\\drivers\\chromedriver.exe");
        // 实例化
        driver = new ChromeDriver();
        driver.get("file:///F:/0.0/%E6%96%B0%E5%BB%BA%E6%96%87%E4%BB%B6%E5%A4%B9%20(2)/selenium+java%E8%87%AA%E5%8A%A8%E5%8C%96%EF%BC%88%E6%9C%80%E6%96%B0%EF%BC%89/%E6%BA%90%E7%A0%81/webdriver_demo/selenium_html/index.html");
        // 通过id或name方式，转交控制权
//       driver.switchTo().frame("aa");
        // 通过WebElement方式。转交控制权
        WebElement element = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(element);
        driver.findElement(By.linkText("baidu")).click();
        Thread.sleep(3000);
        // driver控制权交给原来界面
        driver.switchTo().defaultContent();
        driver.findElement(By.linkText("登陆界面")).click();
        Thread.sleep(3000);

        driver.quit();
    }
}
