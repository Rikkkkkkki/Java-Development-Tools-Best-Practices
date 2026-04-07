package Assignment2.BadSmells;

/*
 * Smell: Speculative Generality
 *
 * Original problem:
 * The NotificationChannel interface carried four parameters — message,
 * futureTemplate, encrypted, and urgent — but the only implementation
 * (EmailChannel) ignored the last three entirely. The extra parameters were
 * prepared for requirements that did not exist, making every call site pass
 * meaningless values.
 *
 * Refactorings applied:
 * - Remove Parameter (Change Method Signature): futureTemplate, encrypted,
 *   and urgent are deleted from the interface and the implementation.
 * - The interface and class now match what is actually needed today.
 *
 * Why it is better:
 * Call sites become honest — no dummy arguments. The interface expresses
 * real capability, not imagined future capability. When encryption or
 * templates are genuinely needed, they can be added with a real design then.
 */
public class SpeculativeGeneralityExample {

    interface NotificationChannel {
        void send(String message);
    }

    static class EmailChannel implements NotificationChannel {
        @Override
        public void send(String message) {
            System.out.println(message);
        }
    }

    public void clientCode() {
        NotificationChannel channel = new EmailChannel();
        channel.send("Exam starts at 10:00");
    }
}
