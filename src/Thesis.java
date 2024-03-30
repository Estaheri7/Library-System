public class Thesis extends Item {
    private String advisorName;

    public Thesis(String itemId, String title, String authorName, String advisorName, String year,
                  String categoryId, String libraryId) {
        super(itemId, title, authorName, year, 1, categoryId, libraryId);
        this.advisorName = advisorName;
    }
}
