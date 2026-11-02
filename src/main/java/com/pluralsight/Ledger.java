package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Ledger {
    public static void main(String[] args) {

        boolean ultimateExit = false;
        Scanner scanner = new Scanner(System.in);


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

        }catch(Exception e){
            System.out.println("There was an oopsie");
            e.printStackTrace();
        }
    }

    public static void makePayment(){

    }
}

