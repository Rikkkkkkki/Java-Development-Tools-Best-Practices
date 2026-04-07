package Assignment2.BadSmells;

/*
 * Smell: Data Class
 *
 * Original problem:
 * StudentRecord was a passive bag of public fields. All decision logic lived
 * in three separate evaluator/calculator/reporter classes that reached into the
 * record directly. The class had no behavior of its own.
 *
 * Refactorings applied:
 * - Encapsulate Fields: fields made private, constructor-assigned, read-only.
 * - Move Method: isEligibleForHonors(), tuitionDiscountPercent(), and
 *   academicStandingDescription() moved into StudentRecord where the data lives.
 * - The three external helper classes are removed because they added no value
 *   once the behavior moved home.
 *
 * Why it is better:
 * StudentRecord now owns both its state and the rules that depend on it.
 * Client code is simpler: one object, all decisions in one place.
 */
public class DataClassExample {

    public static class StudentRecord {
        private final String name;
        private final int credits;
        private final double gpa;

        public StudentRecord(String name, int credits, double gpa) {
            this.name    = name;
            this.credits = credits;
            this.gpa     = gpa;
        }

        public String getName()    { return name; }
        public int getCredits()    { return credits; }
        public double getGpa()     { return gpa; }

        public boolean isEligibleForHonors() {
            return credits >= 30 && gpa >= 3.7;
        }

        public double tuitionDiscountPercent() {
            if (gpa >= 3.8) return 0.15;
            if (gpa >= 3.5) return 0.10;
            return 0.0;
        }

        public String academicStandingDescription() {
            if (gpa < 2.0)      return name + " is on academic probation";
            if (credits < 15)   return name + " is a new student";
            return name + " is in good standing";
        }
    }

    public void clientCode() {
        StudentRecord student = new StudentRecord("Nino", 32, 3.8);
        System.out.println(student.isEligibleForHonors());
        System.out.println(student.tuitionDiscountPercent());
        System.out.println(student.academicStandingDescription());
    }
}
