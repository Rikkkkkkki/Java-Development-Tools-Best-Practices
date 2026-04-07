package Assignment2.BadSmells;

/*
 * Smell: Feature Envy
 *
 * Original problem:
 * ScholarshipCalculator.qualifies() used only StudentAccount data and made a
 * decision that was entirely about account state. It had no data of its own
 * and no reason to exist as a separate class.
 *
 * Refactorings applied:
 * - Move Method: qualifiesForScholarship() moved into StudentAccount.
 * - The ScholarshipCalculator class is removed (Lazy Element as a side effect).
 *
 * Why it is better:
 * The decision lives with the data it examines. Client code no longer needs a
 * separate object to ask a question about another object. Cohesion increases.
 */
public class FeatureEnvyExample {

    static class StudentAccount {
        private final int completedCredits;
        private final double gpa;

        StudentAccount(int completedCredits, double gpa) {
            this.completedCredits = completedCredits;
            this.gpa              = gpa;
        }

        public int getCompletedCredits() { return completedCredits; }
        public double getGpa()           { return gpa; }

        public boolean qualifiesForScholarship() {
            return completedCredits >= 30 && gpa >= 3.7;
        }
    }

    public void clientCode() {
        StudentAccount account = new StudentAccount(36, 3.9);
        System.out.println(account.qualifiesForScholarship());
    }
}
