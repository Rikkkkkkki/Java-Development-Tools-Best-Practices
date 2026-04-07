package Assignment2.BadSmells;

/*
 * Smell: Temporary Field
 *
 * Original problem:
 * examRoom and onlineMeetingLink both lived as instance fields on the same
 * class, but each was only ever set in one mode. When the onsite path ran,
 * onlineMeetingLink was null and irrelevant; vice versa for examRoom. The
 * object's state was always partially meaningless depending on context.
 *
 * Refactorings applied:
 * - Extract Class: OnsiteExam and OnlineExam each own only the field and
 *   logic that belongs to their mode.
 * - The original class's methods became standalone value-returning operations
 *   on their respective classes, so no temporary fields remain anywhere.
 *
 * Why it is better:
 * Each class has a consistent, fully initialised state. No field is ever
 * null or irrelevant. Adding behaviour to one exam mode does not risk
 * accidentally affecting the other.
 */
public class TemporaryFieldExample {

    static class OnsiteExam {
        private final String room;

        OnsiteExam(String room) {
            this.room = room;
        }

        public String prepare() {
            return "Use room " + room;
        }
    }

    static class OnlineExam {
        private final String meetingLink;

        OnlineExam(String meetingLink) {
            this.meetingLink = meetingLink;
        }

        public String prepare() {
            return "Join " + meetingLink;
        }
    }

    public void clientCode() {
        OnsiteExam onsite = new OnsiteExam("B-204");
        OnlineExam online = new OnlineExam("https://meet.example/exam");

        System.out.println(onsite.prepare());
        System.out.println(online.prepare());
        System.out.println("room=" + "B-204" + ", link=" + "https://meet.example/exam");
    }
}
