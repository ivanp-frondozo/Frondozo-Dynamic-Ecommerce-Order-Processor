package org.example;

import java.util.Scanner;

public class InteractiveOrderProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Interactive Order Processor!");

        System.out.println("--- Enter Order Details ---");
        System.out.println("Enter unit price:");
        double unitPrice = scanner.nextDouble();

        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();

        System.out.println("Is customer a member (true/false)?:");
        boolean membership = scanner.nextBoolean();
        scanner.nextLine();

        System.out.println("Enter customer tier (Regular, Silver, Gold):");
        String customerTier = scanner.nextLine();

        System.out.println("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown):");
        String shippingZone = scanner.nextLine();

        System.out.println("Enter discount code (SAVE10, FREESHIP, or \"\" for none):");
        String discountCode = scanner.nextLine();

        System.out.println();
        System.out.println("--- Order Details ---");
        System.out.println("Unit Price: $" + unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + membership); //does this mean tiers not included if not member?
        System.out.println("Customer Tier: " + customerTier);
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);
        System.out.println();

        System.out.println("--- Calculation Steps ---");

        double subtotal = unitPrice * quantity;
        System.out.println("Initial Subtotal: $" + subtotal);

        if (customerTier.equals("Gold")) {
            subtotal = subtotal * 0.85;
            System.out.println("After Tier Discount (Gold - 15%): $" + subtotal);
        } else if (customerTier.equals("Silver")) {
            subtotal = subtotal * 0.9;
            System.out.println("After Tier Discount (Silver - 10%): $" + subtotal);
        } else {
            System.out.println("After Tier Discount (No discount for Regular/Unknown): $" + subtotal);
        }

        if (quantity >= 5) {
            subtotal = subtotal * 0.95;
            System.out.println("After Quantity Discount (5% for >=5 items): $" + subtotal);
        }

        boolean freeShipping = false; //just set to false
        if (discountCode.equals("SAVE10") && subtotal > 75.00) {
            subtotal = subtotal - 10.00;
            System.out.println("After Promotional Code (SAVE10 for >$75): $" + subtotal);
        } else if (discountCode.equalsIgnoreCase("FREESHIP")) {
            freeShipping = true;
            System.out.println("After Promotional Code (FREESHIP): Shipping will be free.");
        }

        double surcharge = (subtotal < 25.00) ? 3.00 : 0.00;
        subtotal = subtotal + surcharge;
        if (surcharge > 0) {
            System.out.println("After Small Order Surcharge (Order < $25): $" + subtotal);
        } else {
            System.out.println("After Small Order Surcharge (if applicable): $" + subtotal + " (No surcharge)");
        }

        double shippingCost = 0.00;
        if (!freeShipping) {
            switch (shippingZone) {
                case "ZoneA":
                    shippingCost = 5.00;
                    break;
                case "ZoneB":
                    shippingCost = 12.50;
                    break;
                case "ZoneC":
                    shippingCost = 20.00;
                    break;
                default:
                    shippingCost = 25.00;
                    break;
            }
            System.out.println("Shipping Cost: $" + shippingCost + " (" + shippingZone + ")");
        } else {
            System.out.println("Shipping Cost: $0.00 (Free Shipping Applied)");
        }

        double finalOrderTotal = subtotal + shippingCost;
        System.out.println();
        System.out.println("Final Order Total: $" + finalOrderTotal);

        interactiveStringEquality(scanner);

        scanner.close();
    }

    public static void interactiveStringEquality(Scanner scanner) {
        System.out.println();
        System.out.println("--- String Equality Demo ---");
        System.out.println("Enter first string for comparison:");
        String firstString = scanner.nextLine();

        System.out.println("Enter second string for comparison:");
        String secondString = scanner.nextLine();

        System.out.println();
        System.out.println("String 1: " + firstString);
        System.out.println("String 2: " + secondString);

        System.out.println();
        System.out.println("String 1 == String 2: " + (firstString == secondString));
        System.out.println("String 1 .equals() String 2: " + firstString.equals(secondString));
        System.out.println("String 1 .equalsIgnoreCase() String 2: " + firstString.equalsIgnoreCase(secondString));

        System.out.println("'==' compares object references which are different for user input strings.");
        System.out.println("'.equals()' compares string content and is case-sensitive making it more ideal for accurate string comparison.");
        System.out.println("'.equalsIgnoreCase()' compares content while ignoring case differences.");
    }
}
