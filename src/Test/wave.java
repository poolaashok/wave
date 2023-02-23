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
import java.util.concurrent.TimeUnit;

public class wave {
    WebDriver driver;
    WebDriverWait wait;
    private static String label = "//label[text()='%s']";
    private static String LABEL_WITH_NAME = "//label[@name='%s']";
    private static String userNameInput = "//input[@name='%s']";
    private static String signInput = "//button[@caption='%s']";
    private static String productImage = "//img[@name='picture_CartList']";
    private static String orderContinue = "//button[@name='%s']";
    private static String Address = "//span[text()='Mid Town 6-3-348, Road No. 1, Banjara Hills Icon']";
    private static String orderStatus = "//li[@listitemindex='0']//label[@name='oStatus']";
    private static String submitButton = "//button[@aria-label='%s']";
    private static String ordered = "Ordered";
    private static String UserAddress = "Mid Town 6-3-348, Road No. 1, Banjara Hills Icon";
    private static String shiPPingAddress = "Mid Town 6-3-348, Road No. 1, Banjara Hills,Hyderabad,";
    private static String cancel = "Cancelled";
    private static String available = "Available";
    private static String COD = "Cash On Delivery(COD)";


    @BeforeMethod
    public void setUp() {
        System.setProperty("Webdriver.chrome.driver", "G:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        implicitlyWait();
        driver.get("https://www.wavemakeronline.com/run-0dnqs4vt0q/WaveKart_master/#/Main");
    }


    @Test(dataProvider = "products", dataProviderClass = dataProvider.class)
    public void verIfyProducts(String productName, String price){
        addToCart(productName);
        placeOrder(productName,price);
        canCelOrder();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void click(By Expression) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Expression));
        driver.findElement(Expression).click();

    }

    public void waveMethodSendKeys(By Expression, String user) {
        driver.findElement(Expression).sendKeys(user);
    }
    public void dropDown(By expression, String value) {
        Select dropdown;
        dropdown = new Select(Select(expression));
        dropdown.selectByVisibleText(value);
    }

    public WebElement Select(By Expression) {
        return driver.findElement(Expression);
    }

    public void implicitlyWait() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    private void verifyElementText(By Expression, String expectedText) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Expression));
        String actualAddressText = driver.findElement(Expression).getText();
        Assert.assertEquals(actualAddressText, expectedText);
        System.out.println(expectedText);
    }

    public void addToCart(String productName) {
        click(By.xpath(String.format(label, productName)));
        verifyElementText(By.xpath(String.format(LABEL_WITH_NAME,"lbl_Available")), available);
        verifyElementText(By.xpath(String.format(LABEL_WITH_NAME, "Name")), productName);
        click(By.xpath(String.format(signInput, "ADD TO CART")));
        waveMethodSendKeys(By.xpath(String.format(userNameInput, "j_username")), "user");
        waveMethodSendKeys(By.xpath(String.format(userNameInput, "j_password")), "user");
        click(By.xpath(String.format(signInput,"Sign in")));
        dropDown(By.xpath("//select[@aria-expanded='false']"), "2");
        verifyElementText(By.xpath(String.format(LABEL_WITH_NAME, "Name")), productName);
    }
    public void placeOrder(String productName,String price){
        click(By.xpath(String.format(signInput, "Place Order")));
        click(By.xpath(String.format(orderContinue, "nextBtn_wizard_payment")));
        verifyElementText(By.xpath(String.format(Address)), "Mid Town 6-3-348, Road No. 1, Banjara Hills Icon");
        click(By.xpath(String.format(orderContinue, "nextBtn_wizard_payment")));
        click(By.xpath(String.format(orderContinue, "nextBtn_wizard_payment")));
        verifyElementText(By.xpath(String.format(label,"Mid Town 6-3-348, Road No. 1, Banjara Hills,Hyderabad,")), "Mid Town 6-3-348, Road No. 1, Banjara Hills,Hyderabad,");
        click(By.xpath(String.format(orderContinue, "doneBtn_wizard_payment")));
        verifyElementText(By.xpath(String.format(label, "Cash On Delivery(COD)")),COD);
        verifyElementText(By.xpath(String.format(LABEL_WITH_NAME,"TotalAmount")), price);
        verifyElementText(By.xpath(String.format(LABEL_WITH_NAME, "productName")), productName);
        verifyElementText(By.xpath(String.format(orderStatus)), ordered);
    }
    public void canCelOrder() {
        click(By.xpath(String.format(signInput, "cancel order")));
        click(By.xpath(String.format(submitButton, "Submit button")));
        click(By.xpath(String.format(submitButton, "Submit button")));
        click(By.xpath(String.format(signInput, "continue shopping")));
    }

}







