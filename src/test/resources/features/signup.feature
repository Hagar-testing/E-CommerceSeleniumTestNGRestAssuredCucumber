Feature: User Registration on DemoBlaze Website
    This feature describes the process of user registration on the DemoBlaze website.

    Scenario: Successful User Registration
        Given I am on the DemoBlaze homepage
        When I click on the Sign Up button located in the header
        And I fill in the username and password fields in the sign-up form and submit the sign-up button
        Then I should see a success message indicating the account is created
