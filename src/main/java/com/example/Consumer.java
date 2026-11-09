
package com.example;

public class Consumer {

    // Consumer information
    private String consumerId;
    private String name;
    private String address;
    private String meterNumber;

    // Constructor
    public Consumer(String consumerId, String name,
                    String address, String meterNumber) {

        this.consumerId = consumerId;
        this.name = name;
        this.address = address;
        this.meterNumber = meterNumber;
    }

    // Getter methods
    public String getConsumerId() {
        return consumerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    // Display consumer information
    public void displayConsumer() {

        System.out.println("Consumer ID: " + consumerId);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Meter Number: " + meterNumber);
    }
}
