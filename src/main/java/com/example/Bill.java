
package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Bill {

    private String billId;
    private String billingMonth;

    private Consumer consumer;
    private MeterReading reading;

    private int units;

    private double energyCharge;
    private double fixedCharge;
    private double totalBill;

    // Constructor
    public Bill(String billId, String billingMonth,
                Consumer consumer, MeterReading reading) {

        this.billId = billId;
        this.billingMonth = billingMonth;
        this.consumer = consumer;
        this.reading = reading;

        // Calculate units
        this.units = reading.calculateUnits();

        // Calculate charges
        this.energyCharge =
            TariffCalculator.calculateEnergyCharge(units);

        this.totalBill =
            TariffCalculator.calculateTotalBill(units);

        this.fixedCharge = totalBill - energyCharge;
    }

    // Display complete bill
    public void displayBill() {

        System.out.println("\n==================================");
        System.out.println("          ELECTRICITY BILL");
        System.out.println("==================================");

        System.out.println("Bill ID: " + billId);
        System.out.println("Billing Month: " + billingMonth);

        System.out.println("\nCONSUMER DETAILS");
        System.out.println("----------------------------------");

        consumer.displayConsumer();

        System.out.println("\nMETER READING DETAILS");
        System.out.println("----------------------------------");

        System.out.println(
            "Previous Reading: "
            + reading.getPreviousReading()
        );

        System.out.println(
            "Current Reading: "
            + reading.getCurrentReading()
        );

        System.out.println("Units Consumed: " + units);

        System.out.println("\nCHARGE DETAILS");
        System.out.println("----------------------------------");

        System.out.printf(
            "Energy Charges: Rs. %.2f%n",
            energyCharge
        );

        System.out.printf(
            "Fixed Charges: Rs. %.2f%n",
            fixedCharge
        );

        System.out.println("----------------------------------");

        System.out.printf(
            "TOTAL BILL: Rs. %.2f%n",
            totalBill
        );

        System.out.println("==================================");
        System.out.println("    BILL GENERATED SUCCESSFULLY");
        System.out.println("==================================");
    }

    public String getBillId() {
        return billId;
    }

    public double getTotalBill() {
        return totalBill;
    }
    public String getConsumerId() {
    return consumer.getConsumerId();
}

public String getConsumerName() {
    return consumer.getName();
}
    
public String getBillData() {

    return billId + " | "
            + billingMonth + " | "
            + consumer.getConsumerId() + " | "
            + consumer.getName() + " | "
            + units + " | "
            + String.format("%.2f", totalBill);
}

    // Find a registered consumer using Consumer ID
    public static Consumer findConsumer(String consumerId) {

        try (BufferedReader reader =
                 new BufferedReader(new FileReader("consumers.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length >= 4 &&
                    data[0].trim().equalsIgnoreCase(consumerId.trim())) {

                    return new Consumer(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim()
                    );
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading consumers: " + e.getMessage());
        }

        return null;
    }


}
