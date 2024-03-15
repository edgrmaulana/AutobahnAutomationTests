@AutobahnSignupPageFeature

Feature: Automation UI Testing for Autobahn Signup Page

  @Positive
  Scenario Outline: Verify Autobahn Signup page with '<reason>'
    When User get email from temporary email provider in '<emailProvider>'
    And  User copying email from temporary email page
    Then User tried to open Autobahn Signup Page
    And  User tried to input user email as '<email>'
    And  User tried to input user password as '<password>'
    And  User click on sign up button
    Then User wait for 5 seconds
    Then User tried to input first name as '<firstName>'
    Then User tried to input last name as '<lastName>'
    Then User tried to input


    Examples:
      | emailProvider  | email    | password  | firstName     | promoType   | reason              |
      | default        | default  | default   | Edgar Maulana | none        | user without promo  |