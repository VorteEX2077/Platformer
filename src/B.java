public class B extends A{
    B() {
        super();
        System.out.println("B Construcotr");
    }
}

class Main {
    public static void main(String[] args) {
        new B();
    }
}
