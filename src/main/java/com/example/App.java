
package com.example;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::showHome);
    }

    
private static void showHome() {

    Color navy = new Color(18, 30, 50);
    Color teal = new Color(0, 150, 136);
    Color background = new Color(245, 248, 250);
    Color darkText = new Color(30, 45, 60);

    JFrame frame = new JFrame("PowerBill Pro | Energy Management");
   frame.setSize(1250, 800);
    frame.setMinimumSize(new Dimension(950, 650));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setLayout(new BorderLayout());

    // SIDEBAR
    JPanel sidebar = new JPanel();
    sidebar.setPreferredSize(new Dimension(245, 720));
    sidebar.setBackground(navy);
    sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
    sidebar.setBorder(BorderFactory.createEmptyBorder(30, 20, 25, 20));

    JLabel logo = new JLabel("⚡ POWERBILL");
    logo.setFont(new Font("Segoe UI", Font.BOLD, 25));
    logo.setForeground(Color.WHITE);

    JLabel pro = new JLabel("PRO  |  ENERGY MANAGER");
    pro.setFont(new Font("Segoe UI", Font.PLAIN, 10));
    pro.setForeground(new Color(130, 190, 185));

    sidebar.add(logo);
    sidebar.add(Box.createVerticalStrut(8));
    sidebar.add(pro);
    sidebar.add(Box.createVerticalStrut(55));

    JLabel menu = new JLabel("WORKSPACE");
    menu.setFont(new Font("Segoe UI", Font.BOLD, 11));
    menu.setForeground(new Color(145, 165, 185));
    sidebar.add(menu);
    sidebar.add(Box.createVerticalStrut(18));
JButton navHome = navButton("Dashboard");
JButton navRegister = navButton("Register Consumer");
JButton navSearch = navButton("Search Consumer");
JButton navPayment = navButton("Payment Management");
JButton navBill = navButton("Generate Bill");
JButton navHistory = navButton("Billing History");
JButton navReports = navButton("Monthly Reports");
JButton navExit = navButton("Exit");

    sidebar.add(navHome);
    sidebar.add(Box.createVerticalStrut(10));
    sidebar.add(navRegister);
    sidebar.add(Box.createVerticalStrut(10));
sidebar.add(navSearch);
sidebar.add(Box.createVerticalStrut(10));
sidebar.add(navPayment);
sidebar.add(Box.createVerticalStrut(10));
    sidebar.add(Box.createVerticalStrut(10));
    sidebar.add(navBill);
    sidebar.add(Box.createVerticalStrut(10));
    sidebar.add(navHistory);
    sidebar.add(Box.createVerticalStrut(10));
    sidebar.add(navReports);
    sidebar.add(Box.createVerticalGlue());
    sidebar.add(navExit);

    // MAIN CONTENT
    JPanel main = new JPanel(new BorderLayout(20, 25));
    main.setBackground(background);
    main.setBorder(BorderFactory.createEmptyBorder(35, 38, 30, 38));

    JPanel heading = new JPanel();
    heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
    heading.setBackground(background);

    JLabel title = new JLabel("Dashboard Overview");
    title.setFont(new Font("Segoe UI", Font.BOLD, 30));
    title.setForeground(darkText);

    JLabel subtitle = new JLabel(
        "Welcome back! Manage your electricity billing operations."
    );
    subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    subtitle.setForeground(new Color(110, 125, 140));

    heading.add(title);
    heading.add(Box.createVerticalStrut(8));
    heading.add(subtitle);

    // HERO PANEL
    JPanel hero = new JPanel();
    hero.setLayout(new BoxLayout(hero, BoxLayout.Y_AXIS));
    hero.setBackground(new Color(24, 74, 82));
    hero.setBorder(BorderFactory.createEmptyBorder(27, 30, 27, 30));

    JLabel heroTitle = new JLabel("Smarter Energy. Simpler Billing.");
    heroTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));
    heroTitle.setForeground(Color.WHITE);

    JLabel heroText = new JLabel(
        "One place to manage consumers, meter readings and payments."
    );
    heroText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    heroText.setForeground(new Color(215, 235, 230));

    hero.add(heroTitle);
    hero.add(Box.createVerticalStrut(12));
    hero.add(heroText);

    // SECTION TITLE
    JLabel section = new JLabel("MANAGE YOUR OPERATIONS");
    section.setFont(new Font("Segoe UI", Font.BOLD, 13));
    section.setForeground(new Color(90, 110, 125));

    // ACTION CARDS
    JPanel cards = new JPanel(new GridLayout(2, 2, 18, 18));
    cards.setBackground(background);

    cards.add(actionCard(
        "01  |  Consumers",
        "Register and manage electricity consumers.",
        "REGISTER CONSUMER",
        teal,
        () -> registerConsumer(frame)
    ));

    cards.add(actionCard(
        "02  |  Billing",
        "Calculate charges from meter readings.",
        "GENERATE BILL",
        new Color(45, 105, 190),
        () -> generateBill(frame)
    ));

    cards.add(actionCard(
        "03  |  Records",
        "Review previously generated electricity bills.",
        "VIEW HISTORY",
        new Color(115, 90, 170),
        () -> viewBillingHistory(frame)
    ));

    cards.add(actionCard(
        "04  |  Analytics",
        "Explore monthly consumption and revenue.",
        "MONTHLY REPORTS",
        new Color(190, 130, 45),
        () -> showMonthlyReport(frame)
    ));

    JPanel center = new JPanel();
    center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
    center.setBackground(background);
    center.setAlignmentX(Component.LEFT_ALIGNMENT);
