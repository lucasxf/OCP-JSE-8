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
        test.testLists();
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
        // some binary searching:
        int firstPosition = Collections.binarySearch(numberList, 1); // 0
        int middlePosition = Collections.binarySearch(numberList, 5); // 2
        int lastPosition = Collections.binarySearch(numberList, 9); // 4
        int noPosition = Collections.binarySearch(numberList, 10); // -6 

        // prints "0, 2, 4, -6"
        System.out.println(String.format("%s, %s, %s, %s",
            firstPosition, middlePosition, lastPosition, noPosition));

    }

    void testLists() {
        List<Integer> integers = new ArrayList<>();
        // throws IndexOutOfBoundsException
        // integers.add(10, 99);
        integers.add(new Integer(1)); // [1]
        integers.add(2); // [1, 2]
        integers.add(new Integer(99)); // [1, 2, 99]
        integers.add(2, 123); // [1, 2, 123, 99]
        integers.add(0, 11); // [11, 1, 2, 123, 99]
        // throws IndexOutOfBoundsException
        // integers.remove(11);
        // runs fine thanks to autoboxing
        boolean hasRemoved = integers.remove(new Integer(11)); // true | [1, 2, 3, 99]
        System.out.println(hasRemoved);
        hasRemoved = integers.remove(new Integer(425)); // false | [1, 2, 3, 99] 
        System.out.println(hasRemoved);
        System.out.println(integers);
    }
}