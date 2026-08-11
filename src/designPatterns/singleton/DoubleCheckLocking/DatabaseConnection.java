package designPatterns.singleton.DoubleCheckLocking;

public class DatabaseConnection {
    private static DatabaseConnection databaseConnection = null;

    private DatabaseConnection(String env) {};  // privateConnection

    public static DatabaseConnection getInstance(String env) {
        if(databaseConnection == null) {
            synchronized (DatabaseConnection.class) {
                if(databaseConnection == null) {
                    databaseConnection = new DatabaseConnection(env);
                }
            }
        }
        return databaseConnection;
    }
}
