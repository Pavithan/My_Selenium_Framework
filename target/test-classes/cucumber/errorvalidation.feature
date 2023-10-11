
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I landed on the e-commerce page
    When Logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed.

  Examples: 
    | username                 | password 	 | product         | 
    | "sripavithran@gmail.com" | "Kuttima#1" | ADIDAS ORIGINAL | 
