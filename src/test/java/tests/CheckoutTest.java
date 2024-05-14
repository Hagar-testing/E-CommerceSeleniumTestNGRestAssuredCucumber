package tests;

import com.demoblaze.factory.DriverFactory;
import com.demoblaze.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
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
                .loginUser("Hagar", "Hagar");

        new Header(driver.get())
                .validateOnAccountIsOpenedSuccessfully();

        new HomePage(driver.get())
                .selectCategory("Laptops")
                .selectProduct(1);
        new ProductDetailsPage(driver.get())
                .clickOnAddToCartButton()
                .validateOnSuccessMessageOfAddProductToCart("Product added.")
                .hideAlertDialog()
                .navigateBack()
                .navigateBack();

        new HomePage(driver.get())
                .selectProduct(2);

        new ProductDetailsPage(driver.get())
                .clickOnAddToCartButton()
                .validateOnSuccessMessageOfAddProductToCart("Product added.")
                .hideAlertDialog();

        new Header(driver.get())
                .clickOnCartButton();

        new CartPage(driver.get())
                .validateOnItemAddedInCart("Sony vaio i5")
                .validateOnProductPrices("Sony vaio i5", "790")
                .validateOnItemAddedInCart("Sony vaio i7")
                .validateOnProductPrices("Sony vaio i7", "790")
                .validateOnTotalProductPrice("1580")
                .clickOnPlaceOrderButton();

        new PlaceOrderPage(driver.get())
                .validateOnTotalPriceInPlaceOrder("1580")
                .fillOrderInformationAndPurchase(
                        "Hagar",
                        "Egypt",
                        "Cairo",
                        "Month",
                        "123456799100",
                        "2024")
                .validateOnSuccessMessageOfPurchaseOrder("Thank you for your purchase!");

    }

    @AfterMethod
    public void afterMethod(){
        driver.get().quit();
    }

}
