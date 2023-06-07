package enums;

public enum URLS {
    FACEBOOK("https://www.facebook.com"),
    GOOGLE("https://www.google.com");

    private final String url;

    URLS(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
