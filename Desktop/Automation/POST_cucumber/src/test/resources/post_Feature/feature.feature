#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: To post a request and to test status code for different scenarios

  @tag1
  Scenario: Sending service URL and End point URL
      Given First im getting "<service URL>"
    And im getting"<End Point URL>"   
    Then I am adding both url to get "<URI>"
    And check more outcomes

  @tag2
  Scenario Outline: Posting payload request
    Given I want to send "<name>"
    When the industry is "<industry>"
    And the level is "<level>"
    Then sending the "<description>"
    And giving the "<duration>"
    And giving the "<value>"
    

    Examples: 
      | name    | industry                 | level                    |description   |duration|value   |
      | Raj     | 5e4d98bb5f7f592524bc73b8 | 5e4d98bb5f7f592554d174fc |Food Servicing|10      |1000    |
      | Bharath | 5e4d98bb5f7f592524bc73b8 | 5e4d98bb5f7f592554d174fc |Food Servicing|10      |1000.02 |
      | Divisha | 5e4d98bb5f7f592524bc73b8 | 5e4d98bb5f7f592554d174fc |Food Servicing|10      |1000.02 |
      | Raj     | 5e4d98bb5f7f592524bc73b8 | 5e4d98bb5f7f592554d174fc |Water Supply  |10      |1000    |
      |         | 5e4d98bb5f7f592524bc73b8 | 5e4d98bb5f7f592554d174fc |Catering      |10      |1000.02 |
      | Prabu   | 5e4d98bb5f7f592524bc73b8 | 5e4d98bb5f7f592554d174fc |              |10      |452     |
      | Pragathi| 5e4d98bb5f7f592524bc73b8 | 5e4d98bb5f7f592554d174fc |Marriage Decor|        |1000.02 |
      | Kamali  | 5e4d98bb5f7f592524bc73b8 | 5e4d98bb5f7f592554d174fc |Receptionist  |10      |        |
      | Vaishhu | 5e4d98bb5f7f592524bc73b8 | 5e4d98bb5f7f592554d174fc |Music Band    |10      |1234.567|       
      | Keerthi | 5e4d98bb5f7f592524bc73b8 | 5e4d98bb5f7f592554d174fc |Seat Arrange  |10      |ab12*&  |
      | Divisha | 5e4d98bb5f7f592524bc73b8 | 5e4d98bb5f7f592554d174fc |Foodwater     |ab12*&  |1234    |
      
      
