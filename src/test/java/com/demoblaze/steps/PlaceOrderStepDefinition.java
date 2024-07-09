package com.demoblaze.steps;

import com.demoblaze.api.ApisAuthentication;
import com.demoblaze.factory.DriverFactory;
import com.demoblaze.pages.*;
import com.demoblaze.utils.JsonUtils;
import com.google.gson.JsonObject;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import static com.demoblaze.constants.FilesPathConstants.CHECKOUT_DATA_FILE_PATH;
import static com.demoblaze.utils.JsonUtils.getTestData;


public class PlaceOrderStepDefinition {

    private final JsonObject testData;
    private final String timeStamp = String.valueOf(System.currentTimeMillis());
    WebDriver driver;

    public PlaceOrderStepDefinition() {
        testData = JsonUtils.parseJsonFile(CHECKOUT_DATA_FILE_PATH);
        driver = new DriverFactory().initializeDriver();

    }


    @Given("I am a registered user on DemoBlaze")
    public void registerUser() {
        new ApisAuthentication()
                .registerUser(
                        getTestData(testData, "user.name") + timeStamp,
                        Objects.requireNonNull(getTestData(testData, "user.password")));
    }

    @When("I log in to the website with valid credentials")
    public void loginToWebsite() {
        new HomePage(driver).load()
                .navigateToHeader()
                .clickOnLoginButton()
                .loginUser(getTestData(testData,"user.name") + timeStamp, getTestData(testData,"user.password"));
    }

    @Then("I validate that the account is opened successfully")
    public void validateOnAccountIsOpenedSuccessfully() {
        new Header(driver).validateOnAccountIsOpenedSuccessfully();
    }

    @And("I select a category")
    public void chooseCategory() {
        new HomePage(driver).selectCategory(getTestData(testData, "category.name"));
    }

    @When("I add the first product to my cart")
    public void addFirstProductToCart() {
        new HomePage(driver)
                .selectProduct(1, getTestData(testData, "category.products.first_product.title"))
                .clickOnAddToCartButton();
    }

    @Then("I should see a success message indicating the product is added")
    public void validateFirstProductAdded() {
        new ProductDetailsPage(driver)
                .validateOnSuccessMessageOfAddProductToCart(getTestData(testData, "messages.add_product_to_cart"))
                .hideAlertDialog();
    }

    @When("I add the second product to my cart")
    public void addSecondProductToCart() {
        new ProductDetailsPage(driver)
                .navigateBack()
                .selectProduct(2, getTestData(testData, "category.products.second_product.title"))
                .clickOnAddToCartButton();
    }

    @Then("I should see a success message indicating the second product is added")
    public void validateSecondProductAdded() {
        new ProductDetailsPage(driver)
                .validateOnSuccessMessageOfAddProductToCart(getTestData(testData, "messages.add_product_to_cart"))
                .hideAlertDialog();
    }

    @When("I view my cart")
    public void viewCart() {
        new Header(driver).clickOnCartButton();
    }

    @Then("I should see both selected products listed in the cart")
    public void validateProductsInCart() {
        new CartPage(driver)
                .validateOnItemAddedInCart(getTestData(testData, "category.products.first_product.title"))
                .validateOnItemAddedInCart(getTestData(testData, "category.products.second_product.title"));
    }

    @And("the total price should be calculated correctly based on product prices")
    public void validateTotalPrice() {
        new CartPage(driver)
                .validateOnTotalProductPrice(getTestData(testData, "cart.total_price"));
    }

    @When("I proceed to place order")
    public void navigateToPlaceOrder() {
        new CartPage(driver).clickOnPlaceOrderButton();

    }

    @Then("I should see the same total price on the place order page")
    public void validateOnTotalPriceInPlaceOrderPage() {
        new PlaceOrderPage(driver)
                .validateOnTotalPriceInPlaceOrder(getTestData(testData, "cart.total_price"));
    }

    @When("I fill in my order details and submit")
    public void fillOrderData() {
        new PlaceOrderPage(driver)
                .fillOrderInformationAndPurchase(
                getTestData(testData, "place_order_data.name"),
                getTestData(testData, "place_order_data.country"),
                getTestData(testData, "place_order_data.city"),
                getTestData(testData, "place_order_data.month"),
                getTestData(testData, "place_order_data.card"),
                getTestData(testData, "place_order_data.year"));
    }

    @Then("I should see a success message confirming my order")
    public void validateOnPlaceOrderSuccessMessage() {
        new PlaceOrderPage(driver)
                .validateOnSuccessMessageOfPurchaseOrder(getTestData(testData, "messages.place_order"));
    }
    //region Configurations
    @After("@PlaceOrder")
    public void after(){
        driver.quit();
    }
    //endregion
}

