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
##@tag
##Feature: Title of your feature
  ##I want to use this template for my feature file

  ##@tag1
  ##Scenario: Title of your scenario
    ##Given I want to write a step with precondition
		##And some other precondition
  	##When I complete action
  	##And some other action
    ##And yet another action
    ##Then I validate the outcomes
    ##And check more outcomes

  ##@tag2
  ##Scenario Outline: Title of your scenario outline
    ##Given I want to write a step with <name>
    ##When I check for the <value> in step
    ##Then I verify the <status> in step

    ##Examples: 
      ##| name  | value | status  |
      ##| name1 |     5 | success |
      ##| name2 |     7 | Fail    |
      
Feature: Validaing place Api's
@AddPlaceApi @Regression
Scenario Outline: verfying a place is being successfully added using AddPlaceApi.
Given Add place Payload "<name>" "<address>" "<phone_number>" <lat> <lng> <accuracy> "<language>" "<website>" "<types>"
When user calls "addPlaceApi" with "POST" Http request
Then Api call is success with status code 200
And "status" in responce body is "OK"
And "scope" in responce body is "APP"
And Verify place Id on Newly Added Maps with UserName "<name>", Api "getPlaceApi" and Http request "GET"

Examples:
|name  | address  | phone_number | lat   | lng   | accuracy | language | website | types 					 | 
|Sai   | Tirupati | 1234567890   | 100.3 | 100.1 | 50       | English  | Maps    | schools,colleges|
|Sai   | Tirupati | 1234567890   | 104.3 | 105.1 | 51       | English  | chrome  | schools,colleges,Home|
@deletePlaceApi @Regression
Scenario: verify the delete place functionlity
Given Delete payload
When user calls "deletePlaceApi" with "delete" Http request
Then Api call is success with status code 200
And "status" in responce body is "OK"

