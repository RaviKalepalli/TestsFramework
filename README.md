# TestsFramework
PageObjectModel

Contains:
test framework including abstract page class, 
framework with 'before' and 'after' annotations
Properties file reader
Data provider class (reading from excel)
Page Objects for amazon and redbox
Test Class

Tasks:
===============================================
*** amazon Test "amazonRokuStreamingStickTest"
Open http://www.amazon.com 
Validate the page has loaded
Search for Roku Streaming Stick (3600R) (2016 Model)
Verify this item is the first result
Click this product
Verify that:
Price is below 50
Free shipping is available

==============================================
*** amazon Test "CartTest"
Open http://www.amazon.com
Validate the page has loaded.
Search for Great Northern Popcorn Original Stainless Steel Stove Top
Validate the first result has Prime shipping
Add this product to the Shopping Cart
Search for Gold Medal Flavacol
Validate the first result is #1 Best Seller
Add this product to the Shopping Cart
Verify that your shopping cart has two items
==============================================

*** amazon Test "LogInTest"
Open http://www.amazon.com 
Validate the page has loaded.
Sign In into the provided account
Hover the Your Account button
Click the Sign In button in the overlay menu.
Input the username and password
Click the Sign In button 
Validate the login was successful
Click the Your Account
Under Account Settings, click Login & Security Settings
Find Name: 
Print the name in the console

==============================================
*** redbox Test "batmanRowCheckingTest"
Create a new method in page Object to identify if a row of results exists.
This method will read the row number from a properties file.
If the row exists, print how many items are available in that row.

Use XPath locators
==============================================

*** redbox Test "clickHoverMenu"
Write a test to:
Open http://www.redbox.com
Hover the DVD & BLU-RAY menu
Click the Most Popular link
For all 20 movies in the page, print in the log:
Movie title
Movie length
==============================================

