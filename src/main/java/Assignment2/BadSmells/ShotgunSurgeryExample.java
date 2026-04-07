package Assignment2.BadSmells;

/*
 * Smell: Shotgun Surgery
 *
 * Original problem:
 * The course title formatting string ("Course: ", "Invoice for ", "Completed ")
 * was scattered across three separate classes. Changing how a course title is
 * presented — even a minor wording tweak — required touching Course, Invoice,
 * and Certificate independently.
 *
 * Refactorings applied:
 * - Move Method: a CourseTitleFormatter class centralises all presentation
 *   rules for a course title. Course, Invoice, and Certificate each receive
 *   a CourseTitleFormatter (or use it statically) instead of embedding their
 *   own format strings.
 * - Single Source of Truth: every label, description, and certificate text is
 *   now derived from the same formatter. One change propagates everywhere.
 *
 * Why it is better:
 * A wording change requires exactly one edit in CourseTitleFormatter.
 * No risk of the three classes drifting apart.
 */
public class ShotgunSurgeryExample {

    static class CourseTitleFormatter {
        public String label(String courseTitle) {
            return "Course: " + courseTitle;
        }

        public String invoiceDescription(String courseTitle) {
            return "Invoice for " + courseTitle;
        }

        public String certificateText(String courseTitle) {
            return "Completed " + courseTitle;
        }
    }

    static class Course {
        private final String title;
        private final CourseTitleFormatter formatter;

        Course(String title, CourseTitleFormatter formatter) {
            this.title     = title;
            this.formatter = formatter;
        }

        public String label() {
            return formatter.label(title);
        }
    }

    static class Invoice {
        private final String courseTitle;
        private final CourseTitleFormatter formatter;

        Invoice(String courseTitle, CourseTitleFormatter formatter) {
            this.courseTitle = courseTitle;
            this.formatter   = formatter;
        }

        public String description() {
            return formatter.invoiceDescription(courseTitle);
        }
    }

    static class Certificate {
        private final String courseTitle;
        private final CourseTitleFormatter formatter;

        Certificate(String courseTitle, CourseTitleFormatter formatter) {
            this.courseTitle = courseTitle;
            this.formatter   = formatter;
        }

        public String text() {
            return formatter.certificateText(courseTitle);
        }
    }

    public void clientCode() {
        CourseTitleFormatter formatter = new CourseTitleFormatter();

        Course      course      = new Course("Refactoring", formatter);
        Invoice     invoice     = new Invoice("Refactoring", formatter);
        Certificate certificate = new Certificate("Refactoring", formatter);

        System.out.println(course.label());
        System.out.println(invoice.description());
        System.out.println(certificate.text());
    }
}
