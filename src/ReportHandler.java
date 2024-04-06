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
}
