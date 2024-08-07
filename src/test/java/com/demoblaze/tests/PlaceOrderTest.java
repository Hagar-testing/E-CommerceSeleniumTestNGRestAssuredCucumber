package com.demoblaze.tests;

import com.demoblaze.api.ApisAuthentication;
import com.demoblaze.factory.DriverFactory;
import com.demoblaze.listener.TestngListener;
import com.demoblaze.pages.*;
import com.demoblaze.utils.JsonUtils;
import com.google.gson.JsonObject;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Objects;

import static com.demoblaze.constants.FilesPathConstants.CHECKOUT_DATA_FILE_PATH;
import static com.demoblaze.utils.JsonUtils.getTestData;
@Tag("TestNG")
@Listeners(TestngListener.class)
@Feature("Place Order Feature")
public class PlaceOrderTest {
    private final String timeStamp = String.valueOf(System.currentTimeMillis());
    private JsonObject data ;
    WebDriver driver;

    @Test
    public void validateOnAccountIsOpenedSuccessfully()  {
        new ApisAuthentication()
                .registerUser(
                        getTestData(data, "user.name")+ timeStamp,
                        Objects.requireNonNull(getTestData(data, "user.password")));

        new HomePage(getDriver())
                .load()
                .navigateToHeader()
                .clickOnLoginButton()
                .loginUser(getTestData(data, "user.name")+ timeStamp, getTestData(data, "user.password"))
                .navigateToHeader()
                .validateOnAccountIsOpenedSuccessfully();

    }

    @Test(dependsOnMethods = {"validateOnAccountIsOpenedSuccessfully"})
    public void addFirstProductToCart(){
        new HomePage(getDriver())
                .selectCategory(getTestData(data, "category.name"))
                .selectProduct(1,getTestData(data, "category.products.first_product.title"))
                .clickOnAddToCartButton()
                .validateOnSuccessMessageOfAddProductToCart(getTestData(data, "messages.add_product_to_cart"));

    }

    @Test(dependsOnMethods = {"addFirstProductToCart"})

    public void addSecondProductToCart(){
        new ProductDetailsPage(getDriver())
                .hideAlertDialog()
                .navigateBack()
                .selectProduct(2,getTestData(data, "category.products.second_product.title"))
                .clickOnAddToCartButton()
                .validateOnSuccessMessageOfAddProductToCart(getTestData(data, "messages.add_product_to_cart"));
    }

    @Test(dependsOnMethods = {"addSecondProductToCart"})
    public void makeSureProductsAreAddedSuccessfully(){
        new ProductDetailsPage(getDriver())
                .hideAlertDialog()
                .navigateToHeader()
                .clickOnCartButton()
                .validateOnItemAddedInCart(getTestData(data, "category.products.first_product.title"))
                .validateOnProductPrices(getTestData(data, "category.products.first_product.title"), getTestData(data, "category.products.first_product.price"))
                .validateOnItemAddedInCart(getTestData(data, "category.products.second_product.title"))
                .validateOnProductPrices(getTestData(data, "category.products.second_product.title"), getTestData(data, "category.products.second_product.price"))
                .validateOnTotalProductPrice(getTestData(data, "cart.total_price"))
                .clickOnPlaceOrderButton();
    }

    @Test(dependsOnMethods = {"makeSureProductsAreAddedSuccessfully"})
    public void placeOrderData(){
        new PlaceOrderPage(getDriver())
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

    //region WebDriver
    public void setDriver(WebDriver driver){
       this.driver = driver;
    }

    public WebDriver getDriver(){
        return driver;
    }
    //endregion

    //region Configurations
    @BeforeClass
    public void beforeClass(){
        data = JsonUtils.parseJsonFile(CHECKOUT_DATA_FILE_PATH);
        setDriver(new DriverFactory().initializeDriver());

    }

    @AfterClass
    public void afterClass(){
       getDriver().quit();

    }
    //endregion

}
