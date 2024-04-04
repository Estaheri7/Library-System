/**
 * The {@code Purchasable} interface represents an item that can be purchased in the library system.
 * It defines methods to check if the item is sold out and to sell the item.
 */
public interface Purchasable {
    /**
     * Checks if the item is sold out.
     *
     * @return {@code true} if the item is sold out, {@code false} otherwise.
     */
    boolean isSoldOut();
    /**
     * Sells the item.
     */
    void sell();
}
