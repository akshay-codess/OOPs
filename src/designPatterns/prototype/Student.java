package designPatterns.prototype;

public class Student {
    private String name;
    private int age;

    private double studentPsp;
    private String batch;
    private double averageBatchPsp;

    public Student() {}

    public Student(Student student) {
        this.age = student.age;
        this.name = student.name;
        this.batch = student.batch;
        this.studentPsp = student.studentPsp;
        this.averageBatchPsp = student.averageBatchPsp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getStudentPsp() {
        return studentPsp;
    }

    public void setStudentPsp(double studentPsp) {
        this.studentPsp = studentPsp;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public double getAverageBatchPsp() {
        return averageBatchPsp;
    }

    public void setAverageBatchPsp(double averageBatchPsp) {
        this.averageBatchPsp = averageBatchPsp;
    }

    public Student clone() {
//        Student student = new Student();
////        student.name = this.name;
////        student.age = this.age;
//        student.batch = this.batch;
//        student.averageBatchPsp = this.averageBatchPsp;
        return new Student(this);
    }
}
