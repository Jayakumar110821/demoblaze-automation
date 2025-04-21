Feature: Sanity Check for Demo Blaze Website

  Scenario: Edu_1 - Verify Home Page Title
    Given user opens the browser
    When user navigates to "https://www.demoblaze.com/"
    Then page title should be "STORE"

  Scenario: Edu_2 - Verify greeting message after login
    Given user opens the browser
    When user navigates to "https://www.demoblaze.com/"
    And user logs in with username "testuser123" and password "testuser123"
    Then user should see greeting message with username "testuser123"

  Scenario: Edu_3 - Verify categories exist
    Given user opens the browser
    When user navigates to "https://www.demoblaze.com/"
    Then category list should contain "Phones", "Laptops", and "Monitors"

  Scenario: Edu_4 - Add item to cart and verify
    Given user opens the browser
    When user navigates to "https://www.demoblaze.com/"
    And user adds "Samsung galaxy s6" to the cart
    Then cart should contain "Samsung galaxy s6"

  Scenario: Edu_5 - Verify contact form popup
    Given user opens the browser
    When user navigates to "https://www.demoblaze.com/"
    And user opens contact form
    Then contact form should be visible
