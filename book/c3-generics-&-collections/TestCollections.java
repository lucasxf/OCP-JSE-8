import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Collections;
import java.util.List;

/**
 * About Collections:
 * List: an ordered collection of elements that allows duplicate entries.
 * Set: a collection that does not allow duplicate entries.
 * Queue: a collection that orders its elements in a specific order for processing. Typically FIFO.
 * Map: a collection that maps keys to values, with no duplicate keys allowed. The elements in a map are key/value pairs.
 * 
 * @author Lucas Xavier Ferreira
 * 
 */
public class TestCollections {

    public static void main (String[] args) {
        TestCollections test = new TestCollections();
        // test.testArrays();
        // test.testLists();
        test.testCollections();
    }

    void testCollections() {
        String lucas = "Lucas";
        int lucasHash = lucas.hashCode(); // 73771404
        int xavierHash = "Xavier".hashCode(); // -1682400983
        int ferreiraHash = "Ferreira".hashCode(); //258348466 
        int anakinHash = "Anakin".hashCode(); //1965478044
        int skywalkerHash = "Skywalker".hashCode(); // 1320816759
        int lukeHash = "Luke".hashCode(); // 2379971
        int jediHash = "Jedi".hashCode();

        String hashes = String.format(
            "Lucas: %s, Xavier: %s, Ferreira: %s," +
            "%nAnakin: %s, Skywalker: %s, Luke: %s, Jedi: %s.",
            lucasHash, xavierHash, ferreiraHash, anakinHash, 
            skywalkerHash, lukeHash, jediHash);
        
        System.out.println(hashes);
        int age = 24;
        long id = 123456;

        List<String> myNames = Arrays.asList("Lucas", "Xavier", "Ferreira", "Lucas");
        List<String> myLastnames = Arrays.asList("Luke", "Skywalker");
        List<String> repeatedNames = Arrays.asList("Luke", "Xavier", "Anakin", "Ferreira", "Jedi");
        List<String> names = new ArrayList<>();
        Set<String> uniqueNames = new HashSet<>();
        
        boolean addToList = names.add(lucas); // true | [Lucas]
        // prints true
        System.out.println(addToList + " " + names);
        addToList = names.add(lucas); // true | [Lucas, Lucas]
        
        // still prints true, since lists can have duplicate elements
        System.out.println(addToList + " " + names);
        
        // prints true
        boolean addToSet = uniqueNames.add(lucas); // true [Lucas]
        System.out.println(addToSet + " " + uniqueNames); 
        
        // will print false because sets do not allow duplicate entries
        addToSet = uniqueNames.add(lucas); // false [Lucas]
        System.out.println(addToSet + " " + uniqueNames);

        // I thought it'd print false, it prints true though.
        // this happens because part of the collection is added
        // while the duplicate items are ignored.
        // Since at least one element was added, it works.
        addToSet = uniqueNames.addAll(myNames); // true [Xavier, Ferreira, Lucas]
        System.out.println(addToSet + " " + uniqueNames);
        // for some reason the order goes kind of crazy
        // [Xavier, Ferreira, Luke, Lucas, Skywalker]
        addToSet = uniqueNames.addAll(myLastnames); // true [Xavier, Ferreira, Luke, Lucas, Skywalker]
        System.out.println(addToSet + " " + uniqueNames);
        boolean removeFromSet = uniqueNames.removeAll(Arrays.asList("Lucas", "Ferreira"));
        System.out.println(removeFromSet + " " + uniqueNames);
        addToSet = uniqueNames.addAll(myNames);
        System.out.println(addToSet + " " + uniqueNames);
        removeFromSet = uniqueNames.remove("Anakin"); // false, there's no "Anakin" in unique names
        System.out.println(removeFromSet);
        System.out.println("Unique names size: " + uniqueNames.size()); // 5
        addToSet = uniqueNames.addAll(repeatedNames);
        System.out.println(addToSet + " " + uniqueNames);
        // doesn't compile. collections.sort can only be called
        // on a List implementation
        // Collections.sort(uniqueNames);
        Set<Integer> treeSet = new TreeSet<Integer>();
        for ( int i = 0; i < 100; i++ ) {
            treeSet.add(i);
        }
        Integer zero = new Integer(0);
        Integer minusOne = new Integer(-1);
        Integer one = new Integer(1);
        Integer two = new Integer(2);
        Integer ninetyNine = new Integer(99);
        Integer oneHundred = new Integer(100);
        Integer oneOone = new Integer(101);
        
        // this throws a nullpointer exception, because there's no
        // element smaller than 0 in this set, and when navigableSet.lower()
        // can't find an element, it returns null. 
        // Since primitives cannot be assigned to null, it'll throw an exception:
        // int lower1 = (int) ((TreeSet) treeSet).lower(zero);
        // lower returns the greatest element that is < e, or null if no such element
        Integer lower2 = (Integer) ((TreeSet) treeSet).lower(minusOne); // null
        Integer lower3 = (Integer) ((TreeSet) treeSet).lower(one); // 0
        Integer lower4 = (Integer) ((TreeSet) treeSet).lower(two); // 1
        Integer lower5 = (Integer) ((TreeSet) treeSet).lower(ninetyNine); // 98
        Integer lower6 = (Integer) ((TreeSet) treeSet).lower(oneHundred); // 99
        Integer lower7 = (Integer) ((TreeSet) treeSet).lower(oneOone); // 99 (not a hundred because '100' isn't an element of treeSet)
        System.out.println(String.format("lower: %s, %s, %s, %s, %s, %s.", 
            lower2, lower3, lower4, lower5, lower
            6, lower7));
        
        // floor returns the greatest element that is <= e, or null if no such element
        Integer floor1 = (Integer) ((TreeSet) treeSet).floor(zero); // 0
        Integer floor2 = (Integer) ((TreeSet) treeSet).floor(minusOne); // null
        Integer floor3 = (Integer) ((TreeSet) treeSet).floor(one); // 1
        Integer floor4 = (Integer) ((TreeSet) treeSet).floor(two); // 2
        Integer floor5 = (Integer) ((TreeSet) treeSet).floor(ninetyNine); // 99
        Integer floor6 = (Integer) ((TreeSet) treeSet).floor(oneHundred); // 99
        Integer floor7 = (Integer) ((TreeSet) treeSet).floor(oneOone); // 99
        System.out.println(String.format("floor: %s, %s, %s, %s, %s, %s, %s.", 
            floor1, floor2, floor3, floor4, floor5, floor6, floor7));

        // ceiling returns the smallest element that is >= e, or null if no such element
        Integer ceil1 = (Integer) ((TreeSet) treeSet).ceiling(zero); // 0
        Integer ceil2 = (Integer) ((TreeSet) treeSet).ceiling(minusOne); // 0
        Integer ceil3 = (Integer) ((TreeSet) treeSet).ceiling(one); // 1
        Integer ceil4 = (Integer) ((TreeSet) treeSet).ceiling(two); // 2 
        Integer ceil5 = (Integer) ((TreeSet) treeSet).ceiling(ninetyNine); // 99
        Integer ceil6 = (Integer) ((TreeSet) treeSet).ceiling(oneHundred); // null
        Integer ceil7 = (Integer) ((TreeSet) treeSet).ceiling(oneOone); // null
        System.out.println(String.format("ceiling: %s, %s, %s, %s, %s, %s, %s.", 
        ceil1, ceil2, ceil3, ceil4, ceil5, ceil6, ceil7));
        
        // higher returns the smallest element that is > e, or null if no such element
        Integer high1 = (Integer) ((TreeSet) treeSet).higher(zero); // 1
        Integer high2 = (Integer) ((TreeSet) treeSet).higher(minusOne); // 0
        Integer high3 = (Integer) ((TreeSet) treeSet).higher(one); // 2
        Integer high4 = (Integer) ((TreeSet) treeSet).higher(two); // 3 
        Integer high5 = (Integer) ((TreeSet) treeSet).higher(ninetyNine); // null
        Integer high6 = (Integer) ((TreeSet) treeSet).higher(oneHundred); // null
        Integer high7 = (Integer) ((TreeSet) treeSet).higher(oneOone); // null
        System.out.println(String.format("higher: %s, %s, %s, %s, %s, %s, %s.", 
        high1, high2, high3, high4, high5, high6, high7));
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
        // nameList.remove(one); // index overload
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