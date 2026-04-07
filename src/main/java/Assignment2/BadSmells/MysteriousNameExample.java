package Assignment2.BadSmells;

/*
 * Smell: Mysterious Name
 *
 * Original problem:
 * f(a, b, c) with locals x and y told the reader nothing. Understanding the
 * computation required reverse-engineering arithmetic instead of reading intent.
 * The method calculated the net profit margin given units sold, unit price, and
 * total costs — but nothing in the code said so.
 *
 * Refactorings applied:
 * - Rename Method: f → calculateNetProfitMargin.
 * - Rename Parameters: a → unitsSold, b → unitPrice, c → totalCosts.
 * - Rename Locals: x → grossRevenue, y → netProfit.
 * - Extract Method: computeGrossRevenue() extracted so each step has a name.
 *
 * Why it is better:
 * The computation is now self-documenting. A reader can verify the formula
 * from the names alone without tracing the arithmetic.
 */
public class MysteriousNameExample {

    public int calculateNetProfitMargin(int unitsSold, int unitPrice, int totalCosts) {
        int grossRevenue = computeGrossRevenue(unitsSold, unitPrice);
        int netProfit    = grossRevenue - totalCosts;
        return netProfit / 2;
    }

    private int computeGrossRevenue(int unitsSold, int unitPrice) {
        return unitsSold * unitPrice;
    }

    public void clientCode() {
        int result = calculateNetProfitMargin(8, 4, 6);
        System.out.println(result);
    }
}
