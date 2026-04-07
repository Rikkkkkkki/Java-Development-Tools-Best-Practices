package Assignment2.BadSmells;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Smell: Loops
 *
 * Original problem:
 * The for-loop in honorStudents() forced the reader to trace mutable state
 * (the result list) and control flow to understand a simple filter-and-map
 * operation. Fowler flags this when a stream pipeline expresses the intent
 * more directly.
 *
 * Refactoring applied:
 * - Replace Loop with Pipeline: rewritten as a stream filter + map + collect.
 * - Extract helper predicate isHonorStudent() to give the threshold a name.
 *
 * Why it is better:
 * The pipeline reads declaratively: keep students that are honor students,
 * then collect their names. No mutable accumulator list, no index, no noise.
 */
public class LoopsExample {

    public List<String> honorStudents(List<Student> students) {
        return students.stream()
                .filter(this::isHonorStudent)
                .map(s -> s.name)
                .collect(Collectors.toList());
    }

    private boolean isHonorStudent(Student student) {
        return student.gpa > 3.5;
    }

    static class Student {
        final String name;
        final double gpa;

        Student(String name, double gpa) {
            this.name = name;
            this.gpa  = gpa;
        }
    }

    public void clientCode() {
        List<Student> students = List.of(
                new Student("Nino",   3.9),
                new Student("Giorgi", 3.1),
                new Student("Maka",   3.7));
        System.out.println(honorStudents(students));
    }
}
