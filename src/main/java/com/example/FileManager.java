
package com.example;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    private static final String FILE_NAME = "bills.txt";

    // Save bill information
    public static void saveBill(String billData) {

        try (FileWriter writer =
                 new FileWriter(FILE_NAME, true)) {

            writer.write(billData);
            writer.write(System.lineSeparator());

            System.out.println("Bill saved successfully!");

        } catch (IOException e) {

            System.out.println("Error saving bill: " + e.getMessage());
        }
    }

    // Display previous billing records
    public static void viewBills() {

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            System.out.println("\n===== BILLING HISTORY =====");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {

            System.out.println("No billing records found yet.");
        }
    }
    
    // Consumer-wise billing summary
    public static void viewConsumerSummary(String consumerId) {

        double totalAmount = 0;
        int totalUnits = 0;
        int billCount = 0;

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            System.out.println("\n===== CONSUMER SUMMARY =====");

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length < 6) {
                    continue;
                }

                String id = data[2].trim();

                if (id.equalsIgnoreCase(consumerId)) {

                    String name = data[3].trim();
                    int units = Integer.parseInt(data[4].trim());
                    double amount = Double.parseDouble(data[5].trim());

                    System.out.println("Consumer: " + name);
                    System.out.println("Month: " + data[1].trim());
                    System.out.println("Units: " + units);
                    System.out.printf("Bill: Rs. %.2f%n", amount);

                    totalUnits += units;
                    totalAmount += amount;
                    billCount++;
                }
            }

            if (billCount == 0) {
                System.out.println("No bills found for this consumer.");
            } else {
                System.out.println("----------------------------");
                System.out.println("Total Bills: " + billCount);
                System.out.println("Total Units: " + totalUnits);
                System.out.printf("Total Amount: Rs. %.2f%n", totalAmount);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading consumer records: " + e.getMessage());
        }
    }

    // Monthly billing summary
    public static void viewMonthlySummary(String month) {

        double totalAmount = 0;
        int totalUnits = 0;
        int billCount = 0;

        java.util.Set<String> consumers = new java.util.HashSet<>();

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            System.out.println("\n===== MONTHLY BILLING SUMMARY =====");

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length < 6) {
                    continue;
                }

                if (data[1].trim().equalsIgnoreCase(month.trim())) {

                    String id = data[2].trim();
                    int units = Integer.parseInt(data[4].trim());
                    double amount = Double.parseDouble(data[5].trim());

                    consumers.add(id);
                    totalUnits += units;
                    totalAmount += amount;
                    billCount++;
                }
            }

            if (billCount == 0) {
                System.out.println("No bills found for this month.");
            } else {
                System.out.println("Billing Month: " + month);
                System.out.println("Total Consumers: " + consumers.size());
                System.out.println("Total Bills: " + billCount);
                System.out.println("Total Units: " + totalUnits);
                System.out.printf("Total Revenue: Rs. %.2f%n", totalAmount);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading monthly records: " + e.getMessage());
        }
    }

    // Check whether a consumer ID already exists
    public static boolean consumerExists(String consumerId) {

        try (BufferedReader reader =
                 new BufferedReader(new FileReader("consumers.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                if (data.length >= 1 &&
                    data[0].trim().equalsIgnoreCase(consumerId.trim())) {
                    return true;
                }
            }

        } catch (IOException e) {
            // File may not exist yet
        }

        return false;
    }

    // Save consumer information
    public static void saveConsumer(Consumer consumer) {

        try (FileWriter writer = new FileWriter("consumers.txt", true)) {

            writer.write(
                consumer.getConsumerId() + " | " +
                consumer.getName() + " | " +
                consumer.getAddress() + " | " +
                consumer.getMeterNumber()
            );

            writer.write(System.lineSeparator());

        } catch (IOException e) {
            System.out.println("Error saving consumer: " + e.getMessage());
        }
    }
    
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
