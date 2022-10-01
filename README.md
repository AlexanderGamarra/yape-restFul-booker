# yape-restFul-booker


### Prerequisites

* Git
* Java jdk 1.8 
  ```
  https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html
  ```
* Maven 3.x or higher. 

    If you are using Windows, you can use the follow command
    ```
    choco install maven
    ```
    OSx:
    ``` 
    brew install maven
    ```

### Clone a repository
1. Go to the repository link and clone repo

```
https://github.com/AlexanderGamarra/yape-restFul-booker
```
---

### Building Project

Open project and following the next steps (IntelliJ - Recommended)

```
1. Open project folder in terminal
2. Execute next command there "mvn clean install" (To install maven dependencies)
3. Execute "mvn verify" (You shouldn't have mistakes)
```
### SetUp IntelliJ

Install plugins
```
1. Go to the preferences
2. Search plugins section
3. Install "Cucumber for Java" and "Gherkin"
4. Reopen IntelliJ 
```

### Run Automated Test Suite

Project has the follow tags executions: @RegressionTest and @SmokeTest
```
1. Go to this file "src/test/java/main.java"
2. If you want to execute all the tests, you must use @RegressionTest tag

@CucumberOptions(
        tags = "@RegressionTest",
        plugin = {"pretty"}
)

3. And use @SmokeTest to execute fast flows
@CucumberOptions(
        tags = "@SmokeTest",
        plugin = {"pretty"}
)
4. After that run "main" class.
5. Meanwhile automated tests are running, you will be able to see tests running on the logs
6. Additionally, If you want to execute specific test you can add a tag name on the scenario.  
```
### Getting serenity reports

```
1. Go to the terminal
2. Execute next command: "mvn serenity:aggregate"
3. You will see serenity reports on the logs:

[INFO]   - Full Report: {user}/yape-restFul-booker/target/site/serenity/index.html
[INFO]   - Single Page HTML Summary: {user}/yape-restFul-booker/target/site/serenity/serenity-summary.html
[INFO]   - Full Report As React Based Single Page Application: {user}/yape-restFul-booker/target/site/serenity/navigator/index.html

5. Enter to the "Full Report" to see execution details.  
```