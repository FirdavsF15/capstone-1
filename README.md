# Capstone 1 - Ledger Application

This CLI app keeps track of a collection of transactions like ledger. This includes payments and deposits which are distinguished by their amount sign. Users can add deposits and payments, view all transactions or only payments/deposits. They also have a robust reports screen to filter their transactions searching.

Features include:

- Add deposit
- Add payment
- list all transactions entries
- list all deposit entries
- list all payment entries
- run pre-defined reports based on month, year and vendor preferences

## Instructions to run code
- **1. Prerequisites:** running on JDK ms-17
- **2. Clone the repository:** Easiest way is to click the green Code button,open with github desktop, and open using IntelliJ, open src ->main->java->com.pluralsight->Ledger.java
- **3. Project is now open in IntelliJ:** Transactions.csv is included when you clone the repo
- **4. Running the application:** Ledger.java contains the `main` method to run
- **5. Starting page of Ledger:** when you run the code you should see a big HOME page and options instructing what the different options do to run the ledger


```java 
private static void displayPreviousMonth(){
        LocalDate date = LocalDate.now();

        for(Transaction transaction: transactions){
            LocalDate transactionDate = LocalDate.parse(transaction.getDate());
            //store previous month
            int previousMonth = date.getMonthValue() - 1;
            int year = date.getYear();

            //if we are currently in january, change month to december and go back a year
            if(date.getMonthValue()==1){
                previousMonth = 12;
                year = year - 1;
            }

            if(previousMonth == transactionDate.getMonthValue() && year==transactionDate.getYear()){
                transaction.display();
            }
        }
    }
```

This method is going to print all transactions in transactions.csv from the previous month.
It follows a similar format to the other display methods, however because we are looking for the previous month there is now a special edge case.
If the current date's month is January, we can't simply do date.getMonthValue()-1 because that would be 0.
The correct answer is December so we have to use if statements to check if we are at January and plan accordingly.

> At first, I was using fileReader in all display methods I had written so far to read each line from the transactions.csv and check values or print those Strings.
> Dave let me know in a meeting that having a Transaction class, an arrayList of Transaction objects and helper methods could make the code a lot simpler.
> Now to look through all transactions, I am using a for each loop to go through an arrayList of Transaction objects.