hero.setAlignmentX(Component.LEFT_ALIGNMENT);
section.setAlignmentX(Component.LEFT_ALIGNMENT);
cards.setAlignmentX(Component.LEFT_ALIGNMENT);

center.add(hero);
center.add(Box.createVerticalStrut(20));
center.add(createStatsPanel());
center.add(Box.createVerticalStrut(20));
center.add(section);
center.add(Box.createVerticalStrut(12));
center.add(cards);

    main.add(heading, BorderLayout.NORTH);
    JScrollPane scrollPane = new JScrollPane(center);

scrollPane.setBorder(null);
scrollPane.getVerticalScrollBar().setUnitIncrement(16);
scrollPane.getViewport().setBackground(background);

main.add(scrollPane, BorderLayout.CENTER);

    // NAVIGATION ACTIONS
    navHome.addActionListener(e ->
        JOptionPane.showMessageDialog(frame, "Dashboard is already open.")
    );

    navRegister.addActionListener(e -> registerConsumer(frame));
    navSearch.addActionListener(e -> searchConsumer(frame));
navPayment.addActionListener(e -> managePayments(frame));
    navBill.addActionListener(e -> generateBill(frame));
    navHistory.addActionListener(e -> viewBillingHistory(frame));
    navReports.addActionListener(e -> showMonthlyReport(frame));
    navExit.addActionListener(e -> frame.dispose());

    frame.add(sidebar, BorderLayout.WEST);
    frame.add(main, BorderLayout.CENTER);
    frame.setVisible(true);
}

// SIDEBAR BUTTON
private static JButton navButton(String text) {

    JButton button = new JButton(text);
    button.setFont(new Font("Segoe UI", Font.BOLD, 13));
    button.setForeground(new Color(225, 235, 245));
    button.setBackground(new Color(30, 47, 68));
    button.setHorizontalAlignment(SwingConstants.LEFT);
    button.setMaximumSize(new Dimension(210, 44));
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    return button;
}

