#Author: KS

Feature: User Login

Scenario: Test gestartet
 
  @tag1
  Scenario: Browser auswählen
    Given Browser Chrome wird gewaehlt
    When Button Chrome gedrueckt ist
    Then Browser per Chrome starten
    

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
