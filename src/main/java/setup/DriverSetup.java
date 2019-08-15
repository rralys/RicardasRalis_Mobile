package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetup extends TestCapabilities {

    private static AppiumDriver driverSingle = null;
    protected DesiredCapabilities capabilities;
    private static WebDriverWait waitSingle;

    protected static String deviceName;
    protected static String testPlatform;
    protected static String appDriver;
    protected static String app;
    protected static String site;
    private static String appType;
    private static String browserName;

    protected DriverSetup(String type) {
        appType = type;
    }

    private void setCapabilities(String type) throws Exception {

        app = getCapability(type, "app");
        site = getCapability(type, "site");
        site = site == null ? null : "http://" + site;

        deviceName = getCapability(type, "deviceName");
        testPlatform = getCapability(type, "platform");
        appDriver = getCapability(type, "driver");

    }

    private void setPlatformCapability() {

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, testPlatform);

        if (testPlatform == "iOS") {
            browserName = "Safari";
            return;
        }

        browserName = "Chrome";
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);

    }

    private void setAppCapability() {

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
        setAppCapability();

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

    public WebDriverWait getWaitSingle() throws Exception {
        return waitSingle;
    }

    protected void prepareDriver(String type) throws Exception {
        setCapabilities(type);
        setDriver();
    }

    protected void prepareDriver() throws Exception{
        setCapabilities(appType);
        setDriver();
    }
}
