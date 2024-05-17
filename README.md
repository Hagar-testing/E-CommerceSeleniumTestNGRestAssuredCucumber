# E-Commerce-Selenium-TestNG-RestAssured-Cucumber

### The main Frameworks included in the project:
* Selenium Webdriver
* TestNG
* Rest Assured
* Cucumber
* Allure Report
* Extent Reports

### Project Design:
* Page Object Model (POM) design pattern
* Data Driven framework
* Fluent design approach (method chaining)
* Have a supporting Utils package in *src/main/java/com/demoblaze/utils* file path, named ***"Utils"*** 

### How to run the project main test cases locally:
* A properties files can be found in *resources* package including all the configurations needed in the execution.
* Can find the test cases in the *src/test/java* folder mainly in the *tests* package.
* To start the execution, open a command-line terminal on the project root path and type `mvn clean test`.
* After executing, you can easily generate the ***Allure Report*** by opening a command-line terminal on the project root path and type `allure serve target/allure-results` (needs to be able to execute mvn commands); Or you can find the Extent Report in this path 'target/extend-report'.

### Followed several best practices, including:
* Avoiding Page Factory.
* Did not use BaseTest and BasePage classes.
* Dynamic Configuration: Avoid using a static testing.xml file in favor of a more dynamic approach.
* Ensured not to mix implicit and explicit waits.
* Did not ignore exceptions.

### To further enhance our code ###
* Added ElementActions and APIAction files.
* Replaced hard-coded paths with a constants file to centralize configurations.
* Implemented regions to make the code more readable and organized.

### Running test cases on Chrome ###
https://github.com/Hagar-testing/E-CommerceSeleniumTestNGRestAssuredCucumber/assets/138511085/7a1ce2fd-675c-4d87-8c07-90e9f9c45980

### Allure Report ##
<img width="829" alt="image" src="https://github.com/Hagar-testing/E-CommerceSeleniumTestNGRestAssuredCucumber/assets/138511085/5daf902e-2abd-4718-8f4a-77ee62655421">

### Extent Report ##
<img width="953" alt="image" src="https://github.com/Hagar-testing/E-CommerceSeleniumTestNGRestAssuredCucumber/assets/138511085/ef7d1015-f396-40e7-982d-9e352202de16">


