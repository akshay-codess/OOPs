package designPatterns.builder;

public class Client {
    public static void main(String[] args) {
//        Builder builder = Student.getBuilder();
//        builder.setAge(21);
//        builder.setBatch("20456335");
//        builder.setGradYear(204);
//        builder.setId(4_634_563);
//        builder.setName("Akshay");
//        Student st = new Student(builder);

          Student st = Student.getBuilder()
                  .setAge(21)
                  .setBatch("20456335")
                  .setGradYear(204)
                  .setId(4_634_563)
                  .setName("Akshay")
                  .build();


        System.out.println("DEBUG");
    }
}
