package setup;

import enums.ApplicationsPaths;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestCapabilities {

    private Properties capabilities = new Properties();
    private String propertyPath;

    public Properties getProperties(String appType) throws IOException {

        propertyPath = appType.equals("web") ?
                ApplicationsPaths.WEB_APP.getFilePath() :
                (appType.equals("native")) ?
                        ApplicationsPaths.NATIVE_APP.getFilePath() : null;

        FileInputStream propertiesFile = new FileInputStream(System.getProperty("user.dir") + propertyPath);

        capabilities.load(propertiesFile);
        propertiesFile.close();

        return capabilities;
    }

    public String readProperty(String appType, String key) throws IOException {
        if (!capabilities.containsKey(key)) {
            capabilities = getProperties(appType);
        }

        return capabilities.getProperty(key);
    }
}
