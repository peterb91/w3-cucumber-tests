package w3.org.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    // Environment constants
    public static final Integer TIMEOUT_FOR_PAGE_LOAD = 15;

    // Session variables
    public static final String PAGE_LINKS_URLS_LIST = "PAGE_LINKS_URLS_LIST";
    public static final String GET_PAGE_LINKS_RESPONSE_LIST = "GET_PAGE_LINKS_RESPONSE_LIST";
}
