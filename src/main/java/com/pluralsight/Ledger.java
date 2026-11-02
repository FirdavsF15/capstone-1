package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
            FileWriter fileWriter = new FileWriter("transactions.csv");
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            String desc, vendor;
            double amount;

            System.out.println("Adding deposit, please enter information below");
            System.out.print("Enter the deposit description: ");
            desc = scanner.nextLine();
            System.out.print("Enter the deposit vendor: ");
            vendor = scanner.nextLine();
            System.out.print("Enter the deposit amount: ");
            amount = Double.parseDouble(scanner.nextLine());



        }catch(Exception e){
            System.out.println("There was an oopsie");
            e.printStackTrace();
        }
    }

    public static void makePayment(){

    }
}

