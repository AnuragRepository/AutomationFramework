Feature: Error validation in ecommerce site

 Background:
 Given Open ecommerce Login page

@ErrorValidation
Scenario Outline: Login Validation
  Given Login with username <emailID> and password <password>
  Then Verify error message "Incorrect email or password."

  Examples:
  |emailID                      |password       |
  | test486test486@gmail.com    | Automation@   |