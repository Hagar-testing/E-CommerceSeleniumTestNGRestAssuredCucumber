package tests;

import com.demoblaze.api.AuthenticationApis;
import com.demoblaze.factory.DriverFactory;
import com.demoblaze.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Base64;

public class CheckoutTest {
    private  WebDriver driver;

    private final String timeStamp = String.valueOf(System.currentTimeMillis());

    @BeforeMethod
    public void beforeMethod() {
        driver = new DriverFactory().initializeDriver() ;

    }

    @Test
    public void verifyTwoProductsArePurchasedSuccessfully() throws InterruptedException {

        new AuthenticationApis()
                .registerUser("Hagar"+ timeStamp,"Hagar");

        new HomePage(driver)
                .load();
        new Header(driver)
                .clickOnLoginButton();

        new LoginPage(driver)
                .loginUser("Hagar" + timeStamp, "Hagar");

        new Header(driver)
                .validateOnAccountIsOpenedSuccessfully();

        new HomePage(driver)
                .selectCategory("Laptops")
                .selectProduct(1);
        new ProductDetailsPage(driver)
                .clickOnAddToCartButton()
                .validateOnSuccessMessageOfAddProductToCart("Product added.")
                .hideAlertDialog()
                .navigateBack()
                .navigateBack();

        new HomePage(driver)
                .selectProduct(2);

        new ProductDetailsPage(driver)
                .clickOnAddToCartButton()
                .validateOnSuccessMessageOfAddProductToCart("Product added.")
                .hideAlertDialog();

        new Header(driver)
                .clickOnCartButton();

        new CartPage(driver)
                .validateOnItemAddedInCart("Sony vaio i5")
                .validateOnProductPrices("Sony vaio i5", "790")
                .validateOnItemAddedInCart("Sony vaio i7")
                .validateOnProductPrices("Sony vaio i7", "790")
                .validateOnTotalProductPrice("1580")
                .clickOnPlaceOrderButton();

        new PlaceOrderPage(driver)
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
        driver.quit();
    }

}
