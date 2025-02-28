/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RejjHelpsYouInvoiceSystem {
    private static Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/rejj_helps_you", "root", "1234");
        } catch (SQLException e) {
            System.out.println("Error");
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Rejj Helps You Invoice System ===");
            System.out.println("1. Clients");
            System.out.println("2. Services");
            System.out.println("3. Invoices");
            System.out.println("4. Quit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 4) break;

            switch (choice) {
                case 1 -> clientMenu(scanner);
                case 2 -> serviceMenu(scanner);
                case 3 -> invoiceMenu(scanner);
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void clientMenu(Scanner scanner) {
        System.out.println("\nClients:");
        System.out.println("1. Add");
        System.out.println("2. View");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        Connection conn = connect();
        if (conn == null) return;

        try {
            if (choice == 1) {
                System.out.print("Client Name: ");
                String name = scanner.nextLine();
                conn.prepareStatement("INSERT INTO clients (name) VALUES ('" + name + "')").executeUpdate();
                System.out.println("Client added.");
            } else {
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM clients");
                while (rs.next()) System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    private static void serviceMenu(Scanner scanner) {
        System.out.println("\nServices:");
        System.out.println("1. Add");
        System.out.println("2. View");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        Connection conn = connect();
        if (conn == null) return;

        try {
            if (choice == 1) {
                System.out.print("Service Name: ");
                String name = scanner.nextLine();
                System.out.print("Rate: ");
                double price = scanner.nextDouble();
                conn.prepareStatement("INSERT INTO services (name, price) VALUES ('" + name + "', " + price + ")").executeUpdate();
                System.out.println("Service added.");
            } else {
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM services");
                while (rs.next()) System.out.println(rs.getInt("id") + " - " + rs.getString("name") + " ($" + rs.getDouble("price") + ")");
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    private static void invoiceMenu(Scanner scanner) {
        System.out.println("\nInvoices:");
        System.out.println("1. Create");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        Connection conn = connect();
        if (conn == null) return;

        try {
            if (choice == 1) {
                System.out.print("Client ID: ");
                int clientId = scanner.nextInt();
                scanner.nextLine();
                conn.prepareStatement("INSERT INTO invoices (client_id) VALUES (" + clientId + ")", Statement.RETURN_GENERATED_KEYS).executeUpdate();
                System.out.println("Invoice created.");
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }
}
