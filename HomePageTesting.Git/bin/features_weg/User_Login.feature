#Author: KS

Feature: User Login

Scenario: User ist auf der Homepage
 
  @tag1
  Scenario: User klickt MyAccount
    Given User ist auf der Homepage
    When User klickt MyAccount
    Then Aufruf der MyAccount Seite
    

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
