package Assignment2.BadSmells;

/*
 * Smell: Alternative Classes with Different Interfaces
 *
 * Original problem:
 * ZoomClassroom and TeamsClassroom served the same purpose — starting an
 * online class session — but exposed different method names: beginSession()
 * vs openMeeting(). Client code had to know the concrete type of each object
 * and could not substitute one for the other.
 *
 * Refactorings applied:
 * - Extract Interface: VirtualClassroom declares the unified contract
 *   startSession().
 * - Rename Method: beginSession() → startSession() in ZoomClassroom;
 *   openMeeting() → startSession() in TeamsClassroom.
 * - Use Supertype Where Possible: clientCode now holds VirtualClassroom
 *   references and loops uniformly over both.
 *
 * Why it is better:
 * Any future platform (e.g. MeetClassroom) can be added without changing
 * client code. Both implementations are substitutable wherever
 * VirtualClassroom is expected. The type-specific branching is gone.
 */
public class AlternativeClassesWithDifferentInterfacesExample {

    interface VirtualClassroom {
        void startSession();
    }

    static class ZoomClassroom implements VirtualClassroom {
        @Override
        public void startSession() {
            System.out.println("Zoom session started");
        }
    }

    static class TeamsClassroom implements VirtualClassroom {
        @Override
        public void startSession() {
            System.out.println("Teams meeting started");
        }
    }

    public void clientCode() {
        VirtualClassroom zoom  = new ZoomClassroom();
        VirtualClassroom teams = new TeamsClassroom();

        for (VirtualClassroom classroom : new VirtualClassroom[]{zoom, teams}) {
            classroom.startSession();
        }
    }
}
