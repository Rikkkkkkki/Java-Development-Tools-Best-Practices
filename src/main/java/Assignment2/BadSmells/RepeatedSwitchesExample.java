package Assignment2.BadSmells;

/*
 * Smell: Repeated Switches
 *
 * Original problem:
 * The same three student-type cases (STUDENT, ATHLETE, EMPLOYEE_CHILD) appeared
 * in two separate switch statements. Adding a fourth type required coordinated
 * edits in tuitionDiscount() AND dormPriority() — a DRY violation waiting to
 * drift out of sync.
 *
 * Refactorings applied:
 * - Replace Conditional with Polymorphism: StudentType becomes an enum whose
 *   constants each implement tuitionDiscount() and dormPriority() directly.
 *   Adding a new type means adding one enum constant in one place.
 * - The two switch methods are replaced by single-line delegation to the enum.
 *
 * Why it is better:
 * The Open/Closed Principle is satisfied: extending to a new student type
 * requires no modification of existing logic, only an addition. There is
 * exactly one place that describes each type's behaviour.
 */
public class RepeatedSwitchesExample {

    enum StudentType {
        STUDENT {
            @Override public double tuitionDiscount() { return 0.05; }
            @Override public String dormPriority()    { return "NORMAL"; }
        },
        ATHLETE {
            @Override public double tuitionDiscount() { return 0.15; }
            @Override public String dormPriority()    { return "HIGH"; }
        },
        EMPLOYEE_CHILD {
            @Override public double tuitionDiscount() { return 0.25; }
            @Override public String dormPriority()    { return "LOW"; }
        },
        UNKNOWN {
            @Override public double tuitionDiscount() { return 0.0; }
            @Override public String dormPriority()    { return "UNKNOWN"; }
        };

        public abstract double tuitionDiscount();
        public abstract String dormPriority();

        public static StudentType fromString(String value) {
            for (StudentType type : values()) {
                if (type.name().equals(value)) return type;
            }
            return UNKNOWN;
        }
    }

    public double tuitionDiscount(String studentType) {
        return StudentType.fromString(studentType).tuitionDiscount();
    }

    public String dormPriority(String studentType) {
        return StudentType.fromString(studentType).dormPriority();
    }

    public void clientCode() {
        System.out.println(tuitionDiscount("ATHLETE"));
        System.out.println(dormPriority("ATHLETE"));
    }
}
