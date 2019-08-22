# FutureTransactionsSummary

This project is intended to read a input file text and output a csv 

System A has produced the file Input.txt, which is a Fixed Width text file that contains the Future Transactions done by client 1234

Requirements:
The Business user would like to see the Total Transaction Amount of each unique product they have done for the day
The Business user would like a program that can read in the Input file and generate a daily summary report
The Daily summary report should be in CSV format (called Output.csv) with the following specifications

The CSV has the following Headers
- Client_Information
- Product_Information
- Total_Transaction_Amount

Client_Information should be a combination of the CLIENT TYPE, CLIENT NUMBER, ACCOUNT NUMBER, SUBACCOUNT NUMBER fields from Input file
Product_Information should be a combination of the EXCHANGE CODE, PRODUCT GROUP CODE, SYMBOL, EXPIRATION DATE
Total_Transaction_Amount should be a Net Total of the (QUANTITY LONG - QUANTITY SHORT) values for each client per product

Notes: Each Record in the input file represents ONE Transaction from the client for a particular product. Please focus on code re-usability. 

Initial Setup needed for the project:Steps:

1.Get the project into your workspace.
2.The input file and output file locations can be mentioned in the propertyfile ConfigProperties.properties
3.Place the input file in the location mentioned in the property file
4.Run Main 
5.You will see the output file generated in the location you mentioned in the property file.




