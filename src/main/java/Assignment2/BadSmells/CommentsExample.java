package Assignment2.BadSmells;

/*
 * Smell: Comments
 *
 * Original problem:
 * The method finalPrice() used three inline comments to explain each calculation
 * step. The comments were compensating for unnamed, opaque expressions —
 * the code could not speak for itself without them.
 *
 * Refactorings applied:
 * - Extract Method: split discount, bulk discount, and tax into named helper methods.
 * - Rename: each method name now states what it computes.
 *
 * Why it is better:
 * All three comments are now gone. Reading finalPrice() reads like a sentence:
 * apply VIP discount, apply bulk discount, then add tax. No guessing required.
 */
public class CommentsExample {

    public double finalPrice(double basePrice, boolean vip, int quantity) {
        double price = applyVipDiscount(basePrice, vip);
        price = applyBulkDiscount(price, quantity);
        price = addTax(price);
        return price;
    }

    private double applyVipDiscount(double price, boolean vip) {
        return vip ? price * 0.90 : price;
    }

    private double applyBulkDiscount(double price, int quantity) {
        return quantity > 20 ? price * 0.95 : price;
    }

    private double addTax(double price) {
        return price * 1.18;
    }

    public void clientCode() {
        double vipOrder     = finalPrice(120, true,  25);
        double regularOrder = finalPrice(120, false,  5);
        System.out.println(vipOrder);
        System.out.println(regularOrder);
    }
}


