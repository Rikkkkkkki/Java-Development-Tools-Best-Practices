package Assignment2.BadSmells;

/*
 * Smell: Lazy Element
 *
 * Original problem:
 * StudentNameFormatter existed only to call String.trim(). A whole class was
 * created for a single one-liner that has no variation, no state, and no
 * reason to grow. The abstraction bought nothing.
 *
 * Refactoring applied:
 * - Inline Class: StudentNameFormatter is removed. The call site uses
 *   String.trim() directly.
 *
 * Why it is better:
 * One less class to navigate. The behavior was always trivially obvious
 * from the standard library call; the wrapper only obscured it.
 */
public class LazyElementExample {

    public void clientCode() {
        System.out.println("  Nino  ".trim());
    }
}
