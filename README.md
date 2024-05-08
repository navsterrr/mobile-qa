# Mobile Test Automation Interview Project

## Requirements

1. Java 8+

    - If Java is not installed, follow these instructions:
        - For Windows, download latest java version from [here](https://java.com/en/download/) and run the installer executable
        - For Mac and Linux, run `java -version` to see what java version is pre-installed. If you want a different version download from [here](https://java.com/en/download/)

2. Maven
   - If Maven is not downloaded, download it from [here](https://maven.apache.org/download.cgi)
   - For installation, follow the instructions [here](https://maven.apache.org/install.html)

3. Appium Inspector
    - If Appium Inspector is not downloaded, download it from [here](https://github.com/appium/appium-inspector/releases)

## Goal of this Project

The Goal of this project is to write an Appium test using TestNG for our TeamSnap Android or iOS Application. You can choose whether you want to write the test for our iOS or Android application. The framework is already set up, you will just need to write your test case(s) in the AppTest.java file. With that being said, feel free to make any changes that you'd like to the test framework in order to write your test. We will be using Browserstack to run our tests for this project. For this example, we would like you to write a test to cover one of the test cases you wrote in the first interview.

## Instructions

1. Pull down this mobile-qa repository (if you haven't already).

2. Install the dependencies

    ```
    mvn clean
    ```

3. In the browserstack.yml file, replace ```username``` and ```accesskey``` values with the ones sent to you via the Google Document.

4. In the browserstack.yml file, replace ```android-app-id``` or ```ios-app-id``` values with the ones sent to you via the Google Document.

    - Choose whether to write the test for Android or iOS. If you choose Android, set the ```android-app-id``` value. If you choose iOS, set the ```ios-app-id``` value.

5. Open the Appium Inspector and select Browserstack as the Cloud Provider.

6. Enter the Browserstack username and access key (same values that you adjusted in step 3) and set the desired capabilities (hint: you can find the app path in the browserstack.yml file)

7. Start the Appium Inspector session and go through the steps of the test case defined in the Google document. The username and password for the TeamSnap app were sent to you via the Google document.

8. Write your test case defined in the Google Doc Instructions. There is a spot in AppTest.java called: ```/* Write your test steps here */```

9. Run your test! Here are two options:

    - Using Maven:
      - Android: ```mvn test -Pandroid```
      - iOS: ```mvn test -Pios```
    - Using xml files:
      - Right click on test_android.xml and click `Run`
      - Right click on test_ios.xml and click `Run`

10. View your test results on the [Browserstack Dashboard](https://app-automate.browserstack.com/dashboard). You will need to log into Browserstack with the email and password sent to you in the Google Document.
