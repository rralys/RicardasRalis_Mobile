package enums;

public enum ApplicationsPaths {

    WEB_APP("/src/main/resources/testweb.properties"),
    NATIVE_APP("/src/main/resources/testnative.properties");

    final String filePath;

    ApplicationsPaths(String path) {
        filePath = path;
    }

    public String getFilePath() {
        return filePath;
    }
}
