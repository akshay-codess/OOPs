package designPatterns.singleton.EagerLoading;

public class DatabaseConnection {
    private static DatabaseConnection databaseConnection = new DatabaseConnection();

    private DatabaseConnection() {};  // privateConnection

    public static DatabaseConnection getInstance() {
        return databaseConnection;
    }
}
