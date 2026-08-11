package designPatterns.builder;

public class Student {
    String name;
    int age;
    double psp;
    String universityName;
    String batch;
    long id;
    int gradYear;
    String phoneNumber;
// Not good approach
//    Student(String name, int age, ......) {
//
//    }

    public static Builder getBuilder() {
        return new Builder();
    }
    private Student(Builder builder) {

        this.name = builder.getName();
        this.gradYear = builder.getGradYear();
        this.age = builder.getAge();
        this.batch = builder.getBatch();
        this.id = builder.getId();
    }

    public static class Builder {
        private String name;
        private int age;
        private double psp;
        private String universityName;
        private String batch;
        private long id;
        private int gradYear;
        private String phoneNumber;

        // getters
        public double getPsp() {
            return psp;
        }
        public int getGradYear() {
            return gradYear;
        }
        public int getAge() {
            return age;
        }
        public long getId() {
            return id;
        }
        public String getPhoneNumber() {
            return phoneNumber;
        }
        public String getBatch() {
            return batch;
        }
        public String getName() {
            return name;
        }
        public String getUniversityName() {
            return universityName;
        }
        // setters
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }
        public Builder setBatch(String batch) {
            this.batch = batch;
            return this;
        }
        public Builder setGradYear(int gradYear) {
            this.gradYear = gradYear;
            return this;
        }
        public Builder setId(long id) {
            this.id = id;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder setPsp(double psp) {
            this.psp = psp;
            return this;
        }
        public Builder setUniversityName(String universityName) {
            this.universityName = universityName;
            return this;
        }

        public Student build() {
            // validation
            if(getGradYear() > 2022) {
                throw new IllegalArgumentException("Grad year can't be greater that 2022 ");
            }
            // validation ends
            return new Student(this);
        }
    }


}
