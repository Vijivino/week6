Feature: login to salesforce

#Background: 
#Given launch the chrome browser 
#And load the url and maximize and disable notifications

Scenario Outline: create opportunity
When enter the username as 'viji@testleaf.com'
And enter the password as 'Alpha@123'
And click login
And click toggle menu
And click view all
And click opportunity tab
And click new button
When enter the opportunity name as <opportunityname>
And store the input text
And enter the stage as <stagename>
And choose date and click save
Then verify opportunity name

Examples:
|opportunityname|stagename|
|Sales Automation|Needs Analysis|
|Sales|Needs Analysis|

 
