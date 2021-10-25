Feature: All links visible on any w3 page shall be valid (lead to existing page)

  All links on `w3.org` pages (subpages) should enable user to go to existing page.

  Scenario Outline: Every link on the page lead to another existing page
    Given w3 page "<pageName>" is loaded
    When I collect all links urls
    And I check every link url
    Then every link is valid

    Examples:
      | pageName    |
      | BAD_PAGE    |
      | MULTI_MODAL |
      | HTML_CSS    |
