package w3.org;

import w3.org.config.TestConfig;

public enum W3OrgEndPoints {

    BAD_PAGE(TestConfig.W3ORG_BASE_URI + "/standards/badpage"),
    MULTI_MODAL(TestConfig.W3ORG_BASE_URI + "/standards/webofdevices/multimodal"),
    HTML_CSS(TestConfig.W3ORG_BASE_URI + "/standards/webdesign/htmlcss");

    private final String url;

    W3OrgEndPoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
