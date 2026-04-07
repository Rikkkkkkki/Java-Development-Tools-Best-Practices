package Assignment2.BadSmells;

/*
 * Smell: Long Parameter List
 *
 * Original problem:
 * registerStudent() took 12 parameters. The signature was noisy and error-prone;
 * callers had to remember argument order across closely typed values like three
 * String phone numbers in a row.
 *
 * Refactorings applied:
 * - Introduce Parameter Object (×2):
 *     Address        groups city, street, zipCode.
 *     GuardianContact groups guardianName and guardianPhone.
 * - Introduce Parameter Object (×1):
 *     EnrollmentInfo groups program, startYear, scholarship.
 * - registerStudent() now takes 5 meaningful objects instead of 12 primitives.
 *
 * Why it is better:
 * Call sites are shorter and self-documenting (labeled constructors).
 * Reordering risk is vastly reduced. Each parameter object can later gain
 * its own validation logic without changing the registration signature.
 */
public class LongParameterListExample {

    static class Address {
        final String city;
        final String street;
        final String zipCode;

        Address(String city, String street, String zipCode) {
            this.city    = city;
            this.street  = street;
            this.zipCode = zipCode;
        }

        @Override public String toString() {
            return city + ", " + street + ", " + zipCode;
        }
    }

    static class GuardianContact {
        final String name;
        final String phone;

        GuardianContact(String name, String phone) {
            this.name  = name;
            this.phone = phone;
        }
    }

    static class EnrollmentInfo {
        final String  program;
        final int     startYear;
        final boolean scholarship;

        EnrollmentInfo(String program, int startYear, boolean scholarship) {
            this.program     = program;
            this.startYear   = startYear;
            this.scholarship = scholarship;
        }
    }

    public String registerStudent(String firstName, String lastName,
                                  String email, String phone,
                                  Address address,
                                  GuardianContact guardian,
                                  EnrollmentInfo enrollment) {
        return firstName + " " + lastName
             + " -> " + enrollment.program + " (" + enrollment.startYear + ")"
             + ", guardian=" + guardian.name
             + ", scholarship=" + enrollment.scholarship
             + ", address=" + address
             + ", contact=" + email + "/" + phone
             + ", guardianPhone=" + guardian.phone;
    }

    public void clientCode() {
        Address        address  = new Address("Tbilisi", "Rustaveli Ave 10", "0108");
        GuardianContact guardian = new GuardianContact("Maka Beridze", "+995-555-000-999");
        EnrollmentInfo  enroll   = new EnrollmentInfo("Computer Science", 2026, true);

        String summary = registerStudent(
                "Nino", "Beridze",
                "nino@example.com", "+995-555-000-001",
                address, guardian, enroll);

        System.out.println(summary);
    }
}
