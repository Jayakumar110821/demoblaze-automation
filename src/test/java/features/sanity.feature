Feature: Sanity Check for Demo Blaze Website

  Scenario Outline: Validate Demo Blaze homepage
    Given user opens the browser
    When user navigates to "<url>"
    Then user should see the homepage

    Examples:
      | url                         |
      | https://www.demoblaze.com/ |

  Scenario: Validate sign-up page navigation
    Given user opens the browser
    When user navigates to "https://www.demoblaze.com/"
    And user clicks on Sign Up
    Then user should see the Sign Up modal
