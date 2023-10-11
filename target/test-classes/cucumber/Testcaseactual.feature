
@tag
Feature: Purchase the order from the Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page.

  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with <username> and <password>
    When I add the product <product> to cart
    And Checkout <product> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed in the confirmation page.

  Examples: 
    | username                 	 | password 	 | product         | 
    | "sripavithran99@gmail.com" | "Kuttima#1" | ADIDAS ORIGINAL | 
