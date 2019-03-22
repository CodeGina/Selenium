package com.example.demo;

import com.po.page.LoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSTest {

    WebDriver driver;

    @BeforeMethod
    public void openPhatomJs() {
        System.setProperty("webdriver.chrome.driver","D:\\IdeaProject\\demo\\drivers\\chromedriver.exe");
        // 实例化
        driver = new ChromeDriver();
    }

    /**
     * 执行JS
     */
    @Test
    public void exJs(){
        driver.get("http://www.baidu.com");
        // 把driver转换成JavascriptExecutor类型
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('kw').setAttribute('value','123')");
    }

    /**
     * 163邮箱注册
     * @throws InterruptedException
     */
    @Test
    public void registTest() throws InterruptedException {
        driver.get("https://mail.163.com/");
        Thread.sleep(3000);
        // driver控制权交个frame
        WebElement element = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(element);
        // 点击注册按钮
        driver.findElement(By.id("changepage")).click();
        // 获取driver当前页面的句柄值
        String handle = driver.getWindowHandle();
        // driver控制权交给新的窗口
        for (String handles : driver.getWindowHandles()){
            if (handles.equals(handle))
                continue;
            driver.switchTo().window(handles);
        }
        driver.findElement(By.id("nameIpt")).sendKeys("AA123123123");
        driver.findElement(By.id("mainPwdIpt")).sendKeys("123123123");
        driver.findElement(By.id("nameIpt")).sendKeys("123123123");
        driver.findElement(By.id("mainCfmPwdIpt")).sendKeys("123123123");
        driver.findElement(By.id("mainCfmPwdIpt")).sendKeys("123123123");
        driver.findElement(By.id("mainMobileIpt")).sendKeys("123123123");
        driver.findElement(By.id("vcodeIpt")).sendKeys("123123123");
        driver.findElement(By.id("mainAcodeIpt")).sendKeys("123123123");

        //显示等待
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"m_mainMobile\"]/span")));

        String error = driver.findElement(By.xpath("//*[@id=\"m_mainMobile\"]/span")).getText();
        Assert.assertEquals(error,"  请填写正确的手机号");
    }

    @Test
    public void loginTest() throws InterruptedException {
        login(driver,"13612415192","13612415192n");
        driver.get("https://mail.163.com/");
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(element);
        driver.findElement(LoginPage.emailInput).sendKeys("13612415192");
        driver.findElement((LoginPage.pwdInput)).sendKeys("13612415192n");
        driver.findElement(LoginPage.loginButton).click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"nerror\"]/div[2]")));

        String s = driver.findElement(By.xpath("//*[@id=\"nerror\"]/div[2]")).getText();
        Assert.assertEquals(s,"帐号或密码错误");

        //WebDriverWait wait = new WebDriverWait(driver,10);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("退出")));

        //*[@id="nerror"]/div[2]



        //String s = driver.findElement(By.linkText("退出")).getText();
        //Assert.assertEquals(s,"退出");

    }

    public static void login(WebDriver driver, String email, String pwd) throws InterruptedException {
        driver.get("https://mail.163.com/");
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(element);
        driver.findElement(LoginPage.emailInput).sendKeys(email);
        driver.findElement((LoginPage.pwdInput)).sendKeys(pwd);
        driver.findElement(LoginPage.loginButton).click();
    }

    @Test
    public void sendEmailTest() throws InterruptedException {
        login(driver,"13612415192","13612415192n");

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"dvNavTop\"]/ul/li[2]/span[2]")));
        // 点击写信
        driver.findElement(By.xpath("//*[@id=\"dvNavTop\"]/ul/li[2]/span[2]")).click();
        // 收件人，并输入
        driver.findElement(By.xpath("//*[@aria-label=\"收件人地址输入框，请输入邮件地址，多人时地址请以分号隔开\"]")).sendKeys("13612415192@163.com");
        // 主题，并输入
        driver.findElement(By.xpath("//*[@aria-label=\"邮件主题输入框，请输入邮件主题\"]/input")).sendKeys("这是个主题");
        // 上传附件
        driver.findElement(By.xpath("//*[@title=\"点击添加附件\"]/input")).sendKeys("D:\\Users\\ASUS\\Desktop\\images\\logo_02.png");

        // driver转交控制权给frame
        WebElement element = driver.findElement(By.className("APP-editor-iframe"));
        driver.switchTo().frame(element);
        // 富文本框输入
        driver.findElement(By.xpath("/html/body")).sendKeys("123123123");
        // driver控制权回到原来
        driver.switchTo().defaultContent();
        // 点击发送
        driver.findElement(By.xpath("//*[@class=\"jp0\"]/div[1]/span[2]")).click();

    }

    @AfterMethod
    public void closeChrome() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
