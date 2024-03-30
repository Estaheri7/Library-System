public class TreasureBook extends Item {
    private String publisher;
    private String donateName;

    public TreasureBook(String itemId, String title, String authorName, String publisher, String year,
                        String donateName, String categoryId, String libraryId) {
        super(itemId, title, authorName, year, 1, categoryId, libraryId);
        this.publisher = publisher;
        this.donateName = donateName;
    }
}
