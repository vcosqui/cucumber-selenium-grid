Feature: Login

  Scenario: User with right credentials is able to login
    Given USER accesses application
    When USER provides its credentials
    Then USER is granted to access application
