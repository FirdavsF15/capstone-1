package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ledger {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean ultimateExit = false;
        String homePageInput;

        do{
            homeScreenPrint();
            homePageInput = scanner.nextLine().toUpperCase();

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

            //Ask user for deposit information and store it in appropriate variables
            System.out.println("Making payment, please enter information below");
            System.out.print("Enter the payment description: ");
            String desc = scanner.nextLine();
            System.out.print("Enter the payment vendor: ");
            String vendor = scanner.nextLine();
            System.out.print("Enter the payment amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            //store timestamp for the deposit the user just inputted
            LocalDate depositDay = LocalDate.now();
            LocalTime depositTime = LocalTime.now();

            //reformat the time to not show millisecond
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
            String depositTimeFormatted = depositTime.format(fmt);

            //store all inputted deposit information in a string formatted to match the csv file and write it into that file
            String deposit = String.format("\n%s|%s|%s|%s|%.2f",depositDay.toString(), depositTimeFormatted, desc, vendor, -amount);
            paymentBufWriter.write(deposit);
            paymentBufWriter.close();




        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void ledgerLoop(){

        boolean ledgerExit = false;
        do{
            ledgerPrint();
            String ledgerInput = scanner.nextLine().toUpperCase();


            if(ledgerInput.equals("H")){
                ledgerExit = true;
            }
        }while(!ledgerExit);




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
}

