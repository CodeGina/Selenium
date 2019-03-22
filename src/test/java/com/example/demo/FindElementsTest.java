package com.example.demo;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindElementsTest {
    WebDriver driver;
    // @BeforeMethod在每个Test使用前都会调用一次
    @BeforeMethod
    public void openChrome(){
        // 根据Chrome的版本下载chromedriver
        System.setProperty("webdriver.chrome.driver","D:\\IdeaProject\\demo\\drivers\\chromedriver.exe");
        // 实例化
        driver = new ChromeDriver();
        // 全局等待 等findElement    10为等待的时间，单位为秒
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     *  我要点击百度首页新闻的链接
     *  那么我打开了新闻页面
     */
    @Test
    public void clickTest() {
        driver.get("http://www.baidu.com");
        WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[3]/a[1]"));
//        WebElement element = driver.findElement(By.linkText("新闻"));
        element.click();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,"http://news.baidu.com/");
    }

    @Test
    public void getATest() {
        driver.get("http://www.baidu.com");
        String s = driver.findElement(By.id("su")).getAttribute("value");
        Assert.assertEquals(s,"百度一下");
    }

    /**
     *  截图
     *  截百度首页（图片大小是打开的窗口大小）
     */
    @Test
    public void sort(){
        driver.get("http://www.baidu.com");
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("D:\\test1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击alert按钮
     * 在alert警告框点击确定按钮
     * @throws InterruptedException
     */
    @Test
    public void alertTest() throws InterruptedException {
        driver.get("file:///F:/0.0/%E6%96%B0%E5%BB%BA%E6%96%87%E4%BB%B6%E5%A4%B9%20(2)/selenium+java%E8%87%AA%E5%8A%A8%E5%8C%96%EF%BC%88%E6%9C%80%E6%96%B0%EF%BC%89/%E6%BA%90%E7%A0%81/webdriver_demo/selenium_html/index.html");
        driver.findElement(By.className("alert")).click();
        // 最好先等待一秒，再点击alter的确定按钮
        Thread.sleep(1000);
        // 把控制权交给Alert
        Alert alert = driver.switchTo().alert();
        // 获取警告框的文本值
        String s = alert.getText();
        // 点击Alert的确定按钮
        alert.accept();
        Assert.assertEquals(s,"请点击确定");
    }

    /**
     * 在Confirm警告框点击取消按钮
     * 再次点击确定按钮
     */
    @Test
    public void comfirmTest() throws InterruptedException {
        driver.get("file:///F:/0.0/%E6%96%B0%E5%BB%BA%E6%96%87%E4%BB%B6%E5%A4%B9%20(2)/selenium+java%E8%87%AA%E5%8A%A8%E5%8C%96%EF%BC%88%E6%9C%80%E6%96%B0%EF%BC%89/%E6%BA%90%E7%A0%81/webdriver_demo/selenium_html/index.html");
        driver.findElement(By.className("confirm")).click();
        Thread.sleep(1000);
        // driver控制权转交给Alert
        Alert alert = driver.switchTo().alert();
        // 点击取消
        alert.dismiss();
        Thread.sleep(1000);
        // 点击确定
        alert.accept();
    }

    /**
     * D:\IdeaProject\demo\drivers\geckodriver.exe
     * 在Prompt弹框中，输入"这个是Prompt"
     * 点击确定按钮
     * 再次点击确定按钮
     */
    @Test
    public void promptTest() throws InterruptedException {
        driver.get("file:///F:/0.0/%E6%96%B0%E5%BB%BA%E6%96%87%E4%BB%B6%E5%A4%B9%20(2)/selenium+java%E8%87%AA%E5%8A%A8%E5%8C%96%EF%BC%88%E6%9C%80%E6%96%B0%EF%BC%89/%E6%BA%90%E7%A0%81/webdriver_demo/selenium_html/index.html");
        driver.findElement(By.className("prompt")).click();
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("这个是Prompt");
        // chromedriver的bug，不能看到输入的文字
        Thread.sleep(3000);
        alert.accept();
        Thread.sleep(3000);
        alert.accept();
        Thread.sleep(3000);
    }

    /**
     * 下拉框
     */
    @Test
    public void selectedTest() throws InterruptedException {
        driver.get("file:///F:/0.0/%E6%96%B0%E5%BB%BA%E6%96%87%E4%BB%B6%E5%A4%B9%20(2)/selenium+java%E8%87%AA%E5%8A%A8%E5%8C%96%EF%BC%88%E6%9C%80%E6%96%B0%EF%BC%89/%E6%BA%90%E7%A0%81/webdriver_demo/selenium_html/index.html");
        WebElement element = driver.findElement(By.id("moreSelect"));
        // 实例化 Select类
        Select select = new Select(element);
        // 通过索引选择下拉框
        select.selectByIndex(2);
        Thread.sleep(3000);
        // 通过属性value值选择下拉框
        select.selectByValue("huawei");
        Thread.sleep(3000);
        // 通过文本值选择下拉框
        select.selectByVisibleText("iphone");
        Thread.sleep(3000);
    }

    /**
     * 多窗口处理
     */
    @Test
    public void winTest() throws InterruptedException {
        driver.get("file:///F:/0.0/%E6%96%B0%E5%BB%BA%E6%96%87%E4%BB%B6%E5%A4%B9%20(2)/selenium+java%E8%87%AA%E5%8A%A8%E5%8C%96%EF%BC%88%E6%9C%80%E6%96%B0%EF%BC%89/%E6%BA%90%E7%A0%81/webdriver_demo/selenium_html/index.html");
        driver.findElement(By.linkText("Open new window")).click();
        Thread.sleep(3000);

        // 显示等待
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Open new window")));

        // 获取当前driver所在的页面的句柄值
        String handel1 = driver.getWindowHandle();
        // for循环判断获取到的handles是否等于handel1
        for (String handles : driver.getWindowHandles()){
            if(handles.equals(handel1)){
                continue;
            }else {
                driver.switchTo().window(handles);
            }
        }
        driver.findElement(By.linkText("baidu")).click();
    }

    /**
     * 打开百度页面
     * 在百度一下按钮上点右键
     */
    @Test
    public void actionTest() throws InterruptedException {
        driver.get("http://www.baidu.com");
        WebElement element = driver.findElement(By.id("su"));
        // 实例化Actions
        Actions actions = new Actions(driver);
        // 在百度一下按钮右键，不传element，chrome默认在左上角右键
        //actions.contextClick(element).perform();
        actions.moveToElement(element).perform();
        Thread.sleep(3000);
        // 双击，不传element，默认在鼠标当前位置双击
        //actions.doubleClick(element).perform();
    }

    @Test
    public void dragTest() throws InterruptedException {
        driver.get("file:///F:/0.0/%E6%96%B0%E5%BB%BA%E6%96%87%E4%BB%B6%E5%A4%B9%20(2)/selenium+java%E8%87%AA%E5%8A%A8%E5%8C%96%EF%BC%88%E6%9C%80%E6%96%B0%EF%BC%89/%E6%BA%90%E7%A0%81/webdriver_demo/selenium_html/dragAndDrop.html");
        WebElement element = driver.findElement(By.id("drag"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(element,500,500).perform();
    }

    @Test
    public void dragTest2() throws InterruptedException {
        driver.get("file:///F:/0.0/%E6%96%B0%E5%BB%BA%E6%96%87%E4%BB%B6%E5%A4%B9%20(2)/selenium+java%E8%87%AA%E5%8A%A8%E5%8C%96%EF%BC%88%E6%9C%80%E6%96%B0%EF%BC%89/%E6%BA%90%E7%A0%81/webdriver_demo/selenium_html/dragAndDrop.html");
        WebElement h1 = driver.findElement(By.tagName("h1"));
        WebElement element = driver.findElement(By.id("drag"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(element).  // 按住
                moveToElement(h1).      // 移动
                release().              // 释放
                perform();              // 执行
    }

    /**
     *  多选选择第一个和第三个
     */
    @Test
    public void moreSelectTest() {
        driver.get("file:///F:/0.0/%E6%96%B0%E5%BB%BA%E6%96%87%E4%BB%B6%E5%A4%B9%20(2)/selenium+java%E8%87%AA%E5%8A%A8%E5%8C%96%EF%BC%88%E6%9C%80%E6%96%B0%EF%BC%89/%E6%BA%90%E7%A0%81/webdriver_demo/selenium_html/index.html");
        WebElement element = driver.findElement(By.id("selectWithMultipleEqualsMultiple"));
        List<WebElement> elementList = driver.findElements(By.xpath("//*[@id=\"selectWithMultipleEqualsMultiple\"]/option"));
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .click(elementList.get(0))
                .click(elementList.get(2))
                .keyUp(Keys.CONTROL)
                .perform();
    }

    /**
     * 下载百度页面
     * @throws AWTException
     */
    @Test
    public void saveHtml() throws AWTException {
        driver.get("http://www.baidu.com");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
//        int keyS = (int)new Character('S');
//        robot.keyPress(keyS);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyPress(KeyEvent.VK_ENTER);
    }


    @AfterMethod
    public void closeChrome() throws InterruptedException {
        Thread.sleep(3000);
        // 完全关闭，结束进程
        driver.quit();
    }
}
