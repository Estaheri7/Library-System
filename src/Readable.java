import java.time.LocalDateTime;

/**
 * The {@code Readable} interface represents an item that can be read in the library system.
 * It defines methods to check if the item can be read at a specific time and to mark the item as read.
 */
public interface Readable {

    /**
     * Checks if the item can be read at the specified time.
     *
     * @param newTime The time at which the item is to be read.
     * @return {@code true} if the item can be read at the specified time, {@code false} otherwise.
     */
    boolean canRead(LocalDateTime newTime);

    /**
     * Marks the item as read at the specified time.
     *
     * @param readTime The time at which the item is read.
     */
    void read(LocalDateTime readTime);
}

