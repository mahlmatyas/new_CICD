import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class App {

    public WebDriver driver;

    @BeforeEach
    void SetUp(){
        System.setProperty("webdriver.chrome.driver","c:/webdriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to("https://www.seleniumeasy.com/test");
        driver.manage().window().maximize();
    }

    @Test
    void TestIntLand(){

        // navigate to Table Sort And Search Demo
        driver.findElement(By.xpath("//*[@id=\"navbar-brand-centered\"]/ul[1]/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"navbar-brand-centered\"]/ul[1]/li[3]/ul/li[4]/a")).click();

        //  25 -->
        Select select = new Select(driver.findElement(By.xpath("//*[@id=\"example_length\"]/label/select")));
        select.selectByValue("100");

        // collecting
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"example\"]/tbody/tr"));
        List<String> nameList = new ArrayList<>();
        for (WebElement name : list) {
            nameList.add(name.getText());
        }

        //ordering
        driver.findElement(By.xpath("//*[@id=\"example\"]/thead/tr/th[4]")).click();
        driver.findElement(By.xpath("//*[@id=\"example\"]/thead/tr/th[4]")).click();

        // 65<66
        String attribute = driver.findElement(By.xpath("//*[@class='sorting_desc']")).getAttribute("aria-sort");
        String age = driver.findElement(By.xpath("//*[@id=\"example\"]/tbody/tr[1]/td[4]")).getText();

        // asserts
        Assertions.assertTrue(nameList.size() > 25);
        Assertions.assertEquals("descending",attribute);
        Assertions.assertTrue(Integer.parseInt(age) > 65);
    }

    @AfterEach
    void TearDowd(){
        driver.quit();
    }
}
