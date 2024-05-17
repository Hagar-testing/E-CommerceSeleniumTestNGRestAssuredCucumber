package com.demoblaze.tests;

import com.demoblaze.api.ApisAuthentication;
import com.demoblaze.factory.DriverFactory;
import com.demoblaze.listener.TestngListener;
import com.demoblaze.pages.*;
import com.demoblaze.utils.JsonUtils;
import com.google.gson.JsonObject;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Objects;
import static com.demoblaze.utils.JsonUtils.getTestData;
import static com.demoblaze.constants.FilesPathConstants.CHECKOUT_DATA_FILE_PATH;

@Listeners(TestngListener.class)
@Feature("Place Order Feature")
public class PlaceOrderTest {
    private WebDriver driver;
    private final String timeStamp = String.valueOf(System.currentTimeMillis());
    private JsonObject data ;

    @BeforeClass
    public void beforeClass(){
       data = JsonUtils.parseJsonFile(CHECKOUT_DATA_FILE_PATH);
    }
    @BeforeMethod
    public void beforeMethod() {
        driver = new DriverFactory().initializeDriver() ;
    }

    @Story("Purchase Process")
    @Description("Given that I am a registered user, When I add two products to the cart and place an order, Then the products should be purchased successfully and a success message should appear")
    @Test(description = "Verify Two Products are Purchased Successfully - GUI")
    public void verifyTwoProductsArePurchasedSuccessfully() {

        new ApisAuthentication()
                .registerUser(getTestData(data, "user.name")+ timeStamp,
                        Objects.requireNonNull(getTestData(data, "user.password")));

        new HomePage(driver)
                .load();
        new Header(driver)
                .clickOnLoginButton();

        new LoginPage(driver)
                .loginUser(getTestData(data, "user.name")+ timeStamp, getTestData(data, "user.password"));

        new Header(driver)
                .validateOnAccountIsOpenedSuccessfully();

        new HomePage(driver)
                .selectCategory(getTestData(data, "category.name"))
                .selectProduct(1,getTestData(data, "category.products.first_product.title"));
        new ProductDetailsPage(driver)
                .clickOnAddToCartButton()
                .validateOnSuccessMessageOfAddProductToCart(getTestData(data, "messages.add_product_to_cart"))
                .hideAlertDialog()
                .navigateBack()
                .navigateBack();

        new HomePage(driver)
                .selectProduct(2,getTestData(data, "category.products.second_product.title"));

        new ProductDetailsPage(driver)
                .clickOnAddToCartButton()
                .validateOnSuccessMessageOfAddProductToCart(getTestData(data, "messages.add_product_to_cart"))
                .hideAlertDialog();

        new Header(driver)
                .clickOnCartButton();

        new CartPage(driver)
                .validateOnItemAddedInCart(getTestData(data, "category.products.first_product.title"))
                .validateOnProductPrices(getTestData(data, "category.products.first_product.title"), getTestData(data, "category.products.first_product.price"))
                .validateOnItemAddedInCart(getTestData(data, "category.products.second_product.title"))
                .validateOnProductPrices(getTestData(data, "category.products.second_product.title"), getTestData(data, "category.products.second_product.price"))
                .validateOnTotalProductPrice(getTestData(data, "cart.total_price"))
                .clickOnPlaceOrderButton();

        new PlaceOrderPage(driver)
                .validateOnTotalPriceInPlaceOrder(getTestData(data, "cart.total_price"))
                .fillOrderInformationAndPurchase(
                        getTestData(data, "place_order_data.name"),
                        getTestData(data, "place_order_data.country"),
                        getTestData(data, "place_order_data.city"),
                        getTestData(data, "place_order_data.month"),
                        getTestData(data, "place_order_data.card"),
                        getTestData(data, "place_order_data.year"))
                .validateOnSuccessMessageOfPurchaseOrder(getTestData(data, "messages.place_order"));


    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

}
