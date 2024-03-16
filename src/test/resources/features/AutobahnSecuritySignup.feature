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
    And  User click on start using autobahn button
    Then User tried change tabs to 'email' page
    Then User refresh the page and click on received verification email
    Then Close ads popup if ads is showing up
    And  User click on verify account button
    Then User wait for 10 seconds
    Then User tried change tabs to 'Autobahn' page
    Then User wait for 10 seconds
    Then User should be redirected to 'Autobahn home' page
    Then User should be successfully registered

    Examples:
      | emailProvider  | email    | password  | firstName  | lastName    | industry              | country    | phoneNumber   |
      | default        | default  | default   | random     | random      | Technology Services   | Indonesia  | random        |