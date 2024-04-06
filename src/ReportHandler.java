import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class ReportHandler {
    private String personId;
    private String password;

    public ReportHandler(String personId, String password) {
        this.personId = personId;
        this.password = password;
    }

    public String categoryReport(String categoryId, String libraryId) {
        if (!Center.libraryExists(libraryId) || !Center.personExists(this.personId) ||
            !Center.categoryExists(categoryId)) {
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
            for (String superCategory : categories) {
                if (category.getSuperCategory().equals(superCategory)) {
                    categories.add(category.getCategoryId());
                }
            }
        }

        return library.categoryReport(categories);
    }

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
                Item item = library.getItems().get(borrow.getItemId());
                long hourBetween = ChronoUnit.HOURS.between(borrow.getBorrowTime(), givenDate);
                if (person.includesDebt(hourBetween, item)) {
                    uniques.add(item.getItemId());
                }
            }
        }

        return this.printResults(new ArrayList<>(uniques));
    }

    public StringBuilder printResults(ArrayList<String> uniques) {
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
}
