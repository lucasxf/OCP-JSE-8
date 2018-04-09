class TestInnerClasses {

    public static void main ( String[] args ) {
        
        InnerClassB innerD = new InnerClassD();
        // won't compile.
        // innerD.printName(new InnerClassC().innerName);
    }

    /*
    * Member Inner Classes
    */
    public class InnerClassA {
        // illegal, won't compile.
        // member inner classes cannot have static fields nor methods
        // static int a = 0;
        // private static void doStuff() { }
    }

    // legal. Member inner classes can be abstract.
    protected class InnerClassB {
        private String innerName = "Inner Class B";
    }

    // legal. Member inner classes can be final.
    final class InnerClassC extends InnerClassB {

    }

    // legal. Inner classes can have all 4 access levels (public, protected, default or private)
    // as well as extend any class and implement any interfaces
    private class InnerClassD implements InnerTestInterface {
        
        public void printName(String className) {
            System.out.println("Class name is " + className);
        }
    }

}

interface InnerTestInterface {
    void printName(String name);
}