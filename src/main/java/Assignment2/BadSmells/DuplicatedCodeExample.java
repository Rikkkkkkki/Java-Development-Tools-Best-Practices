package Assignment2.BadSmells;

/*
 * Smell: Duplicated Code
 *
 * Original problem:
 * summerInvoice() and winterInvoice() shared identical logic for tax and
 * shipping. Only the discount rule differed. If the tax rate changed, both
 * methods had to be updated, creating an easy-to-miss sync risk.
 *
 * Refactorings applied:
 * - Extract Method: tax() and shippingCost() extracted and shared.
 * - The two invoice methods now call the shared helpers and express only
 *   the part that actually differs: the discount rule.
 *
 * Why it is better:
 * Tax and shipping logic exist in one place. Changing the tax rate or
 * free-shipping threshold requires exactly one edit. The difference between
 * summer and winter is now obvious because only the discount line differs.
 */
public class DuplicatedCodeExample {

    public double summerInvoice(double subtotal) {
        double discount = subtotal > 200 ? subtotal * 0.10 : 0;
        return subtotal + tax(subtotal) + shippingCost(subtotal) - discount;
    }

    public double winterInvoice(double subtotal) {
        double discount = subtotal > 200 ? subtotal * 0.20 : 50;
        return subtotal + tax(subtotal) + shippingCost(subtotal) - discount;
    }

    private double tax(double subtotal) {
        return subtotal * 0.18;
    }

    private double shippingCost(double subtotal) {
        return subtotal > 100 ? 0 : 15;
    }

    public void clientCode() {
        System.out.println(summerInvoice(240));
        System.out.println(winterInvoice(240));
    }
}
