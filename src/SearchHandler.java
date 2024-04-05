import java.util.ArrayList;
import java.util.Collections;

/**
 * The {@code SearchHandler} class provides methods for searching books and users within the library system.
 * It allows searching by a keyword and returns matching results.
 */
public class SearchHandler {
    private String searchKey;
    private ArrayList<String> searchResults = new ArrayList<>();

    /**
     * Constructs a new SearchHandler object with the specified search keyword.
     *
     * @param searchKey The keyword used for searching.
     */
    public SearchHandler(String searchKey) {
        this.searchKey = searchKey;
    }

    /**
     * Searches for books containing the specified keyword.
     *
     * @return A StringBuilder containing the IDs of books that match the search criteria,
     *         separated by '|', or a message indicating no results found.
     */
    public StringBuilder searchBook() {
        for (Library library : Center.getLibraries().values()) {
            for (Item item : library.getItems().values()) {
                if (item.search(this.searchKey)) {
                    this.searchResults.add(item.getItemId());
                }
            }
        }

        return returnResults();
    }

    /**
     * Searches for users containing the specified keyword.
     *
     * @param personId The ID of the user performing the search.
     * @param password The password of the user performing the search.
     * @return A StringBuilder containing the IDs of users that match the search criteria,
     *         separated by '|', or a message indicating no results found.
     */
    public StringBuilder searchUser(String personId, String password) {
        if (!Center.personExists(personId)) {
            return new StringBuilder("not-found");
        }

        if (!Center.isCorrectPassword(personId, password)) {
            return new StringBuilder("invalid-pass");
        }

        if (Center.describeRole(personId).equals("student")) {
            return new StringBuilder("permission-denied");
        }

        for (Person person : Center.getPersons().values()) {
            if (person.search(this.searchKey)) {
                this.searchResults.add(person.getPersonId());
            }
        }

        return returnResults();
    }

    /**
     * Formats and returns the search results.
     *
     * @return A StringBuilder containing the formatted search results,
     *         separated by '|', or a message indicating no results found.
     */
    private StringBuilder returnResults() {
        if (this.searchResults.size() == 0) {
            return new StringBuilder("not-found");
        }

        Collections.sort(this.searchResults);
        StringBuilder resultIds = new StringBuilder();

        for (String id : this.searchResults) {
            resultIds.append(id);
            resultIds.append("|");
        }

        resultIds.deleteCharAt(resultIds.length() - 1);
        return resultIds;
    }
}
