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
    And  User tried to input last name as '<lastName>'
    And  User tried to to choose industry as '<industry>'
    And  User tried to choose phone number country to '<country>'
    And  User tried to input phone number as '<phoneNumber>'
    Then User go to temporary email provider page and refresh the email
    Then User click on received verification email
    And  User click on verify account button
    Then User go to Autobahn homepage
    And  User should be successfully registered


    Examples:
      | emailProvider  | email    | password  | firstName     | industry   | country              | phoneNumber   |
      | default        | default  | default   | Edgar Maulana | none        | user without promo  |               |