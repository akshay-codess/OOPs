package designPatterns.prototype;

public class Client {
    private static void fillRegister(StudentRegistry studentRegistry) {
        Student febBatchStudent = new Student();
        febBatchStudent.setBatch("Feb 22 LLD");
        febBatchStudent.setAverageBatchPsp(89);

        studentRegistry.register("Feb 22 LLD", febBatchStudent);

        IntelligentStudent febBatchIntelligentStudent = new IntelligentStudent();
        febBatchIntelligentStudent.iq = 180;
        febBatchIntelligentStudent.setBatch("Feb 22 LLD intelligent");
        febBatchIntelligentStudent.setAverageBatchPsp(89);

        studentRegistry.register("Feb 22 LLD intelligent", febBatchIntelligentStudent);
    }
    public static void main(String[] args) {
        StudentRegistry studentRegistry = new StudentRegistry();
        fillRegister(studentRegistry);

        Student aakash = studentRegistry.get("Feb 22 LLD").clone();
        aakash.setAge(23);
        aakash.setName("aakash");
        aakash.setStudentPsp(100);

        Student devdutt = studentRegistry.get("Feb 22 LLD intelligent").clone();
        devdutt.setName("Devdutt");
        devdutt.setStudentPsp(99);
        System.out.println("DEPUG");

    }
}
