package Assignment2.BadSmells;

/*
 * Smell: Data Clumps
 *
 * Original problem:
 * name, email, and phone always traveled together as three separate parameters
 * across every method. The same trio appeared in buildLabel, buildEmailGreeting,
 * buildSmsMessage, and isReachable — and was repeated three times in clientCode
 * for three different people.
 *
 * Refactorings applied:
 * - Introduce Parameter Object (Extract Class): ContactInfo encapsulates the
 *   three fields as one concept.
 * - Move Method: the four operations are moved into ContactInfo because they
 *   act entirely on its data.
 *
 * Why it is better:
 * Every method signature drops from three parameters to one object. ClientCode
 * becomes compact and readable. Adding a new contact field or operation requires
 * a change in one class, not four method signatures.
 */
public class DataClumpsExample {

    public static class ContactInfo {
        private final String name;
        private final String email;
        private final String phone;

        public ContactInfo(String name, String email, String phone) {
            this.name  = name;
            this.email = email;
            this.phone = phone;
        }

        public String buildLabel() {
            return name + " <" + email + ">, phone: " + phone;
        }

        public String buildEmailGreeting() {
            return "To: " + email + ", hello " + name;
        }

        public String buildSmsMessage() {
            return "SMS to " + phone + ": Hi " + name;
        }

        public boolean isReachable() {
            return email != null && !email.trim().isEmpty()
                && phone != null && !phone.trim().isEmpty();
        }
    }

    public void clientCode() {
        ContactInfo student    = new ContactInfo("Nino",   "nino@example.com",   "+995-555-000-001");
        ContactInfo advisor    = new ContactInfo("Giorgi", "giorgi@example.com", "+995-555-000-002");
        ContactInfo accountant = new ContactInfo("Maka",   "maka@example.com",   "+995-555-000-003");

        for (ContactInfo contact : new ContactInfo[]{student, advisor, accountant}) {
            System.out.println(contact.buildLabel());
            System.out.println(contact.buildEmailGreeting());
            System.out.println(contact.buildSmsMessage());
            System.out.println(contact.isReachable());
        }
    }
}
