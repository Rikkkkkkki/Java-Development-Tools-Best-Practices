package Assignment2.BadSmells;

/*
 * Smell: Refused Bequest
 *
 * Original problem:
 * Penguin extended Bird and inherited fly(), but immediately threw
 * UnsupportedOperationException. The inheritance relationship promised
 * every Bird can fly, but Penguin broke that promise — a violation of
 * the Liskov Substitution Principle.
 *
 * Refactorings applied:
 * - Extract Superclass: Bird becomes a common base with only the behaviors
 *   that ALL birds share (here: move(), which is introduced as a general
 *   movement abstraction).
 * - Extract Interface: FlyingBird interface carries the fly() contract.
 *   Only birds that actually fly implement it.
 * - Push Down: fly() is removed from the common Bird base and lives only in
 *   FlyingBird implementations (Sparrow, Eagle).
 * - Penguin extends Bird without implementing FlyingBird — it overrides
 *   move() with its own locomotion, refusing nothing.
 *
 * Why it is better:
 * No class throws UnsupportedOperationException. The type hierarchy
 * accurately describes the domain: some birds fly, some do not. A method
 * that iterates List<FlyingBird> is guaranteed all birds fly. A method
 * that iterates List<Bird> makes no such promise and cannot accidentally
 * call fly() on a Penguin.
 */
public class RefusedBequestExample {

    interface FlyingBird {
        void fly();
    }

    static abstract class Bird {
        public abstract void move();
    }

    static class Sparrow extends Bird implements FlyingBird {
        @Override
        public void fly()  { System.out.println("Sparrow is flying"); }

        @Override
        public void move() { fly(); }
    }

    static class Eagle extends Bird implements FlyingBird {
        @Override
        public void fly()  { System.out.println("Eagle is soaring"); }

        @Override
        public void move() { fly(); }
    }

    static class Penguin extends Bird {
        @Override
        public void move() { System.out.println("Penguin is swimming"); }
    }

    public void clientCode() {
        Bird sparrow = new Sparrow();
        Bird eagle   = new Eagle();
        Bird penguin = new Penguin();

        sparrow.move(); // Sparrow is flying
        eagle.move();   // Eagle is soaring
        penguin.move(); // Penguin is swimming  — no exception, no lie

        // Only flying birds can be asked to fly:
        FlyingBird flyingSparrow = (FlyingBird) sparrow;
        flyingSparrow.fly();
    }
}
