import java.util.ArrayList;
import java.util.List;

public class TestingGenerics {

    public static void main( String[] args) {
        TestingGenerics test = new TestingGenerics();
        test.testLists();
    }

    void testLists() {
        // right now it could add any type of object
        // causes compiler warning
        List nonGenericList = new ArrayList();

        nonGenericList.add("Luke"); // [Luke]
        System.out.println(nonGenericList);

        nonGenericList.add("Skywalker"); // [Luke, Skywalker]
        // kinda crazy, but works
        nonGenericList.add(123); // [Luke, Skywalker, 123]
        
        System.out.println(nonGenericList);
    }
}