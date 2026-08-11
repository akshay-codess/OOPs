package designPatterns.singleton;

import designPatterns.singleton.singleThreaded.DatabaseConnection;

public class Client {
    public static void main(String[] args) {
        DatabaseConnection db = DatabaseConnection.getInstance("Akshay");
        DatabaseConnection db2 = DatabaseConnection.getInstance("Rohit");
//        DatabaseConnection db3 = new DatabaseConnection("Akshay Kanpur")
//        DatabaseConnection.databaseConnection = db2;
        System.out.println(db.value);
        System.out.println(db.value);

    }
}
