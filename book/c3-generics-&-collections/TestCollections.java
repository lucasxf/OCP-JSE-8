import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        // but both will throw an "UnsupportedOperationException"
        // nameList.add(0, "Skywalker");
        // nameList.add("Luke");
        nameList.set(0, "Skywalker"); // [Skywalker, Lucas Xavier]
        
        // both will print: "[Skywalker, Lucas Xavier]"
        // because updating the nameList, will also
        // update the array it is backed by:
        System.out.println(nameList); // 
        for ( String name : nameArray) {
            System.out.print(String.format("%s, ", name));
        }
        System.out.println("");
        int[] numbers = { 2, 8, 4, 6, 10 };
        List<Integer> numberList = Arrays.asList(3, 9, 1, 5, 7);
        Arrays.sort(numbers);
        Collections.sort(numberList);
        // prints: 2, 4, 6, 8, 10
        for ( int number : numbers) {
            System.out.print(number + ", ");
        }
        // prints: [1, 3, 5, 7, 9]
        System.out.println("\n" + numberList);
    }

    void testLists() {
        
    }
}