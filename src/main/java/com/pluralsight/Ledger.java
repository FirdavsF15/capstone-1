package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {

    public static Scanner scanner = new Scanner(System.in);

    public static ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public static void main(String[] args) {

        boolean ultimateExit = false;
        String homePageInput;

        loadTransactionList();

        do{
            homeScreenPrint();
            homePageInput = scanner.nextLine().toUpperCase().trim();

            switch(homePageInput){
                case "D"->{
                    addDeposit();
                    break;
                }
                case "P"->{
                    makePayment();
                    break;
                }
                case "L"->{
                    ledgerLoop();
                    break;
                }
                case "X"->{
                    System.out.println("THANKS FOR USING MY APP BYE BYE");
                    ultimateExit = true;
                }
            }
        }while(!ultimateExit);

    }

    private static void loadTransactionList() {
        try{
            FileReader allReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader allBufReader = new BufferedReader(allReader);

            allBufReader.readLine();
            String input;

            while((input = allBufReader.readLine())!=null){
                String[] parts = input.split("\\|");

                String date = parts[0];
                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                Transaction initialTransaction = new Transaction(date, time, description, vendor, amount);

                transactions.add(initialTransaction);

            }
            allBufReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void ledgerLoop(){

        boolean ledgerExit = false;
        do{
            ledgerPrint();
            String ledgerInput = scanner.nextLine().toUpperCase().trim();


            switch(ledgerInput){
                case "A" ->{
                    displayAll();
                    break;
                }
                case "D" ->{
                    displayDeposits();
                    break;
                }
                case "P"->{
                    displayPayments();
                    break;
                }
                case "R"->{
                    reportsLoop();
                    break;
                }
                case "H" ->{
                    ledgerExit = true;
                    break;
                }
            }
        }while(!ledgerExit);




    }

    public static void reportsLoop(){
        boolean reportsExit = false;

        do{
            reportsPrint();

            int reportsInput = Integer.parseInt(scanner.nextLine());

            switch(reportsInput){
                case 1->{
                    displayMonthToDate();
                    break;
                }
                case 2->{
                    displayPreviousMonth();
                    break;
                }
                case 0 ->{
                    reportsExit = true;
                    break;
                }
            }

        }while(!reportsExit);


    }

    private static void displayMonthToDate() {
        // todo: what's today's year and month?
        // loop through my transactions and if the month and year is the same as todays, then, uh, print it

        LocalDate date = LocalDate.now();

        for(Transaction transaction: transactions){
            LocalDate transactionDate = LocalDate.parse(transaction.getDate());

            if(date.getYear() == transactionDate.getYear() && date.getMonth() == transactionDate.getMonth() && transactionDate.getDayOfMonth()<=date.getDayOfMonth()){
                transaction.display();
            }
        }
    }

    private static void displayPreviousMonth(){

    }

    public static void displayAll(){
        for(Transaction t: transactions){
            t.display();
        }
    }

    public static void displayDeposits(){
        for(Transaction t: transactions){
            if(t.isDeposit()){
                t.display();
            }
        }
    }

    public static void displayPayments(){
        for(Transaction t: transactions){
            if(t.isPayment()){
                t.display();
            }
        }
    }


    public static void addDeposit(){
        try{
            //create filewriter and bufferedwriter to add to transactions.csv
            FileWriter depositWriter = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter depositBufWriter = new BufferedWriter(depositWriter);

            //Ask user for deposit information and store it in appropriate variables
            System.out.println("Adding deposit, please enter information below");
            System.out.print("Enter the deposit description: ");
            String desc = scanner.nextLine();
            System.out.print("Enter the deposit vendor: ");
            String vendor = scanner.nextLine();
            System.out.print("Enter the deposit amount: ");
            double amount = Double.parseDouble(scanner.nextLine());



            //store timestamp for the deposit the user just inputted
            LocalDate depositDay = LocalDate.now();
            LocalTime depositTime = LocalTime.now();

            //reformat the time to not show millisecond
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
            String depositTimeFormatted = depositTime.format(fmt);

            Transaction newTransaction = new Transaction(depositDay.toString(), depositTimeFormatted,desc, vendor, amount);
            transactions.add(newTransaction);

            //store all inputted deposit information in a string formatted to match the csv file and write it into that file
            String deposit = String.format("\n%s|%s|%s|%s|%.2f",depositDay.toString(), depositTimeFormatted, desc, vendor, amount);
            depositBufWriter.write(deposit);
            depositBufWriter.close();

        }catch(Exception e){
            System.out.println("There was an oopsie");
            e.printStackTrace();
        }
    }

    public static void makePayment(){
        try{
            FileWriter paymentWriter = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter paymentBufWriter = new BufferedWriter(paymentWriter);

            //Ask user for payment information and store it in appropriate variables
            System.out.println("Making payment, please enter information below");
            System.out.print("Enter the payment description: ");
            String desc = scanner.nextLine();
            System.out.print("Enter the payment vendor: ");
            String vendor = scanner.nextLine();
            System.out.print("Enter the payment amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            //store timestamp for the payment the user just inputted
            LocalDate depositDay = LocalDate.now();
            LocalTime depositTime = LocalTime.now();

            //reformat the time to not show millisecond
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
            String depositTimeFormatted = depositTime.format(fmt);

            Transaction newTransaction = new Transaction(depositDay.toString(), depositTimeFormatted,desc, vendor, -amount);
            transactions.add(newTransaction);

            //store all inputted payment information in a string formatted to match the csv file and write it into that file
            String deposit = String.format("\n%s|%s|%s|%s|%.2f",depositDay.toString(), depositTimeFormatted, desc, vendor, -amount);
            paymentBufWriter.write(deposit);
            paymentBufWriter.close();




        }catch(Exception e){
            e.printStackTrace();
        }

    }





    public static void homeScreenPrint(){
        System.out.println("""
                _________________________
                (-----------------------)
                (----- HOME SCREEN -----)
                (-----------------------)
                """);

        System.out.print("""
                    D - Add Deposit
                    P - Make Payment(Debit)
                    L - Ledger
                    X - Exit app
                    
                    Choose an option:""");
    }

    public static void ledgerPrint(){
        System.out.println("""
                _________________________
                <----------------------->
                <---- LEDGER SCREEN ---->
                <----------------------->
                """);

        System.out.println("""
                A - All Entries
                D - Display deposits
                P - Display payments
                R - Run reports
                H - GO back to home page
               
                """);

        System.out.print("Enter an option: ");
    }

    public static void reportsPrint(){
        System.out.println("""
                    ^^^^^^^^^^^^^^^^^^^^^^^^^^^
                    ----- REPORTS SCREEN ------
                    ___________________________
                    """);

        System.out.print("""
                    1 - Month to date
                    2 - Previous Month
                    3 - Year to Date 
                    4 - Previous Year
                    5 - Search By Vendor
                    0 - Back to Ledger screen
                    
                    Enter an option:""");
    }
}

