
package com.example;

import java.util.Scanner;

public class Main {

    public static String readText(Scanner sc, String message) {

        while (true) {

            System.out.print(message);
            String value = sc.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty!");
        }
    }

    public static int readNumber(Scanner sc, String message) {

        while (true) {

            System.out.print(message);

            try {

                int value = Integer.parseInt(sc.nextLine());

                if (value >= 0) {
                    return value;
                }

                System.out.println("Enter a non-negative number!");

            } catch (NumberFormatException e) {

                System.out.println("Invalid number! Try again.");
            }
        }
    }

    // Generate electricity bill
    public static void generateBill(Scanner sc) {

        System.out.println("\n--- CONSUMER REGISTRATION ---");

        String id = readText(sc, "Enter Consumer ID: ");
        String name = readText(sc, "Enter Consumer Name: ");
        String address = readText(sc, "Enter Address: ");
        String meter = readText(sc, "Enter Meter Number: ");

        Consumer consumer = new Consumer(
            id, name, address, meter
        );

        String month = readText(
            sc, "Enter Billing Month: "
        );

        System.out.println("\n--- METER READINGS ---");

        int previous = readNumber(
            sc, "Enter Previous Reading: "
        );

        int current;

        while (true) {

            current = readNumber(
                sc, "Enter Current Reading: "
            );

            if (current >= previous) {
                break;
            }

            System.out.println(
                "Current reading cannot be less than previous reading!"
            );
        }

        MeterReading reading = new MeterReading(
            previous, current
        );

        // Generate unique bill ID
        String billId = "BILL-" + System.currentTimeMillis();

        Bill bill = new Bill(
            billId, month, consumer, reading
        );

        bill.displayBill();

        // Save bill
        FileManager.saveBill(bill.getBillData());

    }

    // Main menu
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n==================================");
            System.out.println("     ELECTRICITY BILLING SYSTEM");
            System.out.println("==================================");

          System.out.println("1. Register Consumer & Generate Bill");
System.out.println("2. View Billing History");
System.out.println("3. Consumer-wise Summary");
System.out.println("4. Monthly Billing Summary");
System.out.println("5. Exit");
            System.out.println("----------------------------------");

            choice = readNumber(sc, "Enter your choice: ");

           
switch (choice) {

    case 1:
        generateBill(sc);
        break;

    case 2:
        FileManager.viewBills();
        break;

    case 3: {
        String consumerId = readText(sc, "Enter Consumer ID: ");
        FileManager.viewConsumerSummary(consumerId);
        break;
    }

    case 4: {
        String month = readText(sc, "Enter Billing Month: ");
        FileManager.viewMonthlySummary(month);
        break;
    }

    case 5:
        System.out.println(
            "Thank you for using Electricity Billing System!"
        );
        break;

    default:
        System.out.println("Invalid choice! Select 1 to 5.");
}


        } while (choice != 5);

        sc.close();
    }
}
