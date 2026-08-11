package inhertance;

public class Client {
    public static void main(String[] args) {
        Instructor i = new Instructor();
        i.avgRating = 4.5;
        i.batchName = "JUNE 34";
        i.userName = "Prbahat Sir";
        i.login();
        i.scheduleClass();

        User u = new User();
        u.userName = "Akshay Akshay";
        u.login();

    }
}
