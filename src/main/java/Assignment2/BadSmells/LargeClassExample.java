package Assignment2.BadSmells;

import java.util.ArrayList;
import java.util.List;

/*
 * Smell: Large Class
 *
 * Original problem:
 * One class managed enrollment, staffing, courses, finance, help-desk tickets,
 * the website theme, the bus schedule, the cafeteria menu, and payroll.
 * Eight or nine unrelated concerns were crammed into a single abstraction.
 *
 * Refactorings applied:
 * - Extract Class (repeated): five focused classes extracted, each owning one
 *   cohesive domain area:
 *     EnrollmentOffice    — students and teachers
 *     CourseCatalog       — course list
 *     FinanceOffice       — budget, tuition, salaries
 *     FacilitiesService   — bus schedule and cafeteria menu
 *     AdministrationPortal — help desk, website theme, payroll day
 * - Move Field + Move Method: every field and method migrated to the class
 *   that owns its concern.
 * - SchoolAdministration is kept as a thin facade so the original clientCode
 *   call pattern remains valid, but it no longer carries any fields.
 *
 * Why it is better:
 * Each class has one reason to change. A cafeteria menu update does not risk
 * touching financial logic. Cognitive load per class is dramatically lower.
 */
public class LargeClassExample {

    static class EnrollmentOffice {
        private final List<String> students = new ArrayList<>();
        private final List<String> teachers = new ArrayList<>();

        public void enrollStudent(String student) { students.add(student); }
        public void hireTeacher(String teacher)   { teachers.add(teacher); }

        public List<String> getStudents() { return students; }
        public List<String> getTeachers() { return teachers; }
    }

    static class CourseCatalog {
        private final List<String> courses = new ArrayList<>();

        public void addCourse(String course) { courses.add(course); }
        public List<String> getCourses()     { return courses; }
    }

    static class FinanceOffice {
        private double budget;

        public void chargeTuition(double amount) { budget += amount; }
        public void paySalary(double amount)     { budget -= amount; }
        public double getBudget()                { return budget; }
    }

    static class FacilitiesService {
        private String busSchedule;
        private String cafeteriaMenu;

        public void publishBusSchedule(String schedule) { busSchedule = schedule; }
        public void publishCafeteriaMenu(String menu)   { cafeteriaMenu = menu; }
    }

    static class AdministrationPortal {
        private int    openTickets;
        private String websiteTheme;
        private String payrollDay;

        public void openHelpDeskTicket()            { openTickets++; }
        public void updateWebsiteTheme(String theme){ websiteTheme = theme; }
        public void setPayrollDay(String day)       { payrollDay = day; }
    }

    // Thin coordinator — no fields, just wires the extracted classes together.
    static class SchoolAdministration {
        final EnrollmentOffice    enrollment     = new EnrollmentOffice();
        final CourseCatalog       courses        = new CourseCatalog();
        final FinanceOffice       finance        = new FinanceOffice();
        final FacilitiesService   facilities     = new FacilitiesService();
        final AdministrationPortal administration = new AdministrationPortal();
    }

    public void clientCode() {
        SchoolAdministration school = new SchoolAdministration();

        school.enrollment.enrollStudent("Nino");
        school.enrollment.hireTeacher("Ms. Kapanadze");
        school.courses.addCourse("Refactoring");
        school.finance.chargeTuition(2400);
        school.finance.paySalary(1200);
        school.administration.openHelpDeskTicket();
        school.administration.updateWebsiteTheme("blue");
        school.facilities.publishBusSchedule("Route A at 08:00");
        school.facilities.publishCafeteriaMenu("Soup and salad");
        school.administration.setPayrollDay("Friday");
    }
}
