package Assignment2.BadSmells;

/*
 * Smell: Middle Man
 *
 * Original problem:
 * StudentPortal had exactly one method that did nothing except forward the call
 * to TranscriptService. It added no behavior, no policy, no translation — just
 * noise and an extra hop.
 *
 * Refactoring applied:
 * - Remove Middle Man (Inline Class): StudentPortal is removed. The client
 *   talks directly to TranscriptService.
 *
 * Why it is better:
 * One less class to maintain. The indirection provided no encapsulation benefit
 * and only obscured what was happening. Direct calls are simpler and equally
 * readable.
 */
public class MiddleManExample {

    static class TranscriptService {
        public String findGrade(String studentId) {
            return "A";
        }
    }

    public void clientCode() {
        TranscriptService transcriptService = new TranscriptService();
        System.out.println(transcriptService.findGrade("s-1001"));
    }
}
