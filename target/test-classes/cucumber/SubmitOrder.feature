
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on the Confirmation Page

    Examples: 
      | name  								| password     | productName   |
      | rahulshetty@gmail.com | IamKing@000	 | IPHONE 13 PRO |
      
