public class PurchasableBook extends Item {
    private String publisher;
    private String price;
    private String discount;

    public PurchasableBook(String itemId, String title, String authorName, String publisher, String year,
                           int copies, String price, String discount, String categoryId, String libraryId) {
        super(itemId, title, authorName, year, copies, categoryId, libraryId);
        this.publisher = publisher;
        this.price = price;
        this.discount = discount;
    }
}
