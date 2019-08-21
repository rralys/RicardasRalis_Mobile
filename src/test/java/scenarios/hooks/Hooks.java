package scenarios.hooks;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import setup.DriverSetup;

public class Hooks extends DriverSetup {

    protected Hooks(String type) {
        super(type);
    }

    @BeforeSuite(description = "Set driver for test running")
    public void setup() throws Exception {
        prepareDriver();
        System.out.println("The test driver has been set.");
    }

    @BeforeGroups(description = "Set driver for native app test running", groups = "native")
    public void setupNative() throws Exception {
        prepareDriver("native");
        System.out.println("The native app driver has been set.");
    }

    @BeforeGroups(description = "Set driver for web application test running", groups = "web")
    public void setupWeb() throws Exception {
        prepareDriver("web");
        System.out.println("The browser driver has been set.");
    }

    @AfterSuite(description = "Close active driver for the native app", groups = {"native", "web"})
    public void tearDownNative() throws Exception {
        getDriverSingle().closeApp();
        System.out.println("The driver has been destroyed.");
    }
}