// DASHBOARD ACTION CARD
private static JPanel actionCard(
    String title,
    String description,
    String buttonText,
    Color accent,
    Runnable action
) {

    JPanel card = new JPanel();
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
    card.setBackground(Color.WHITE);
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(225, 232, 238)),
        BorderFactory.createEmptyBorder(20, 22, 20, 22)
    ));

    JLabel titleLabel = new JLabel(title);
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 19));
    titleLabel.setForeground(new Color(30, 45, 60));

    JLabel descriptionLabel = new JLabel(description);
    descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    descriptionLabel.setForeground(new Color(115, 130, 145));

    JButton button = new JButton(buttonText);
    button.setFont(new Font("Segoe UI", Font.BOLD, 11));
    button.setForeground(Color.WHITE);
    button.setBackground(accent);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    button.setAlignmentX(Component.LEFT_ALIGNMENT);
    button.setMaximumSize(new Dimension(190, 38));

    button.addActionListener(e -> action.run());

    card.add(titleLabel);
    card.add(Box.createVerticalStrut(12));
    card.add(descriptionLabel);
    card.add(Box.createVerticalGlue());
    card.add(button);

    return card;
}


    
    // Generate electricity bill
    private static void generateBill(JFrame parent) {

        JTextField idField = new JTextField();
        JTextField monthField = new JTextField("November 2026");
        JTextField previousField = new JTextField();
        JTextField currentField = new JTextField();

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));

        form.add(new JLabel("Consumer ID:"));
        form.add(idField);

        form.add(new JLabel("Billing Month:"));
        form.add(monthField);

        form.add(new JLabel("Previous Reading:"));
        form.add(previousField);

        form.add(new JLabel("Current Reading:"));
        form.add(currentField);

        int result = JOptionPane.showConfirmDialog(
            parent,
            form,
            "Generate Electricity Bill",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            String consumerId = idField.getText().trim();
            String month = monthField.getText().trim();

            int previous = Integer.parseInt(previousField.getText().trim());
            int current = Integer.parseInt(currentField.getText().trim());

            if (consumerId.isEmpty() || month.isEmpty()) {
                throw new IllegalArgumentException("Fill in all fields.");
            }

            if (previous < 0 || current < previous) {
                throw new IllegalArgumentException(
                    "Meter readings are invalid."
                );
            }

            Consumer consumer = FileManager.findConsumer(consumerId);

            if (consumer == null) {
                JOptionPane.showMessageDialog(
                    parent,
                    "Consumer not found! Register the consumer first."
                );
                return;
            }

            MeterReading reading = new MeterReading(previous, current);

            String billId = "BILL-" + System.currentTimeMillis();

            Bill bill = new Bill(billId, month, consumer, reading);

            String billText =
                "BILL ID: " + bill.getBillId() +
                "\nConsumer: " + consumer.getName() +
                "\nConsumer ID: " + consumer.getConsumerId() +
                "\nMonth: " + month +
                "\nUnits Consumed: " + reading.calculateUnits() +
                "\nTotal Bill: Rs. " + String.format("%.2f", bill.getTotalBill());

            FileManager.saveBill(bill.getBillData());

            showBillReceipt(parent, bill, reading, month);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                parent,
                "Please enter valid numbers for meter readings."
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                parent,
                ex.getMessage()
            );
        }
    }
    
    // Display billing history
  
