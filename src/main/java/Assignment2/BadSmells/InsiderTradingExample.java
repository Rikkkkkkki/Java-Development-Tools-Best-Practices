package Assignment2.BadSmells;

/*
 * Smell: Insider Trading
 *
 * Original problem:
 * AuditService directly accessed BankAccount.balance and wrote to
 * BankAccount.secretFlag. Both classes knew each other's internals, creating
 * tight coupling that makes either class hard to change independently.
 *
 * Refactorings applied:
 * - Encapsulate Field: balance and status are now private with a controlled
 *   mutator method freeze() and a getter isFrozen().
 * - Move Method: the freeze decision (balance < 0 => freeze) moved into
 *   BankAccount as freezeIfOverdrawn(), since it is a rule about account state.
 * - AuditService now calls an intention-revealing method instead of
 *   manipulating raw fields.
 *
 * Why it is better:
 * BankAccount owns the rule about when it should be frozen. AuditService
 * only triggers the check; it does not need to know what "frozen" means
 * internally or how balance is stored. Coupling is reduced.
 */
public class InsiderTradingExample {

    static class BankAccount {
        private double  balance;
        private boolean frozen;

        BankAccount(double balance) {
            this.balance = balance;
        }

        public boolean isFrozen() { return frozen; }

        public void freezeIfOverdrawn() {
            if (balance < 0) {
                frozen = true;
            }
        }
    }

    static class AuditService {
        public void auditAccount(BankAccount account) {
            account.freezeIfOverdrawn();
        }
    }

    public void clientCode() {
        BankAccount  account      = new BankAccount(-50);
        AuditService auditService = new AuditService();
        auditService.auditAccount(account);
        System.out.println(account.isFrozen() ? "FROZEN" : "ACTIVE");
    }
}
