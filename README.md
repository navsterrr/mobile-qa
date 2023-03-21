### Requirements

1. Java 8+

    - If Java is not installed, follow these instructions:
        - For Windows, download latest java version from [here](https://java.com/en/download/) and run the installer executable
        - For Mac and Linux, run `java -version` to see what java version is pre-installed. If you want a different version download from [here](https://java.com/en/download/)

2. Maven
   - If Maven is not downloaded, download it from [here](https://maven.apache.org/download.cgi)
   - For installation, follow the instructions [here](https://maven.apache.org/install.html)

3. Appium Inspector
    - If Appium Inspector is not downloaded, download it from [here](https://github.com/appium/appium-inspector/releases)


### Goal of this Project

The Goal of this project is to write an Appium test using JUnit for an Android Application. The framework is already set up, you will just need to plug in some variables and write your test case! We will be using Browserstack to run our tests for this project. For this example, we would like you to write a test to log into the TeamSnap mobile application.

### Instructions

1. Pull down this repo (if you haven't already).

2. Install the dependencies

    ```
    mvn clean
    ```

3. In src/test/resources/com/teamsnap/first.conf.json, replace ```username``` and ```access key``` values with the ones sent to you via the google doc.

4. Open the appium inspector and select Browserstack as the Cloud Provider.

5. Enter the browserstack username and access key (same values that you adjusted in step 2)

6. In desired capabilities, set the platformName, deviceName, and app. (hint: you can find the app path in the first.conf.json file)

7. Start the Appium Inspector session and go through the steps of a user logging into the TeamSnap app. Make sure to copy down the element names to use for your test case. The username and password for the TeamSnap app were sent to you via the google doc.

8. Write out your test case to log into the TeamSnap app in src/test/java/com/teamsnap/AppTest.java. There is a spot called:   /* Write your test steps here */

9. Run your test! Here are two options:

    Using Maven: ```mvn test -P first```

    Manually execute:
    - Open AppTest.java
    - Right click --> Run Java

10. View your test results on the [Browserstack Dashboard](https://app-automate.browserstack.com/dashboard). You will need to log into Browserstack with the email and password sent to you in the google doc.

