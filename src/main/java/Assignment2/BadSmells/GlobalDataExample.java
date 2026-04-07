package Assignment2.BadSmells;

/*
 * Smell: Global Data
 *
 * Original problem:
 * currentSemester and tuitionRate were public static fields reachable and
 * writable from any class. Any module could silently change the state without
 * the owning class being aware, making bugs hard to trace.
 *
 * Refactorings applied:
 * - Extract Class: AcademicSettings encapsulates both values.
 * - Encapsulate Field: fields made private; controlled via getters and
 *   intention-revealing mutator methods.
 * - BillingService and SemesterAdministration receive AcademicSettings
 *   by constructor (dependency injection) instead of reaching for globals.
 *
 * Why it is better:
 * State changes go through methods with clear names. No code outside
 * AcademicSettings can mutate tuitionRate or currentSemester directly.
 * The scope of potential side effects is narrow and easy to audit.
 */
public class GlobalDataExample {

    static class AcademicSettings {
        private String currentSemester = "SPRING";
        private double tuitionRate     = 1250.0;

        public String getCurrentSemester() { return currentSemester; }
        public double getTuitionRate()     { return tuitionRate; }

        public void openFallSemester()        { currentSemester = "FALL"; }
        public void applyRateIncrease(double delta) { tuitionRate += delta; }
    }

    static class BillingService {
        private final AcademicSettings settings;

        BillingService(AcademicSettings settings) {
            this.settings = settings;
        }

        public double calculateInvoice(int credits) {
            return credits * settings.getTuitionRate();
        }
    }

    static class SemesterAdministration {
        private final AcademicSettings settings;

        SemesterAdministration(AcademicSettings settings) {
            this.settings = settings;
        }

        public void openFallSemester()  { settings.openFallSemester(); }
        public void approveRateIncrease() { settings.applyRateIncrease(100); }
    }

    public void clientCode() {
        AcademicSettings        settings       = new AcademicSettings();
        BillingService          billingService = new BillingService(settings);
        SemesterAdministration  administration = new SemesterAdministration(settings);

        System.out.println(settings.getCurrentSemester());
        System.out.println(billingService.calculateInvoice(3));

        administration.openFallSemester();
        administration.approveRateIncrease();

        System.out.println(settings.getCurrentSemester());
        System.out.println(billingService.calculateInvoice(3));
    }
}
