package Assignment2.BadSmells;

/*
 * Smell: Long Function
 *
 * Original problem:
 * processOrder() computed subtotal, discount, shipping, tax, approval status,
 * and a formatted summary all in one ~40-line block. Five distinct concerns
 * were blended into a single method with no internal structure.
 *
 * Refactorings applied:
 * - Extract Method (×4): calculateDiscount(), calculateShipping(),
 *   calculateTax(), and determineApprovalStatus() extracted.
 * - Extract Method: buildSummary() extracted for the formatting concern.
 * - processOrder() now reads as a linear pipeline of named steps.
 *
 * Why it is better:
 * Each extracted method is short, named, and testable in isolation.
 * processOrder() communicates intent at a glance. No comments needed.
 */
public class LongFunctionExample {

    public String processOrder(String customerType, int quantity,
                               double price, boolean expressDelivery) {
        double subtotal = quantity * price;
        double discount = calculateDiscount(customerType, subtotal);
        double shipping = calculateShipping(quantity, expressDelivery);
        double tax      = calculateTax(subtotal, discount);
        double total    = subtotal - discount + shipping + tax;
        String status   = determineApprovalStatus(total);
        return buildSummary(customerType, quantity, price,
                            subtotal, discount, shipping, tax, total, status);
    }

    private double calculateDiscount(String customerType, double subtotal) {
        switch (customerType) {
            case "STUDENT":       return subtotal * 0.05;
            case "VIP":           return subtotal * 0.12;
            case "EMPLOYEE":      return subtotal * 0.20;
            default:              return 0;
        }
    }

    private double calculateShipping(int quantity, boolean expressDelivery) {
        double base    = expressDelivery ? 25 : 10;
        double surcharge = quantity > 10 ? (expressDelivery ? 10 : 5) : 0;
        return base + surcharge;
    }

    private double calculateTax(double subtotal, double discount) {
        return (subtotal - discount) * 0.18;
    }

    private String determineApprovalStatus(double total) {
        if (total > 500) return "MANAGER_APPROVAL";
        if (total > 200) return "FINANCE_REVIEW";
        return "AUTO_APPROVED";
    }

    private String buildSummary(String customerType, int quantity, double price,
                                double subtotal, double discount, double shipping,
                                double tax, double total, String status) {
        return "customerType=" + customerType + '\n'
             + "quantity="     + quantity     + '\n'
             + "price="        + price        + '\n'
             + "subtotal="     + subtotal     + '\n'
             + "discount="     + discount     + '\n'
             + "shipping="     + shipping     + '\n'
             + "tax="          + tax          + '\n'
             + "total="        + total        + '\n'
             + "status="       + status;
    }

    public void clientCode() {
        System.out.println(processOrder("VIP",     12, 30, true));
        System.out.println(processOrder("STUDENT",  2, 50, false));
    }
}
