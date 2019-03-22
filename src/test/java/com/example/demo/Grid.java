package com.example.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class Grid {
    @Test
    public void testChrome() throws InterruptedException, MalformedURLException {
        // 创建一个DesiredCapabilities类型
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        // 创建一个driver
        WebDriver driver = new RemoteWebDriver(new URL("http://10.78.3.93:4444/wd/hub"),dc);
        driver.get("http://www.baidu.com");
        Thread.sleep(3000);
        driver.quit();
    }

    /**
     * 数据驱动
     */
    @DataProvider(name = "data")
    public Object[][] test(){
        return new Object[][]{
                {"firefox"},
                {"chrome"}
        };
    }

    @Test(dataProvider =  "data")
    public void testGrid(String bower) throws InterruptedException, MalformedURLException {
        DesiredCapabilities dc = null;
        if(bower.contentEquals("firefox")){
            dc = DesiredCapabilities.firefox();
        }else if(bower.contentEquals("chrome")){
            dc = DesiredCapabilities.chrome();
        }else{
            System.out.println("error");
        }
        WebDriver driver = new RemoteWebDriver(new URL("http://10.78.3.93:5555/wd/hub"),dc);
        driver.get("http://www.baidu.com");
        Thread.sleep(3000);
        driver.quit();
    }
}
