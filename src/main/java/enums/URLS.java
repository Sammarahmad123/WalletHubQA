package enums;

public enum URLS {
    FACEBOOK("https://www.facebook.com"),
    WALLET_HUB("https://wallethub.com/join/login");

    private final String url;

    URLS(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
