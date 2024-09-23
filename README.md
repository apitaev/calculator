# Rest API with Spring Boot for a Calculator Service

## Getting Started
* Install Gradle: https://gradle.org/install/.
* Compile project by running <code>./gradlew clean build</code> (for Mac environment).
* Deploy service locally by running <code>./gradlew bootRun</code> (for Mac environment).
* Run health-check command in terminal <code>curl http://localhost:8080/actuator/health</code>, 
<code>{"status":"UP"}</code> response should appear.
* Run a test Greeting Service API, e.g. by typing in browser <code>http://localhost:8080/api/calculator/</code>,
response should appear in Browser: ![text](src/main/resources/static/TestCalculatorService.jpg)
* Import postman collection from the file <code>src/test/resources/CalculatorAPI.postman_collection.json</code> and run
integration testing suite using provided local environment file <code>src/test/resources/local_env.postman_environment.json, all test should pass successfully.

## Project Structure
Calculator project consists of the 5 packages:
* <code>com.ebay.assignment.calculator</code> containing SpringBoot application.
* <code>com.ebay.assignment.calculator</code> containing Controller implementation.
* <code>com.ebay.assignment.calculator.exception</code> storing CalculatorExceptionHandler with ExceptionClasses.
* <code>com.ebay.assignment.calculator.service</code> storing CalculatorService with utility classes.

## Some project considerations
* CalculatorService is a generic interface defining operations on Number attributes.
* Rest api clients passing Concrete ArgumentsTypes e.g. Integer, Double, Float along with other parameters.
* CalculatorController invokes a CalculatorService implementation based on the ArgumentsType defined by Client e.g. 
<code>com.ebay.assignment.calculator.service.IntegerCalculatorServiceImpl</code> or throws exception if ArgumentsType is
not supported.
* Support for a new ArgumentsType does not require a changes of the exising service implementations.

## Areas to contribute
* Generate javadoc.
* Update README.md to include project diagrams.
* Complete and unit-test implementation for DoubleCalculatorServiceImpl.
* Complete integration and postman testing for the <code>/calculateChain</code>.
* Add new Types for CalculatorService interface e.g. FloatCalculatorServiceImpl, Long.

## Steps to add a new service implementation for a Calculator Service:
  * Add a new class implementing <code>com.ebay.assignment.calculator.service.CalculatorService</code> and implement interface methods;
  * Update enumeration <code>com.ebay.assignment.calculator.service.ArgumentsType</code> by adding a new supported type of arguments.
  * Update a constructor of the <code>com.ebay.assignment.calculator.api.CalculatorController</code> to inject a new implementation.
  * Update api implementations of the <code>com.ebay.assignment.calculator.api.CalculatorController</code> to invoke a new service.
  * Update unit-tests, integration-tests and postman integration suite.



