
package com.example;

public class TariffCalculator {

    // Sample tariff rates
    private static final double RATE_1 = 1.50;
    private static final double RATE_2 = 2.50;
    private static final double RATE_3 = 4.00;

    private static final double FIXED_CHARGE = 50.00;

    // Calculate electricity charges
    public static double calculateEnergyCharge(int units) {

        if (units < 0) {
            throw new IllegalArgumentException(
                "Units cannot be negative!"
            );
        }

        double amount = 0;

        if (units <= 100) {

            amount = units * RATE_1;

        } else if (units <= 200) {

            amount = (100 * RATE_1)
                   + ((units - 100) * RATE_2);

        } else {

            amount = (100 * RATE_1)
                   + (100 * RATE_2)
                   + ((units - 200) * RATE_3);
        }

        return amount;
    }

    // Calculate final bill
    public static double calculateTotalBill(int units) {

        double energyCharge = calculateEnergyCharge(units);

        return energyCharge + FIXED_CHARGE;
    }
}