private static void showMonthlyReport(JFrame parent) {

    String month = JOptionPane.showInputDialog(
        parent,
        "Enter Billing Month (Example: November 2026):",
        "Monthly Analytics Report",
        JOptionPane.QUESTION_MESSAGE
    );

    if (month == null || month.trim().isEmpty()) {
        return;
    }

    double totalRevenue = 0;
    double collectedAmount = 0;
    double pendingAmount = 0;

    int totalUnits = 0;
    int totalBills = 0;
    int paidBills = 0;
    int pendingBills = 0;

    java.util.Set<String> consumers = new java.util.HashSet<>();
    java.util.Set<String> paidBillIds = new java.util.HashSet<>();

    try {

        // Read payment records
        java.nio.file.Path paymentPath =
            java.nio.file.Path.of("payments.txt");

        if (java.nio.file.Files.exists(paymentPath)) {

            for (String payment :
                    java.nio.file.Files.readAllLines(paymentPath)) {

                String[] paymentData = payment.split("\\|");

                if (paymentData.length >= 2 &&
                    paymentData[1].trim().equalsIgnoreCase("PAID")) {

                    paidBillIds.add(paymentData[0].trim());
                }
            }
        }

        // Read billing records
        try (java.io.BufferedReader reader =
                 new java.io.BufferedReader(
                     new java.io.FileReader("bills.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length < 6) {
                    continue;
                }

                if (data[1].trim().equalsIgnoreCase(month.trim())) {

                    String billId = data[0].trim();

                    int units = Integer.parseInt(data[4].trim());
                    double amount = Double.parseDouble(data[5].trim());

                    consumers.add(data[2].trim());

                    totalUnits += units;
                    totalRevenue += amount;
                    totalBills++;

                    if (paidBillIds.contains(billId)) {
                        paidBills++;
                        collectedAmount += amount;
                    } else {
                        pendingBills++;
                        pendingAmount += amount;
                    }
                }
            }
        }

    } catch (java.io.IOException | NumberFormatException e) {

        JOptionPane.showMessageDialog(
            parent,
            "Error generating report: " + e.getMessage(),
            "Report Error",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    if (totalBills == 0) {
        JOptionPane.showMessageDialog(
            parent,
            "No billing records found for " + month
        );
        return;
    }

    String report =
        "================================================\n" +
        "              POWERBILL PRO\n" +
        "           MONTHLY ANALYTICS REPORT\n" +
        "================================================\n\n" +

        "Billing Month       : " + month + "\n\n" +

        "--------------- BILLING SUMMARY ----------------\n" +
        "Total Consumers     : " + consumers.size() + "\n" +
        "Total Bills         : " + totalBills + "\n" +
        "Total Units Used    : " + totalUnits + "\n\n" +

        "--------------- PAYMENT SUMMARY ----------------\n" +
        "Paid Bills          : " + paidBills + "\n" +
        "Pending Bills       : " + pendingBills + "\n\n" +

        "---------------- REVENUE SUMMARY ----------------\n" +
        String.format("Total Revenue       : Rs. %.2f%n", totalRevenue) +
        String.format("Amount Collected    : Rs. %.2f%n", collectedAmount) +
        String.format("Outstanding Amount  : Rs. %.2f%n", pendingAmount) +

        "\n================================================\n" +
        "           POWERBILL PRO REPORT\n" +
        "================================================";

    javax.swing.JTextArea reportArea =
        new javax.swing.JTextArea(report, 22, 55);

    reportArea.setEditable(false);
    reportArea.setFont(
        new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 14)
    );

  JButton exportButton = new JButton("Export Report");
JButton closeButton = new JButton("Close");

JPanel buttons = new JPanel();
buttons.add(exportButton);
buttons.add(closeButton);

JDialog reportDialog = new JDialog(
    parent,
    "PowerBill Pro - Monthly Analytics",
    true
);

exportButton.addActionListener(e -> {

    try {
        java.nio.file.Path reportFile =
            java.nio.file.Path.of("MonthlyReport_" +
                month.replaceAll("[^a-zA-Z0-9]", "_") + ".txt");

        java.nio.file.Files.writeString(
            reportFile,
            reportArea.getText()
        );

        JOptionPane.showMessageDialog(
            reportDialog,
            "Report exported successfully!\n" +
            reportFile.toAbsolutePath()
        );

    } catch (java.io.IOException ex) {
        JOptionPane.showMessageDialog(
            reportDialog,
            "Error exporting report: " + ex.getMessage()
        );
    }
});

closeButton.addActionListener(e -> reportDialog.dispose());

reportDialog.setLayout(new java.awt.BorderLayout());
reportDialog.add(new JScrollPane(reportArea), BorderLayout.CENTER);
reportDialog.add(buttons, BorderLayout.SOUTH);
reportDialog.setSize(650, 550);
reportDialog.setLocationRelativeTo(parent);
reportDialog.setVisible(true);
}

    // Consumer registration form
    private static void registerConsumer(JFrame parent) {

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField meterField = new JTextField();

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));

        form.add(new JLabel("Consumer ID:"));
        form.add(idField);

        form.add(new JLabel("Consumer Name:"));
        form.add(nameField);

        form.add(new JLabel("Address:"));
        form.add(addressField);

        form.add(new JLabel("Meter Number:"));
        form.add(meterField);

        int result = JOptionPane.showConfirmDialog(
            parent,
            form,
            "Register New Consumer",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();
        String meter = meterField.getText().trim();

        if (id.isEmpty() || name.isEmpty() ||
            address.isEmpty() || meter.isEmpty()) {

            JOptionPane.showMessageDialog(
                parent,
                "Please fill in all fields!"
            );
            return;
        }

        if (FileManager.consumerExists(id)) {
            JOptionPane.showMessageDialog(
                parent,
                "This Consumer ID already exists!"
            );
            return;
        }

        Consumer consumer = new Consumer(id, name, address, meter);
        FileManager.saveConsumer(consumer);

        JOptionPane.showMessageDialog(
            parent,
            "Consumer registered successfully!\nConsumer ID: " + id
        );
    }

