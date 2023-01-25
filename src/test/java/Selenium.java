import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Selenium {

    private static ChromeDriver driver1;
    private static ChromeDriver driver;
    private static ChromeOptions options;
   private static Actions actions;
    @BeforeClass
    public void setDriver(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\Automation Course\\chromedriver.exe");
        driver = new ChromeDriver();
        options = new ChromeOptions();
        actions = new Actions(driver);
    }

    @Test
    public void Test01_OpenInternet(){
        driver.get("https://dgotlieb.github.io/WebCalculator/");
    }

    @Test
    public void Test02_Print7Dimension(){
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        WebElement num7 = driver.findElement(By.id("seven"));
        System.out.println("Number 7 X: " + num7.getRect().x);
        System.out.println("Number 7 y: " + num7.getRect().y);
        System.out.println("Number 7 height: " + num7.getRect().height);
        System.out.println("Number 7 width: " + num7.getRect().width);

    }

    @Test
    public void Test03_6IsDisplay(){
        WebElement num6 = driver.findElement(By.id("six"));
        System.out.println("Number 6 is display: " + num6.isDisplayed());
    }

    @Test
    public void Test04_Calculation(){
        WebElement num5 = driver.findElement(By.id("five"));
        WebElement num2 = driver.findElement(By.id("two"));
        WebElement num3 = driver.findElement(By.id("three"));
        WebElement add = driver.findElement(By.id("add"));
        WebElement equal = driver.findElement(By.id("equal"));
        WebElement screen = driver.findElement(By.id("screen"));
        WebElement clear = driver.findElement(By.id("clear"));
        num2.click();
        add.click();
        num3.click();
        equal.click();
        WebElement screenAfterAddition = screen;
        Assert.assertEquals(screen.getText(),"5");
    }

    @Test
    public void Test05_AssertionWebURL(){
        Assert.assertEquals(driver.getCurrentUrl(),"https://dgotlieb.github.io/WebCalculator/");
    }

    @Test
    public void Test06_AssertionWebTitle(){
        String webTitle = driver.getTitle();
        driver.navigate().refresh();
        Assert.assertEquals(driver.getTitle(),webTitle);
    }

    @Test
    public void Test07_OpenChromeWithoutExtensions(){
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
        driver.navigate().to("https://dgotlieb.github.io/WebCalculator/");
    }

    @Test
    public void Test08_Actions(){
        driver.navigate().to("https://dgotlieb.github.io/Actions");
        WebElement webElement = driver.findElement(By.id("close"));
        try {
            File screenShotFile = webElement.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShotFile, new File("element-screenshot.png")); // save screenshot to disk
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test09_DoubleClick(){
//        WebElement webElement = driver.findElement(By.xpath("/html/body/p[2]"));
//        Actions myAction = new Actions(driver);
//        myAction.doubleClick(webElement);
//        myAction.perform();
    }



    @Test
    public void Test10_mouseHover(){
        driver.navigate().to("https://dgotlieb.github.io/Actions");
        Actions actions1 = new Actions(driver);
        WebElement webElement = driver.findElement(By.id("close"));
        actions1.moveToElement(webElement).perform();
    }
    @Test
    public void Test11_selectMultiple(){
        driver.navigate().to("https://dgotlieb.github.io/Actions");
        List<WebElement> foods = driver.findElements(By.name("kind"));
        System.out.println(foods.size());
        Actions myAction = new Actions(driver);
        myAction.clickAndHold(foods.get(0)).clickAndHold(foods.get(1));
        myAction.build().perform();
    }
    @Test
    public void Test12_uploadFile(){
        driver.findElement(By.name("pic")).sendKeys("C:\\Users\\user\\Desktop\\1.txt");
    }

    @Test
    public void Test13_ScrollDown(){
        driver.navigate().to("https://dgotlieb.github.io/Actions");
        WebElement element = driver.findElement(By.id("clickMe"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        driver.navigate().refresh();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(8,881)");
    }

    @Test
    public void Test14_QuestionNum6(){
        // select from dropdown, Print all elements in dropdown
        driver.navigate().to("https://dgotlieb.github.io/Controllers/");
        driver.findElement(By.cssSelector("input[value=Cheese]")).click();
        Select select = new Select(driver.findElement(By.name("dropdownmenu")));
        select.selectByIndex(0);
        System.out.println("Your Selection is: " + select.getFirstSelectedOption().getText() + "\n\n");

        for(int i = 0; i<select.getOptions().size(); i++){
            System.out.println(select.getOptions().get(i).getText());
        }
    }

    @Test
    public void Test15_QuestionNum7(){
//         print button height and width
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        WebElement num2 = driver.findElement(By.id("two"));
        WebElement num6 = driver.findElement(By.id("six"));
        System.out.println("Number 2 height is: " + num2.getRect().height);
        System.out.println("Number 6 width is: " + num6.getRect().width);
    }

    @Test
    public void Test16_QuestionNum8_Bonus(){
        driver.get("https://www.themarker.com/");
        int size = driver.getPageSource().split("news").length;
        System.out.println(size);
    }

    @Test
    public void Test17_QuestionNum9_Bonus(){
        driver.get("https://www.themarker.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.print();");
    }
    
    @AfterClass
    public void close(){
//        driver.close();
    }

}
