import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * The {@code ReportHandler} class provides methods to generate various reports in the library system.
 * It allows generating category reports, library reports, and reports on items passed their return deadline.
 * Additionally, it provides a method to calculate the sum of penalties incurred by all users.
 */
public class ReportHandler {
    private String personId;
    private String password;

    /**
     * Constructs a new ReportHandler object with the specified person ID and password.
     *
     * @param personId The ID of the person accessing the reports.
     * @param password The password of the person accessing the reports.
     */
    public ReportHandler(String personId, String password) {
        this.personId = personId;
        this.password = password;
    }

    /**
     * Generates a category report for the specified category and library.
     *
     * @param categoryId The ID of the category for which the report is generated.
     * @param libraryId  The ID of the library for which the report is generated.
     * @return A string representing the category report.
     */
    public String categoryReport(String categoryId, String libraryId) {
        if (!Center.libraryExists(libraryId) || !Center.personExists(this.personId)) {
            return "not-found";
        }

        if (!Center.categoryExists(categoryId) && !categoryId.equals("null")) {
            return "not-found";
        }

        if (!Center.isLibraryManager(this.personId, libraryId)) {
            return "permission-denied";
        }

        if (!Center.isCorrectPassword(this.personId, this.password)) {
            return "invalid-pass";
        }

        Library library = Center.getLibraries().get(libraryId);
        HashSet<String> categories = new HashSet<>();
        categories.add(categoryId);
        if (categoryId.equals("null")) {
            return library.categoryReport(categories);
        }

        for (Category category : Center.getCategories().values()) {
            if (category.getSuperCategory().equals(categoryId)) {
                categories.add(category.getCategoryId());
            }
        }

        for (Category category : Center.getCategories().values()) {
            for (String superCategory : categories) {
                if (category.getSuperCategory().equals(superCategory)) {
                    categories.add(category.getCategoryId());
                }
            }
        }

        return library.categoryReport(categories);
    }

    /**
     * Generates a library report for the specified library.
     *
     * @param libraryId The ID of the library for which the report is generated.
     * @return A string representing the library report.
     */
    public String libraryReport(String libraryId) {
        if (!Center.libraryExists(libraryId) || !Center.personExists(this.personId)) {
            return "not-found";
        }

        if (!Center.isLibraryManager(this.personId, libraryId)) {
            return "permission-denied";
        }

        if (!Center.isCorrectPassword(this.personId, this.password)) {
            return "invalid-pass";
        }

        Library library = Center.getLibraries().get(libraryId);
        return library.libraryReport();
    }

    /**
     * Generates a report for items that have passed their return deadline in the specified library.
     *
     * @param libraryId The ID of the library for which the report is generated.
     * @param date      The date of the deadline check in "YYYY-MM-DD" format.
     * @param clock     The time of the deadline check in "HH:MM" format.
     * @return A StringBuilder containing the IDs of the items passed their return deadline.
     */
    public StringBuilder reportPassedDeadline(String libraryId, String date, String clock) {
        if (!Center.libraryExists(libraryId) || !Center.personExists(this.personId)) {
            return new StringBuilder("not-found");
        }

        if (!Center.isLibraryManager(this.personId, libraryId)) {
            return new StringBuilder("permission-denied");
        }

        if (!Center.isCorrectPassword(this.personId, this.password)) {
            return new StringBuilder("invalid-pass");
        }

        Library library = Center.getLibraries().get(libraryId);
        String fixedDate = Center.formatDate(date);
        LocalDateTime givenDate = LocalDateTime.parse(fixedDate + "T" + clock);

        HashSet<String> uniques = new HashSet<>();
        for (Person person : Center.getPersons().values()) {
            ArrayList<Borrow> borrows = person.getBorrows();
            for (Borrow borrow : borrows) {
                if (borrow.getLibraryId().equals(libraryId)) {
                    Item item = library.getItems().get(borrow.getItemId());
                    long hourBetween = ChronoUnit.HOURS.between(borrow.getBorrowTime(), givenDate);
                    if (person.includesDebt(hourBetween, item)) {
                        uniques.add(item.getItemId());
                    }
                }
            }
        }

        return this.printResults(new ArrayList<>(uniques));
    }

    /**
     * Helper method to format and print the results of a report.
     *
     * @param uniques The unique item IDs to be printed.
     * @return A StringBuilder containing the formatted result.
     */
    private StringBuilder printResults(ArrayList<String> uniques) {
        if (uniques.size() == 0) {
            return new StringBuilder("none");
        }

        Collections.sort(uniques);
        StringBuilder resultIds = new StringBuilder();

        for (String id : uniques) {
            resultIds.append(id);
            resultIds.append("|");
        }

        resultIds.deleteCharAt(resultIds.length() - 1);
        return resultIds;
    }

    /**
     * Calculates the sum of penalties incurred by all users in the system.
     *
     * @return A string representing the total sum of penalties.
     */
    public String reportPenaltiesSum() {
        if (!Center.personExists(this.personId)) {
            return "not-found";
        }

        if (!Center.isCorrectPassword(this.personId, this.password)) {
            return "invalid-pass";
        }

        if (!Center.describeRole(personId).equals("admin")) {
            return "permission-denied";
        }

        long totalDebt = 0;
        for (Person person : Center.getPersons().values()) {
            totalDebt += person.totalDebt;
        }

        return "" + totalDebt;
    }
}
