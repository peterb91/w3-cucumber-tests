Feature: W3 pages shall return correct response code on page load

  User should receive response code `200` for `GET` requests send to valid `w3.org` pages and `404` for invalid pages.

  Scenario Outline: I can get w3.org pages documents
    Given the w3 org BASE.URI is set
    When I send GET request to "<endpointName>" endpoint
    Then the API response code is <responseCode>

    Examples:
    | endpointName | responseCode |
    | BAD_PAGE     | 404          |
    | MULTI_MODAL  | 200          |
    | HTML_CSS     | 200          |
