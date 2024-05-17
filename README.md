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


### Allure Report ##

### Extent Report ##

