Feature: W3 pages shall return no console errors on load.

  All w3.org pages (subpages) should be loaded without errors being logged in the console.

  @chrome
  Scenario Outline: No console errors on w3.org page load.
    Given w3 page "<pageName>" is loaded
    When I check console errors for the page
    Then there is no console errors visible.

    Examples:
      | pageName    |
      | BAD_PAGE    |
      | MULTI_MODAL |
      | HTML_CSS    |
