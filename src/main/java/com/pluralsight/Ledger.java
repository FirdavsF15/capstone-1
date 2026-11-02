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
            System.out.println("""
                _________________________
                (-----------------------)
                (----- HOME SCREEN -----)
                (-----------------------)
                """);

            System.out.println("""
                    D - Add Deposit
                    P - Make Payment(Debit)
                    L - Ledger
                    X - Exit app
                    
                    Choose an option:
                    """);

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
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

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

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
            String depositTimeFormatted = depositTime.format(fmt);

            String deposit = String.format("\n%s|%s|%s|%s|%.2f",depositDay.toString(), depositTimeFormatted, desc, vendor, amount);
            bufWriter.write(deposit);
            bufWriter.close();

        }catch(Exception e){
            System.out.println("There was an oopsie");
            e.printStackTrace();
        }
    }

    public static void makePayment(){

    }
}

