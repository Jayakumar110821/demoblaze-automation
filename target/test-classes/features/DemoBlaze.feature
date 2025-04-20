Feature: Demo Blaze Access

  Scenario: Verify homepage
    Given user opens the browser
    When user navigates to "https://www.demoblaze.com/"
    Then user should see the homepage
