Feature: All links visible on any W3 page shall be valid (lead to existing page)

  All links on w3.org pages (subpages) should enable user to go to existing page (the links should

  @chrome
  Scenario: Every link on the page lead to another existing page
    Given w3 page "<pageName>" is loaded
    When I collect all links urls
    And I check every link url
    Then every link is valid
