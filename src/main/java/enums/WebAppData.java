package enums;

public enum WebAppData {

    WEBSITE_TITLE("Internet Assigned Numbers Authority"),
    URL("https://www.iana.org/");

    final String data;

    WebAppData(String text) {
        data = text;
    }

    public String getData() {
        return data;
    }
}
