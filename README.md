
# CapitalOne-Interview

Candidate name: Karthik Mohana Krishnan

To run the project : 

1. Clone the project to local folder location.

2. run - mvn clean install.

3. Add the project to web server and run the same.

4. Navigate from the home page URL : http://localhost:8080/transactionManager/ to different operations displayed in UI.


This project contains the following operations : 

1. Get All Transactions - This operations fetches all the transactions for the User with UID "1110590645"

2. Get average for Month and Year - This operation fetches the transactions for the specified month and year.

   Its defaulted to August 2015 for sample purposes.

3. Get Transaction Aggregate - This operation aggregates the spending and income month wise in every year.

4. Get Transactions Ignoring CC - This operation identifies the Credit card payments and removes them from average spending and income calculations.

5. Get Transactions Ignoring Donuts - This operation fetches the transactions ignoring the donut transactions.

6. Crystal Ball - This operation fetches the projected transactions for future months and merges with past date transactions.    Finally identifies the yearly projected average income and spending.