private static JPanel createStatsPanel() {

    int totalConsumers = 0;
    int totalBills = 0;
    int totalUnits = 0;
    double totalRevenue = 0;

    try {
        List<String> consumers =
            Files.readAllLines(Path.of("consumers.txt"));

        totalConsumers = (int) consumers.stream()
            .filter(line -> !line.trim().isEmpty())
            .count();

    } catch (IOException e) {
        // No consumer records yet
    }

    try {
        List<String> bills =
            Files.readAllLines(Path.of("bills.txt"));

        for (String line : bills) {

            String[] data = line.split("\\|");

            if (data.length >= 6) {
                try {
                    totalUnits += Integer.parseInt(data[4].trim());
                    totalRevenue += Double.parseDouble(data[5].trim());
                    totalBills++;
                } catch (NumberFormatException e) {
                    // Skip invalid records
                }
            }
        }

    } catch (IOException e) {
        // No bills yet
    }

    JPanel panel = new JPanel(new GridLayout(1, 4, 12, 12));
    panel.setBackground(new Color(245, 248, 250));

    panel.add(createStatCard(
        "CONSUMERS",
        String.valueOf(totalConsumers),
        new Color(40, 105, 185)
    ));

    panel.add(createStatCard(
        "TOTAL BILLS",
        String.valueOf(totalBills),
        new Color(0, 145, 125)
    ));

    panel.add(createStatCard(
        "UNITS USED",
        String.valueOf(totalUnits),
        new Color(120, 85, 175)
    ));

    panel.add(createStatCard(
        "REVENUE",
        "Rs. " + String.format("%.2f", totalRevenue),
        new Color(185, 125, 40)
    ));

    return panel;
}

private static JPanel createStatCard(
    String heading, String value, Color accent
) {

    JPanel card = new JPanel();
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
    card.setBackground(Color.WHITE);

    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(225, 232, 238)),
        BorderFactory.createEmptyBorder(14, 12, 14, 12)
    ));

    JLabel title = new JLabel(heading);
    title.setFont(new Font("Segoe UI", Font.BOLD, 10));
    title.setForeground(new Color(110, 125, 140));

    JLabel number = new JLabel(value);
    number.setFont(new Font("Segoe UI", Font.BOLD, 20));
    number.setForeground(accent);

    card.add(title);
    card.add(Box.createVerticalStrut(10));
    card.add(number);

    return card;
}

