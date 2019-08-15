package scenarios.testWebApp;

import enums.WebAppData;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import scenarios.hooks.Hooks;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.testng.Assert.assertEquals;

@Test(groups = "web")
public class TestWebSite extends Hooks {

    TestWebSite() {
        super("web");
    }

    @Test(description = "This test verifies that the web app opens in the mobile device")
    public void verifyWebApp() throws Exception {
        getDriverSingle().get(site);
        getWaitSingle().until(ExpectedConditions.urlToBe(site+"/"));
        assertEquals(getDriverSingle().getTitle(), WebAppData.WEBSITE_TITLE.getData());

        URL url = new URL(WebAppData.URL.getData());

        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        assertEquals(connection.getResponseCode(), 200);

        System.out.println("The site has been successfully opened in the mobile device.");
    }

}
