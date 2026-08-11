package designPatterns.prototype;

public class IntelligentStudent extends Student {
    int iq;

    public IntelligentStudent() {
    }

    public IntelligentStudent(IntelligentStudent student) {
        super(student);
        this.iq = student.iq;
    }

    public IntelligentStudent clone() {
//        IntelligentStudent is = new IntelligentStudent();
//        is.iq = 198;
        return new IntelligentStudent(this);
    }
}
