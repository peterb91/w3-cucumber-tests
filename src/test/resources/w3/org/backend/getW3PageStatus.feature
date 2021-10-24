Feature: W3 pages shall return status "OK" on load

  User should receive response status code 200 for GET requests send to w3.org pages.

  Scenario Outline: I can get w3.org pages documents
    When I send GET request to "<endpointName>" endpoint
    Then the API response code is 200

    Examples:
    | endpointName |
    | BAD_PAGE     |
    | MULTI_MODAL  |
    | HTML_CSS     |
