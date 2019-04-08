import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.*;

class AmazonTest {

    //To get the values from the excel sheet
    HashMap<String,String> values = Amazon.getList();

    public static void takeSnapShot(WebDriver webdriver,String fileWithPath){

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //System.out.println(SrcFile.getAbsolutePath());
        //Move image file to new destination
        File DestFile=new File(fileWithPath);
        //System.out.println(DestFile.getPath());

        //Copy file at destination
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        }catch (IOException e) {

            System.err.println("An IOException was caught :"+e.getMessage());
        }
    }

    @Test
    public void testcase1_amazon_create_account() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Balaji\\chrome\\chromedriver.exe");
        WebDriver driver  = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Create\\homepage.png");
        //Wait
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='nav-link-accountList']/span[1]")));
        // Clicking account sign-in
        WebElement signIn = driver.findElement(By.xpath("//*[@id='nav-link-accountList']/span[1]"));
        signIn.click();
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Create\\signin.png");
        //CreateAccount Submit Button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createAccountSubmit")));
        WebElement header = driver.findElement(By.id("createAccountSubmit"));
        header.click();

        driver.findElement(By.xpath("//*[@id='ap_customer_name']")).sendKeys(values.get("name"));
        driver.findElement(By.xpath("//*[@id='ap_email']")).sendKeys(values.get("email"));
        driver.findElement(By.xpath("//*[@id='ap_password']")).sendKeys(values.get("password"));
        driver.findElement(By.xpath("//input[@id='ap_password_check']")).sendKeys(values.get("password_check"));
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Create\\Create.png");

        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

        WebElement createAccountSubmit = driver.findElement(By.id("continue"));
        createAccountSubmit.click();

        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

        driver.close();
    }

    @Test
    public void testcase1_amazon_create_account_negative() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Balaji\\chrome\\chromedriver.exe");
        WebDriver driver  = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Create_negative\\homepage.png");
        //Wait
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='nav-link-accountList']/span[1]")));
        // Clicking account sign-in
        WebElement signIn = driver.findElement(By.xpath("//*[@id='nav-link-accountList']/span[1]"));
        signIn.click();
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Create_negative\\signin.png");
        //CreateAccount Submit Button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createAccountSubmit")));
        WebElement header = driver.findElement(By.id("createAccountSubmit"));
        header.click();

        driver.findElement(By.xpath("//*[@id='ap_customer_name']")).sendKeys("");
        driver.findElement(By.xpath("//*[@id='ap_email']")).sendKeys("");
        driver.findElement(By.xpath("//*[@id='ap_password']")).sendKeys(values.get("password"));
        driver.findElement(By.xpath("//input[@id='ap_password_check']")).sendKeys(values.get("password_check"));
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Create_negative\\Create.png");

        WebElement createAccountSubmit = driver.findElement(By.id("continue"));
        createAccountSubmit.click();

        try {
            Thread.sleep(7000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

        boolean errorNameMessage = driver.findElement(By.xpath("//div[@id='auth-customerName-missing-alert']//div[contains(text(),'Enter your name')]")).isDisplayed();
        assertTrue(errorNameMessage, "Name field is empty which is not expected");

        boolean errorEmailMessage = driver.findElement(By.xpath("//div[@id='auth-email-missing-alert']//div[contains(text(),'Enter your email')]")).isDisplayed();
        assertTrue(errorEmailMessage, "Email field is empty which is not expected");

        driver.close();
    }



    @Test
    public void testcase2_amazon_signIn() {

        //ChromeDriver's location.
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Balaji\\chrome\\chromedriver.exe");
        WebDriver driver  = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\homepage.png");

        //Wait
        WebDriverWait wait = new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='nav-link-accountList']/span[1]")));

        // Clicking account sign-in
        WebElement signIn = driver.findElement(By.xpath("//*[@id='nav-link-accountList']/span[1]"));
        signIn.click();
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\signin.png");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInSubmit")));

        //Entering Credentials
        driver.findElement(By.xpath("//*[@id='ap_email']")).sendKeys(values.get("ap_email"));
        driver.findElement(By.xpath("//*[@id='ap_password']")).sendKeys(values.get("ap_password"));
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\credentials.png");

        //Clicking Submit
        WebElement SignInYourAccount = driver.findElement(By.id("signInSubmit"));
        SignInYourAccount.click();

        //Wait for few seconds to show the output
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

        driver.close();

    }

    @Test
    public void testcase3_amazon_order() {

        //ChromeDriver's location.
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Balaji\\chrome\\chromedriver.exe");
        WebDriver driver  = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\homepage.png");

        //Wait
        WebDriverWait wait = new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='nav-link-accountList']/span[1]")));

        // Clicking account sign-in
        WebElement signIn = driver.findElement(By.xpath("//*[@id='nav-link-accountList']/span[1]"));
        signIn.click();
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\signin.png");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInSubmit")));

        //Entering Credentials
        driver.findElement(By.xpath("//*[@id='ap_email']")).sendKeys(values.get("ap_email"));
        driver.findElement(By.xpath("//*[@id='ap_password']")).sendKeys(values.get("ap_password"));
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\credentials.png");

        //Clicking Submit
        WebElement SignInYourAccount = driver.findElement(By.id("signInSubmit"));
        SignInYourAccount.click();

        //Searching the desired Product
        driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).sendKeys(values.get("Product"));
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\search.png");
        WebElement SearchButton = driver.findElement(By.xpath("//input[@type='submit' and @class='nav-input']"));
        SearchButton.click();

        //Wait for the product to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='search']//img[@src='https://m.media-amazon.com/images/I/71skm4XpjvL._AC_UL320_.jpg']")));
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\product.png");
        //Select the product after the search results
        WebElement SelectProduct = driver.findElement(By.xpath("//*[@id='search']//img[@src='https://m.media-amazon.com/images/I/71skm4XpjvL._AC_UL320_.jpg']"));
        SelectProduct.click();

        //See all buying Options
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\buying_option.png");
        WebElement BuyingOptions = driver.findElement(By.xpath("//a[@id='buybox-see-all-buying-choices-announce']"));
        BuyingOptions.click();

        //Wait for the page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='a-autoid-0']//input[@value='Add to cart']")));

        //Adding to Cart
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\add_cart.png");
        WebElement AddToCart = driver.findElement(By.xpath("//span[@id='a-autoid-0']//input[@value='Add to cart']"));
        AddToCart.click();

        //Wait for the page to load-out and Proceeding to Checkout
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='hlb-ptc-btn-native']")));
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\checkout.png");
        WebElement ProceedtoCheckout = driver.findElement(By.xpath("//a[@id='hlb-ptc-btn-native']"));
        ProceedtoCheckout.click();

        //Confirm Deliver Address
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Deliver to this address')]")));
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\address.png");
        WebElement ConfirmDeliverAddress = driver.findElement(By.xpath("//a[contains(text(),'Deliver to this address')]"));
        ConfirmDeliverAddress.click();

        //Continue Button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shippingOptionFormId']//span[1]/span/input[@value ='Continue']")));
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\continue.png");
        WebElement ContinueButton = driver.findElement(By.xpath("//*[@id='shippingOptionFormId']//span[1]/span/input[@value ='Continue']"));
        ContinueButton.click();

        //Select Payment Method Button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='continue-top']")));
        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\payment.png");
        WebElement PaymentContinue = driver.findElement(By.xpath("//input[@id='continue-top']"));
        PaymentContinue.click();

        this.takeSnapShot(driver,"C:\\Users\\Balaji\\IdeaProjects\\Automation\\Order\\place_order.png");

        //Wait for few seconds to show the output
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
        //close the google chrome driver
        driver.close();
    }

}