import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lucas Xavier Ferreira
 * 
 */
public class TestCollections {

    public static void main (String[] args) {
        TestCollections test = new TestCollections();
        test.testArrays();
    }

    void testArrays() {
        String[] nameArray = { "Lucas", "Xavier", "Ferreira"};
        List<String> nameList = Arrays.asList(nameArray); // [Lucas, Xavier, Ferreira]
        List<String> listOfNames = Arrays.asList("Lucas", "Xavier", "Ferreira"); // [Lucas, Xavier, Ferreira]
        // compiler error: array is of type String and list is of type Integer
        // List<Integer> crazyList = Arrays.asList(nameArray);

        // prints: "[Lucas, Xavier, Ferreira]"
        System.out.println(nameList);
        System.out.println(listOfNames);
        
        // prints "Lucas, Xavier, Ferreira,"
        for ( String name : nameArray) {
            System.out.print(String.format("%s, ", name));
        }
        System.out.println("");
        Arrays.sort(nameArray); // nameArray = { Ferreira, Lucas, Xavier }
        // prints "Ferreira, Lucas, Xavier,"
        for ( String name : nameArray) {
            System.out.print(String.format("%s, ", name));
        }
        // now it prints: "[Ferreira, Lucas, Xavier]", since this list is backed by
        // nameArray object, and it was sorted.
        System.out.println(nameList);

        // if ran independently, both should remove "Lucas"
        // but both will throw an "UnsupportedOperationException"
        // because Arrays.asList() returns a fixed-size list.
        // nameList.remove(1); // index overload
        // nameList.remove("Lucas"); // object overload

    }

    void testLists() {
        
    }
}