private static void showBillReceipt(
    JFrame parent,
    Bill bill,
    MeterReading reading,
    String month
) {

    int units = reading.calculateUnits();

    double energyCharge =
        TariffCalculator.calculateEnergyCharge(units);

    double totalBill = bill.getTotalBill();
    double fixedCharge = totalBill - energyCharge;

    JTextArea receipt = new JTextArea();

    receipt.setEditable(false);
    receipt.setFont(new Font("Monospaced", Font.PLAIN, 14));
    receipt.setMargin(new Insets(20, 20, 20, 20));

    String receiptText =
        "          POWERBILL PRO\n" +
        "      ELECTRICITY BILL RECEIPT\n" +
        "========================================\n\n" +
        "Bill ID       : " + bill.getBillId() + "\n" +
        "Billing Month : " + month + "\n\n" +
        "CONSUMER DETAILS\n" +
        "----------------------------------------\n" +
        "Consumer ID   : " + bill.getConsumerId() + "\n" +
        "Consumer Name : " + bill.getConsumerName() + "\n\n" +
        "METER READINGS\n" +
        "----------------------------------------\n" +
        "Previous      : " + reading.getPreviousReading() + "\n" +
        "Current       : " + reading.getCurrentReading() + "\n" +
        "Units Used    : " + units + "\n\n" +
        "CHARGE DETAILS\n" +
        "----------------------------------------\n" +
        "Energy Charge : Rs. " +
        String.format("%.2f", energyCharge) + "\n" +
        "Fixed Charge  : Rs. " +
        String.format("%.2f", fixedCharge) + "\n" +
        "----------------------------------------\n" +
        "TOTAL AMOUNT  : Rs. " +
        String.format("%.2f", totalBill) + "\n" +
        "========================================\n\n" +
        "       Thank you for using PowerBill Pro!";

    receipt.setText(receiptText);

    JDialog dialog = new JDialog(
        parent,
        "Electricity Bill Receipt",
        true
    );

    dialog.setLayout(new BorderLayout(10, 10));

    dialog.add(
        new JScrollPane(receipt),
        BorderLayout.CENTER
    );

    JPanel buttons = new JPanel(new FlowLayout());

    JButton printButton = new JButton("Print Bill");
    JButton closeButton = new JButton("Close");

    printButton.addActionListener(e -> {
        try {
            receipt.print();
        } catch (java.awt.print.PrinterException ex) {
            JOptionPane.showMessageDialog(
                dialog,
                "Unable to print: " + ex.getMessage()
            );
        }
    });

    closeButton.addActionListener(e -> dialog.dispose());

    buttons.add(printButton);
    buttons.add(closeButton);

    dialog.add(buttons, BorderLayout.SOUTH);

    dialog.setSize(520, 650);
    dialog.setLocationRelativeTo(parent);
    dialog.setVisible(true);
}

