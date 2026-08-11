package constructors;

public class Student {
    String name;
    double  psp;
    int age;
    String universityName;

    // no default constructor will be used
    private Student(String studentName, String uniName) {
        System.out.println(age);
        name = studentName;
        universityName = uniName;
        age = 31;
        System.out.println(age);
    }
}