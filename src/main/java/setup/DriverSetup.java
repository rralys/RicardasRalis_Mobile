package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class DriverSetup extends TestCapabilities {

    private static AppiumDriver driverSingle = null;
    protected DesiredCapabilities capabilities;
    private static WebDriverWait waitSingle;

    protected static String token;
    private static String endPoint;
    protected static String udid;
    protected static String appPackage;
    protected static String appActivity;
    protected static String testPlatform;
    protected static String appDriver;
    protected static String app;
    protected static String buildCheck;
    protected static String site;
    private static String appType;
    private static String browserName;

    protected DriverSetup(String type) {
        appType = type;
    }

    private void readCapabilities(String type) throws Exception {

        app = readProperty(type, "app");
        site = readProperty(type, "site");
        site = site == null ? null : "http://" + site;

        endPoint = readProperty(type, "endPoint");
        udid = readProperty(type,"udid");
        testPlatform = readProperty(type, "platform");
        appPackage = readProperty(type, "appPackage");
        appActivity = readProperty(type, "appActivity");

        appDriver = readProperty(type, "driver");
        token = readProperty(type, "token");
        buildCheck = readProperty(type, "disableBuildCheck");

    }

    private void setPlatformCapability() {

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, testPlatform);
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("chromedriverDisableBuildCheck", buildCheck);

        if (testPlatform == "iOS") {
            browserName = "Safari";
            return;
        }

        browserName = "Chrome";

    }

    private void setAppTypeCapability() {

        if (site == null) {
            File file = new File(app);
            capabilities.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
        } else {
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
        }
    }

    protected void setDriver() throws MalformedURLException {
        capabilities = new DesiredCapabilities();

        setPlatformCapability();
        setAppTypeCapability();

        if (driverSingle == null) {
            driverSingle = new AppiumDriver(new URL(appDriver), capabilities);
        }

        if (waitSingle == null) {
            waitSingle = new WebDriverWait(driverSingle, 100);
        }
    }

    public AppiumDriver getDriverSingle() throws Exception {
        if (driverSingle == null) {
            setDriver();
        }

        return driverSingle;
    }

    public WebDriverWait getWaitSingle() {
        return waitSingle;
    }

    protected void prepareDriver(String type) throws Exception {
        readCapabilities(type);

        if (type.equals("native")) {
            remoteInstallApk();
        }

        setDriver();
    }

    public void remoteInstallApk() {

        File appFile = new File(System.getProperty("user.dir") + "\\" + app);

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(endPoint + "/" + udid)
                .addHeader("Authorization", "Bearer " + token)
                .addMultiPart(appFile)
                .setRelaxedHTTPSValidation()
                .build();

        io.restassured.response.Response response = given(requestSpecification)
                .post();

        System.out.println("Apk installation post request with the response with code: " + response.getStatusCode());
    }
}
