import java.util.ArrayList;
import java.util.Collections;

public class SearchHandler {
    private String searchKey;
    private ArrayList<String> searchResults = new ArrayList<>();

    public SearchHandler(String searchKey) {
        this.searchKey = searchKey;
    }

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
