package designPatterns.singleton.singleThreaded;

public class DatabaseConnection {
    private static DatabaseConnection databaseConnection = null;
    public String value;

    private DatabaseConnection(String value) {
        this.value = value;
    };  // privateConnection

    public static DatabaseConnection getInstance(String value) {
        if(databaseConnection == null) {
            databaseConnection = new DatabaseConnection(value);
        }
        return databaseConnection;
    }
}
