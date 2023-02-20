package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class wave {
    WebDriver driver;
    WebDriverWait wait;
    private static String label = "(//label[text()='%s'])";
    private static String cart = "//button[@caption='%s']";
    private static String userNameInput = "//input[@name='%s']";
    private static String signInput = "//button[@caption='Sign in']";
    private static String image = "//img[@name='picture_CartList']";
    private static String orderContinue = "//button[@name='%s']";
    private static String Address = "//span[text()='Mid Town 6-3-348, Road No. 1, Banjara Hills Icon']";
    private static String Address2 = "//label[text()='Mid Town 6-3-348, Road No. 1, Banjara Hills,Hyderabad,']";
    private static String Amount = "//label[@name='TotalAmount']";

    private static String submit = "//button[@aria-label='%s']";
    private static String shoPing = "//button[@caption='continue shopping']";


    @BeforeMethod
    public void setUp() {
        System.setProperty("Webdriver.chrome.driver", "G:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        implicitlyWait();
        driver.get("https://www.wavemakeronline.com/run-0dnqs4vt0q/WaveKart_master/#/Main");
    }

    public void dropDown1(By expression, String value) {
        Select dropdown1;
        dropdown1 = new Select(Select(expression));
        dropdown1.selectByVisibleText(value);
    }

    @Test(dataProvider = "products", dataProviderClass = dataProvider.class)
    public void product(String value,String value2) {
        System.out.println("products" + value);
        waveClick(By.xpath(String.format(label, value)));
        waveClick(By.xpath(String.format(cart, "ADD TO CART")));
        waveMethodSendKeys(By.xpath(String.format(userNameInput, "j_username")), "user");
        waveMethodSendKeys(By.xpath(String.format(userNameInput, "j_password")), "user");
        waveClick(By.xpath(String.format(signInput)));
        dropDown1(By.xpath("//select[@aria-expanded='false']"), "2");
        waveClick(By.xpath(String.format(cart, "Place Order")));
        waveClick(By.xpath(String.format(orderContinue, "nextBtn_wizard_payment")));
        verifyElementText(By.xpath(Address), "Mid Town 6-3-348, Road No. 1, Banjara Hills Icon");
        waveClick(By.xpath(String.format(orderContinue, "nextBtn_wizard_payment")));
        waveClick(By.xpath(String.format(orderContinue, "nextBtn_wizard_payment")));
        verifyElementText(By.xpath(Address2), "Mid Town 6-3-348, Road No. 1, Banjara Hills,Hyderabad,");
        waveClick(By.xpath(String.format(orderContinue, "doneBtn_wizard_payment")));
        verifyElementText(By.xpath(String.format(Amount)),value2);
        waveClick(By.xpath(String.format(cart, "cancel order")));
        canCleOrder(By.xpath(String.format(submit, "Submit button")));
        waveClick(By.xpath(String.format(submit, "Submit button")));
        waveClick(By.xpath(shoPing));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void waveClick(By Expression) {
        driver.findElement(Expression).click();

    }

    public void waveMethodSendKeys(By Expression, String user) {
        driver.findElement(Expression).sendKeys(user);
    }

    public WebElement Select(By Expression) {
        return driver.findElement(Expression);
    }

    public void implicitlyWait() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void canCleOrder(By Expression) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Expression));
        driver.findElement(Expression);
    }


    private String verifyElementText(By Expression, String expectedAddress) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Expression));
        String actualAddressText = driver.findElement(Expression).getText();
        String ExpectedAddress = expectedAddress;
        Assert.assertEquals(actualAddressText, ExpectedAddress);
        return ExpectedAddress;
    }
}




