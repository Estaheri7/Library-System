import java.util.HashMap;

public class Center {
    private final static HashMap<String, Person> persons = new HashMap<>();
    private final static HashMap<String, Library> libraries = new HashMap<>();
    private final static HashMap<String, Category> categories = new HashMap<>();

    private static boolean libraryExists(String key) {
        return libraries.containsKey(key);
    }

    private static boolean categoryExists(String key) {
        return categories.containsKey(key);
    }

    private static boolean personExists(String key) {
        return persons.containsKey(key);
    }
}
