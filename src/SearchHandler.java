import java.util.ArrayList;
import java.util.Collections;

public class SearchHandler {
    private String searchKey;
    private ArrayList<String> books = new ArrayList<>();

    public SearchHandler(String searchKey) {
        this.searchKey = searchKey;
    }

    public StringBuilder searchBook() {
        for (Library library : Center.getLibraries().values()) {
            for (Item item : library.getItems().values()) {
                if (item.search(this.searchKey)) {
                    this.books.add(item.getItemId());
                }
            }
        }

        return returnResults();
    }

    private StringBuilder returnResults() {
        if (this.books.size() == 0) {
            return new StringBuilder("not-found");
        }

        Collections.sort(this.books);
        StringBuilder bookIds = new StringBuilder();

        for (String id : this.books) {
            bookIds.append(id);
            bookIds.append("|");
        }

        bookIds.deleteCharAt(bookIds.length() - 1);
        return bookIds;
    }
}
