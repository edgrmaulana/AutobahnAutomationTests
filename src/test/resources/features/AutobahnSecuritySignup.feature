@AutobahnSignupPageFeature

Feature: Automation UI Testing for Autobahn Signup Page

  @Positive
  Scenario Outline: Verify Autobahn Signup page with user '<email>' should be working normally
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

    @Negative
    Scenario Outline: Verify Autobahn Signup page with user '<user>' should be failed
      When User get email from temporary email provider in '<emailProvider>'
      And  User copying email from temporary email page
      Then User tried to open Autobahn Signup Page
      Then User tried to input user email as '<email>'
      And  User tried to input user password as '<password>'
      And  User click on sign up button
      Then User wait for 5 seconds
      Then User error notification should be showing up as '<errorNotification>'

      Examples:
        | emailProvider | email    | password  | errorNotification                            | user             |
        | default       | existing | default   | There was an error creating your account.    | existing account |
        | default       | default  | Qwerty123 | Weak                                         | weak password    |

    @Negative
    Scenario Outline: Verify Autobahn Signup page with user '<user>' should be failed
      When User get email from temporary email provider in 'default'
      And  User copying email from temporary email page
      Then User tried to open Autobahn Signup Page
      And  User tried to input user email as 'default'
      And  User tried to input user password as 'default'
      And  User click on sign up button
      Then User wait for 5 seconds
      Then User tried to input first name as '<firstName>'
      And  User tried to input last name as '<lastName>'
      And  User tried to to choose industry as '<industry>'
      And  User tried to choose phone number country to '<country>'
      And  User tried to input phone number as '<phoneNumber>'
      Then User error notification should be showing up as '<errorNotification>'

    Examples:
      | firstName  | lastName    | industry              | country    | phoneNumber   | errorNotification                 | user                    |
      | random     | random      | Technology Services   | Indonesia  | 12345678      | Please enter a valid phone number | incorrect phone number  |
      |            | random      | Technology Services   | Indonesia  | random        | Field cannot be empty             | incorrect first name    |
      | random     |             | Technology Services   | Indonesia  | random        | Field cannot be empty             | incorrect last name     |
      |            |             | Technology Services   | Indonesia  | random        | Field cannot be empty             | empty name              |