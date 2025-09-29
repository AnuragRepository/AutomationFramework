@tag
  Feature: Ecommerce Site

 Background:
   Given Open ecommerce Login page

 @tag
 Scenario Outline: Add item and submit
   Given Login with username <emailID> and password <password>
   When  Select products <item> and add to cart
   And   Verify added product <item> in cart and checkout by select country <textcountrytoSelect>,<countrytoSelect>
   Then  Verify success message "THANKYOU FOR THE ORDER."

   Examples:
     | emailID                     | password        |  item                              | textcountrytoSelect | countrytoSelect|
     | test786test786@gmail.com    | Automation@01   |  ZARA COAT 3,ADIDAS ORIGINAL       | Ind                 | India          |

   Examples:
     | emailID                     | password        |  item                              | textcountrytoSelect | countrytoSelect|
     | test486test486@gmail.com    | Automation@01   |  iphone 13 pro                      | Ind                 | India          |

