Feature: Application Login

Scenario: Home page default login
Given user is on net bancking landing page
When user login into the application with username "Sai" and password "1234"
Then Home page is populated
And Cards are displayed "true"


Scenario: Home page default login
Given user is on net bancking landing page
When user login into the application with username "JIN" and password "123456"
Then Home page is populated
And Cards are displayed "false"