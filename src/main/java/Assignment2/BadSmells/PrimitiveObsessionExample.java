package Assignment2.BadSmells;

/*
 * Smell: Primitive Obsession
 *
 * Original problem:
 * Age, enrollment status, unpaid balance, and country code were all passed as
 * raw primitives. Validation was scattered inside the consuming method as ad-hoc
 * comparisons. Strings like "ACTIVE" and "GE" carried implicit rules with no
 * central definition.
 *
 * Refactorings applied:
 * - Replace Primitive with Object:
 *     StudentStatus enum replaces the raw "ACTIVE"/"BLOCKED" string.
 *     CountryCode value-object replaces the raw country string.
 *     Age value-object wraps the int and enforces adult check.
 *     UnpaidBalance value-object wraps the double and encodes the threshold.
 * - canRentDormRoom() now delegates each check to the appropriate object,
 *   reads like a sentence, and requires no inline comments.
 *
 * Why it is better:
 * Each domain concept knows its own rules. Adding a new status, changing the
 * balance threshold, or recognising a new country code requires one change
 * in one place. The method body shows intent, not raw comparisons.
 */
public class PrimitiveObsessionExample {

    enum StudentStatus {
        ACTIVE, BLOCKED, GRADUATED;

        public boolean isEligibleForDorm() {
            return this == ACTIVE;
        }
    }

    static class Age {
        private final int value;

        Age(int value) { this.value = value; }

        public boolean isAdult() { return value >= 18; }
    }

    static class UnpaidBalance {
        private static final double DORM_ELIGIBILITY_LIMIT = 100.0;
        private final double amount;

        UnpaidBalance(double amount) { this.amount = amount; }

        public boolean isWithinDormLimit() { return amount < DORM_ELIGIBILITY_LIMIT; }
    }

    static class CountryCode {
        private final String code;

        CountryCode(String code) { this.code = code; }

        public boolean isDomestic() { return "GE".equals(code); }
    }

    public boolean canRentDormRoom(Age age, StudentStatus status,
                                   UnpaidBalance balance, CountryCode country) {
        return age.isAdult()
            && status.isEligibleForDorm()
            && balance.isWithinDormLimit()
            && country.isDomestic();
    }

    public void clientCode() {
        System.out.println(canRentDormRoom(
                new Age(19), StudentStatus.ACTIVE,
                new UnpaidBalance(0.0), new CountryCode("GE")));   // true

        System.out.println(canRentDormRoom(
                new Age(17), StudentStatus.BLOCKED,
                new UnpaidBalance(120.0), new CountryCode("US"))); // false
    }
}
