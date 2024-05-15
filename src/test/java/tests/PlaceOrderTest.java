package tests;

import com.demoblaze.api.AuthenticationApis;
import com.demoblaze.factory.DriverFactory;
import com.demoblaze.listener.RetryAnalyzer;
import com.demoblaze.pages.*;
import com.demoblaze.utils.JsonUtils;
import com.google.gson.JsonObject;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Objects;
import static com.demoblaze.utils.JsonUtils.getTestData;
import static data.DataFilesPathConstants.CHECKOUT_DATA_FILE_PATH;

@Feature("Place Order Feature")
public class PlaceOrderTest {
    private  WebDriver driver;

    private final String timeStamp = String.valueOf(System.currentTimeMillis());
    JsonObject data ;

    @BeforeClass
    public void beforeClass(){
       data = JsonUtils.parseJsonFile(CHECKOUT_DATA_FILE_PATH);
    }
    @BeforeMethod
    public void beforeMethod() {
        driver = new DriverFactory().initializeDriver() ;
    }

    @Test
    public void verifyTwoProductsArePurchasedSuccessfully()  {

        new AuthenticationApis()
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


        // New instance of pages
        // time in logger
        // reports
        // merge on master
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

}
