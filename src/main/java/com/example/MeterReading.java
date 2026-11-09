
package com.example;

public class MeterReading {

    private int previousReading;
    private int currentReading;

    // Constructor
    public MeterReading(int previousReading,
                        int currentReading) {

        if (previousReading < 0) {
            throw new IllegalArgumentException(
                "Previous reading cannot be negative!"
            );
        }

        if (currentReading < previousReading) {
            throw new IllegalArgumentException(
                "Current reading cannot be less than previous reading!"
            );
        }

        this.previousReading = previousReading;
        this.currentReading = currentReading;
    }

    // Calculate units consumed
    public int calculateUnits() {

        return currentReading - previousReading;
    }

    public int getPreviousReading() {
        return previousReading;
    }

    public int getCurrentReading() {
        return currentReading;
    }
}
