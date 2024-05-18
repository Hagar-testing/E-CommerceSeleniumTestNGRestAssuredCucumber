@PlaceOrder @Cucumber
Feature: Purchase Products on DemoBlaze Website

    Scenario: Purchase Multiple Products Successfully
        Given I am a registered user on DemoBlaze
        When I log in to the website with valid credentials
        Then I validate that the account is opened successfully
        And I select a category
        When I add the first product to my cart
        Then I should see a success message indicating the product is added
        When I add the second product to my cart
        Then I should see a success message indicating the product is added
        When I view my cart
        Then I should see both selected products listed in the cart
        And the total price should be calculated correctly based on product prices
        When I proceed to place order
        Then I should see the same total price on the place order page
        When I fill in my order details and submit
        Then I should see a success message confirming my order
