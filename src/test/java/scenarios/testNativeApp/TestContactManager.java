package scenarios.testNativeApp;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import scenarios.hooks.Hooks;

import static enums.ContactManagerSelectors.*;
import static org.testng.Assert.assertTrue;

@Test(groups = "native")
public class TestContactManager extends Hooks {

    TestContactManager() {
        super("native");
    }

    @Test(description = "Verify ContactManager layout.")
    public void verifyContactManagerLayout() throws Exception{
        getDriverSingle().findElement(By.id(ADD_BUTTON.getSelector())).click();
        assertTrue(getDriverSingle().findElement(By.id(ADD_CONTACT_TITLE.getSelector())).isDisplayed());
        assertTrue(getDriverSingle().findElement(By.id(TARGET_ACCOUNT_FIELD.getSelector())).isDisplayed());
        assertTrue(getDriverSingle().findElement(By.id(CONTACT_NAME_FIELD.getSelector())).isDisplayed());
        assertTrue(getDriverSingle().findElement(By.id(CONTACT_PHONE_FIELD.getSelector())).isDisplayed());
        System.out.println("The ContactManager layout has been verified.");
    }
}
