# w3-cucumber-tests

w3-cucumber-tests project aimed at testing general health of selected w3.org pages.
The project uses BDD approach and utilize features of Cucumber, Serenity and RestAssured java library for efficient testing of the endpoints.

## Prerequisites

**A. Docker environment (remote webdriver)**

1. Java 8+ (openjdk) You can find a suitable version of Java and install instructions [here](https://docs.oracle.com/en/java/javase/15/install/)
1. Maven 3.6.3+ [here](https://maven.apache.org/install.html)
1. Docker [here](https://docs.docker.com/get-docker/)

**B. Local environment**

1. First IntelliJ needs to be installed. (Preferably a recent version)
    1. You can find IntelliJ CE (2020.2.2) here:
        - [Windows](https://download.jetbrains.com/idea/ideaIC-2020.2.2.exe) (exe)
        - [Linux](https://download.jetbrains.com/idea/ideaIC-2020.2.2.tar.gz) (tar.gz)
        - [Mac](https://download.jetbrains.com/idea/ideaIC-2020.2.2.dmg) (dmg)

    1. Java needs to be installed either through IntelliJ it will prompt for this during the setup or by you as the end-user.
        - You can find a suitable version of Java here: https://adoptopenjdk.net/, this needs to be extracted and then the path to this folder has to be added, otherwise, IntelliJ won't know where the java installation is.
        - Or you can download jdk directly from oracle and install as per instructions [here](https://docs.oracle.com/en/java/javase/15/install/)
1. Then start IntelliJ and complete the initial setup of the editor, as of now there are no additional plugins required, choose whatever color scheme that suits you this can be changed later.

1. We need to install the plugins Cucumber for Java and Gherkin.
    1. This can be done by following this small flow:
        1. Click the file option in the toolbar.
        1. Then click Settings
        1. In the settings window, you click on Plugins.
        1. In the plugin marketplace, search for Cucumber for Java and install the plugin
        1. Also in the plugin marketplace, search for Gherkin and install the plugin
        1. Restart IntelliJ
1. Install maven as per instructions [here](https://maven.apache.org/install.html)

   > **NOTE:** If you have the JBehave plugin installed this needs to be disabled as this will interfere with the gherkin files.

## Get the code

Git:

    git clone https://github.com/peterb91/w3-cucumber-tests.git
    cd w3-cucumber-tests


Or simply [download a zip](https://github.com/peterb91/w3-cucumber-tests/archive/refs/heads/main.zip) file.

## Run tests and get Serenity report

1. Run in **docker-compose** (in the project root dir)
  - **chrome**:
    ```
    docker-compose -f docker-compose.yml up -d && mvn clean verify -Dwebdriver.remote.url="http://localhost:4444/wd/hub" -Dwebdriver.driver=chrome
    ```
  - **firefox** (@chromeOnly console error tests excluded):
    ```
    docker-compose -f docker-compose.yml up -d && mvn clean verify -Dwebdriver.remote.url="http://localhost:4444/wd/hub" -Dcucumber.filter.tags="not @chromeOnly" -Dwebdriver.driver=firefox
    ```

Serenity report is generated after any above run in the following location (please note that mvn clean remove previously created report): 

`w3-cucumber-tests/target/site/serenity/index.html`

For failing scenarios in the results detail screenshot is attached together with error log
Step details for API requests contains details about given call and response.

1. Run **locally** (in the project root dir)
  - Run from maven:
    
    (All feature files with Serenity report  which includes both the living documentation from the feature files
    and also details of the REST requests and responses that were executed during the test)
    ```
    mvn clean verify -Dwebdriver.driver=chrome/firefox
    ```
  - Run from ide:
    
    (Run the `RunCucumberTest` class in src/test/java/w3/org/runners/ dir).
    - If you use IntelliJ as your editor this should be preconfigured and should exist under the run configurations.
    - To change browser please change **webdriver.driver** variable in _serenity.conf_ file.

Serenity report is generated only after `mvn clean verify...` command run in the following location: `w3-cucumber-tests/target/site/serenity/index.html`

## Configuration

1. **W3ORG_BASE_URI** can be changed in `src/test/java/w3.org/config/TestConfig.class`
1. **Constants** or **Session variables** can be modified or added in `src/test/java/w3.org/config/Constants.class`
1. **Browser type/settings** and **Headless mode** can be changed in `src/test/resources/serenity.conf`



### The project directory structure

The project follows the standard directory structure used in most Cucumber and Serenity projects:
```Gherkin
src
  + test
    + java                              
      + w3.org                            Supporting code
        + config                          Config and constants files for the framework
        + pages                           Page objects classes 
        + runners                         Test runner for all feature files
        + stepdefs                        Definitions of steps from feature files
    + resources
      + features                          Feature files???
          + backend                
          + frontend   

```

## How to write new test
1. Add new feature file under `src/test/resources/features`
    1. For new API endpoint create separate directory in backend
    1. For new UI based scenarios create new page directory in frontend
    1. Create feature file with descriptive name
    1. Write scenario(s) using Gherkin syntax Given-When-Then (please see "Coding Conventions" section below for guidelines)
2. Add java definitions for feature steps in appropriate *Steps.java file under `src/test/java/w3.org/stepdefs`
3. Run a newly created scenario to verify it works.

## Coding Conventions

Best practices for writing feature files/scenarios
- **Scenario description**: Write what you are testing, not how you are testing that (for the ???how??? the GWT is used.). Write it in a short, clear sentence trying to highlight the requirement part for which the given scenario is created.
- Keep the GWT order in your tests (for this annotating existing steps is a technique). However, there are cases, where the WhenThenWhenThen makes sense.
- Use **@Given** to express the preconditions of a test execution. Use passive sentences, @Given is mostly not an "action". E.g "I push the button" --> "the button is pushed"
- Use **@When** only for the functionality you want to test in the scenario (use only 1 or 2 Whens in a scenario) For all other pre-requisites, use @Given  (alias the When steps if needed)
- Use **@Then** only for assertions. Use it to describe the actual outcome, not an expected. DO NOT use "shall happen", but DO use "happened". A statement instead of an expectation.
- Try to write reusable steps (make them parametrized)
- Do not save the words, step definitions shall be descriptive and clear, but not technical sentences.
- Add quote for exact button names e.g: `user clicks on "Login" button` -> the button text is exactly `Login`
- When you have two values in connection use the same unit in the tests (e.g: do not set 1 minute timeout and execute 10000 millis algorithm, but: 2 minute timeout and 1 minute execution)
- When testing result, you can be code specific: e.g: when a json response shall contain a "myMessage" filled with "xyz":, And I get the response with "xyz" message  -->, And I get the response with "myMessage" is "xyz".