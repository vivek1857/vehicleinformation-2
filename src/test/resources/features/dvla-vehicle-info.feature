#This is to check test result for Pass test case
Feature: DVLA Vechicle search
Scenario: Login functionality exists
	Given I have opened the browser
	When I navigate to DVLA Vechicle information site
	When I click on Start now
	And I enter registration number "YR12UMB"
	And I click on continue button
	Then I take a screenshot
	Then Login button should exist

#This is to check test result for Failed test case
#Scenario: Forgot password exists
#Given I have open the browser
#When I open Facebook website
#Then Forgot password link should exist