package overloading;

public class A {
    int doSomething() {
        System.out.println("Do Something Parameters");
        return 10;
    }

    String doSomething(String a) {
        System.out.println("Do Something Parameters : " + a);
        return a;
    }
}
