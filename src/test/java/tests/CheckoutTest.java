package tests;

import com.demoblaze.factory.DriverFactory;
import com.demoblaze.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest {
    protected final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public HomePage homePage;
    public SignupPage signupPage;

    public Header header;

    private final String timeStamp = String.valueOf(System.currentTimeMillis());

    @BeforeMethod
    public void beforeMethod() {
        driver.set(new DriverFactory().initializeDriver());

    }

    @Test
    public void verifyTwoProductsArePurchasedSuccessfully() throws InterruptedException {
        new HomePage(driver.get())
                .load();
        new Header(driver.get())
                .clickOnLoginButton();

        new LoginPage(driver.get())
                .loginUser("Hagar","Hagar");

        new Header(driver.get())
                .validateOnAccountIsOpenedSuccessfully();

        new HomePage(driver.get())
                .selectCategory("Laptops")
                .selectProduct(1);
        new ProductPage(driver.get())
                .clickOnAddToCartButton()
                .validateOnSuccessMessageOfAddProductToCart("Product added.")
                .hideAlertDialog();

        new Header(driver.get())
                .clickOnHomeButton();

        new HomePage(driver.get())
                .selectCategory("Laptops")
                .selectProduct(2);
        new ProductPage(driver.get())
                .clickOnAddToCartButton()
                .validateOnSuccessMessageOfAddProductToCart("Product added.")
                .hideAlertDialog();

        new Header(driver.get())
                .clickOnCartButton();
    }



}
