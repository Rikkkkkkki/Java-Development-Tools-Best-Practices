package Assignment2.BadSmells;

/*
 * Smell: Message Chains
 *
 * Original problem:
 * Client code called university.getDepartment().getCoordinator().getOffice()
 * .getPhoneNumber() — a four-hop chain. The caller was coupled to the entire
 * internal structure of University and its contained objects. Any change to
 * the object graph (e.g., Department no longer has a Coordinator) breaks the
 * call site.
 *
 * Refactoring applied:
 * - Hide Delegate: University exposes getCoordinatorPhoneNumber() and
 *   traverses the chain internally. The chain shrinks to one call for the caller.
 *
 * Why it is better:
 * Client code is decoupled from the internal object structure. If the path
 * to a phone number changes, only University.getCoordinatorPhoneNumber()
 * needs updating, not every call site.
 */
public class MessageChainsExample {

    static class University {
        private final Department department = new Department();

        public String getCoordinatorPhoneNumber() {
            return department.getCoordinator().getOffice().getPhoneNumber();
        }
    }

    static class Department {
        Coordinator getCoordinator() { return new Coordinator(); }
    }

    static class Coordinator {
        Office getOffice() { return new Office(); }
    }

    static class Office {
        String getPhoneNumber() { return "555-0101"; }
    }

    public void clientCode() {
        University university = new University();
        System.out.println(university.getCoordinatorPhoneNumber());
    }
}