private static void searchConsumer(JFrame parent) {

    String consumerId = JOptionPane.showInputDialog(
        parent,
        "Enter Consumer ID:",
        "Search Consumer",
        JOptionPane.QUESTION_MESSAGE
    );

    if (consumerId == null || consumerId.trim().isEmpty()) {
        return;
    }

    Consumer consumer =
        FileManager.findConsumer(consumerId.trim());

    if (consumer == null) {
        JOptionPane.showMessageDialog(
            parent,
            "Consumer not found!",
            "Search Result",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    String details =
        "========== CONSUMER DETAILS ==========\n\n" +
        "Consumer ID : " + consumer.getConsumerId() + "\n" +
        "Name        : " + consumer.getName() + "\n" +
        "Address     : " + consumer.getAddress() + "\n" +
        "Meter Number: " + consumer.getMeterNumber() + "\n\n" +
        "Consumer record found successfully!";

    JOptionPane.showMessageDialog(
        parent,
        details,
        "Consumer Search Result",
        JOptionPane.INFORMATION_MESSAGE
    );
}

private static void managePayments(JFrame parent) {

    java.util.Set<String> paidBills = new java.util.HashSet<>();
    java.util.List<String> billOptions = new java.util.ArrayList<>();

    try {
        // Read previously recorded payments
        Path paymentPath = Path.of("payments.txt");

        if (Files.exists(paymentPath)) {
            for (String payment : Files.readAllLines(paymentPath)) {
                String[] data = payment.split("\\|");

                if (data.length >= 2 &&
                    data[1].trim().equalsIgnoreCase("PAID")) {

                    paidBills.add(data[0].trim());
                }
            }
        }

        // Read all generated bills
        Path billPath = Path.of("bills.txt");

        if (!Files.exists(billPath)) {
            JOptionPane.showMessageDialog(
                parent, "No billing records found."
            );
            return;
        }

        for (String line : Files.readAllLines(billPath)) {

            String[] data = line.split("\\|");

            if (data.length < 6) {
                continue;
            }

            String billId = data[0].trim();

            String status = paidBills.contains(billId)
                ? "PAID"
                : "PENDING";

            String option =
                billId + " | " +
                data[1].trim() + " | " +
                data[3].trim() + " | Rs. " +
                data[5].trim() + " | " + status;

            billOptions.add(option);
        }

        if (billOptions.isEmpty()) {
            JOptionPane.showMessageDialog(
                parent, "No valid bills found."
            );
            return;
        }

        Object selected = JOptionPane.showInputDialog(
            parent,
            "Select a bill to view its payment status:",
            "Payment Management",
            JOptionPane.QUESTION_MESSAGE,
            null,
            billOptions.toArray(),
            billOptions.get(0)
        );

        if (selected == null) {
            return;
        }

        String selectedBill = selected.toString();
        String billId = selectedBill.split("\\|")[0].trim();

        if (paidBills.contains(billId)) {
            JOptionPane.showMessageDialog(
                parent,
                "This bill is already PAID.\n\n" + selectedBill,
                "Payment Status",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            parent,
            "Confirm payment for this bill?\n\n" + selectedBill,
            "Confirm Payment",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String paymentRecord =
            billId + " | PAID | " +
            java.time.LocalDate.now() +
            System.lineSeparator();

        Files.writeString(
            paymentPath,
            paymentRecord,
            java.nio.file.StandardOpenOption.CREATE,
            java.nio.file.StandardOpenOption.APPEND
        );

        JOptionPane.showMessageDialog(
            parent,
            "Payment recorded successfully!\n\n" +
            "Bill ID: " + billId +
            "\nStatus: PAID",
            "Payment Successful",
            JOptionPane.INFORMATION_MESSAGE
        );

    } catch (IOException e) {

        JOptionPane.showMessageDialog(
            parent,
            "Error managing payments: " + e.getMessage(),
            "Payment Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}

private static void viewBillingHistory(JFrame parent) {

    JTextArea historyArea = new JTextArea(15, 55);
    historyArea.setEditable(false);
    historyArea.setFont(
        new Font("Monospaced", Font.PLAIN, 14)
    );

    try {

        List<String> bills = Files.readAllLines(
            Path.of("bills.txt")
        );

        if (bills.isEmpty()) {
            historyArea.setText("No billing records found.");
        } else {

            StringBuilder history = new StringBuilder();

            history.append(
                "========== BILLING HISTORY ==========\n\n"
            );

            for (String bill : bills) {
                history.append(bill).append("\n\n");
            }

            historyArea.setText(history.toString());
        }

    } catch (IOException e) {

        historyArea.setText("No billing records found yet.");
    }

    JOptionPane.showMessageDialog(
        parent,
        new JScrollPane(historyArea),
        "Billing History",
        JOptionPane.INFORMATION_MESSAGE
    );
}

}
