Feature: Open InerG Test App Search Page

  Scenario: Search for Kerala and extract total cases
    Given I launch the browser
    When I open the URL "https://inerg-test.web.app/"
    Then The page title should contain "InerG Test App"
    When I select Kerala from the state dropdown
    Then I scroll down to the line chart
    Then I print the values at each point from the line chart
