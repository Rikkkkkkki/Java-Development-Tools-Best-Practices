package Assignment2.BadSmells;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Smell: Mutable Data
 *
 * Original problem:
 * getEnrolledStudents() returned the live internal list. Any caller could call
 * .clear(), .add(), or .remove() on it without the owning class knowing.
 * The clientCode() example demonstrated this exactly: it cleared the list
 * from outside, bypassing all class invariants.
 *
 * Refactorings applied:
 * - Encapsulate Field: the list is private and final.
 * - Return Unmodifiable View: getEnrolledStudents() wraps the list in
 *   Collections.unmodifiableList(), so callers can read but not mutate.
 * - All writes go through the controlled enroll() method.
 *
 * Why it is better:
 * State can only change through the class's own methods. External code can
 * never corrupt the enrollment list behind the class's back. The invariant is
 * enforceable — for example, adding duplicate prevention to enroll() works now.
 */
public class MutableDataExample {

    private final List<String> enrolledStudents = new ArrayList<>();

    public List<String> getEnrolledStudents() {
        return Collections.unmodifiableList(enrolledStudents);
    }

    public void enroll(String studentId) {
        enrolledStudents.add(studentId);
    }

    public void clientCode() {
        enroll("s-1001");
        List<String> students = getEnrolledStudents();
        // students.clear(); // would now throw UnsupportedOperationException
        System.out.println(getEnrolledStudents().size()); // prints 1
    }
}
