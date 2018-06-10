import java.util.Collection;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

/**
 * About Collections:
 * List: an ordered collection of elements that allows duplicate entries.
 * Set: a collection that does not allow duplicate entries.
 * Queue: a collection that orders its elements in a specific order for processing. Typically FIFO.
 * Map: Map is special, because it's a collection that doesn't extend Collection.
 * It maps keys to values, with no duplicate keys allowed. The elements in a map are key/value pairs.
 * 
 * @author Lucas Xavier Ferreira
 * 
 */
public class TestCollections {

    public static void main (String[] args) {
        TestCollections test = new TestCollections();
        // test.testArrays();
        // test.testLists();
        // test.testCollections();
        // test.testQueues();
        test.testMaps();
    }

    // map <K, V> (key, value)
    void testMaps() {
        // stores the keys in a hash table (uses hashCode() to retrieve them)
        Map<String, Long> hashMap = new HashMap<>();
        
        // stores the keys in a sorted tree (adding and checking keys takes O(log n) time)
        Map<String, Integer> treeMap = new TreeMap<>();

        // V - adds or replaces key/value pair. return previous value or null
        Long value = hashMap.put("Lucas", 11982546774L); // {Lucas, 11982546774}
        System.out.println(hashMap + " " + value); // prints: "{Lucas, 11982546774} null"
        
        value = hashMap.put("Lucas", 123L);
        System.out.println(hashMap + " " + value); // prints: "{Lucas, 123} 11982546774"

        value = hashMap.get("Lucas");
        System.out.println(value); // 123

        try {
            // returns null since there's no "abc" key
            // and then throws NPE (NullPointerException)
            // since primitives can't be null:
            long x = hashMap.get("abc");   
            System.out.println(x);
        } catch (Exception e) {
            e.printStackTrace(); // NPE
        }
        hashMap.clear(); // {}
        System.out.println(hashMap); // prints: "{}"
        String alpha = "";
        for ( int i = 1; i <= 26; i++) {
            String s = "" + (char) (i + 64);
            alpha += s;
            hashMap.put(s, i+0L);
        }
        System.out.println(alpha); // the alphabet
        System.out.println(hashMap); // prints: "{A=1, B=2, C=3, D=4, ..., Z=26}"

        boolean containsKey = hashMap.containsKey("a"); // false
        int n = 1;
        boolean containsValue = hashMap.containsValue(n); // should print false because it stores Long values, not ints
        System.out.println(containsKey + " | " + containsValue); // prints: "false | false"
        // returns a Set of K type:
        Set<String> mapKeys = hashMap.keySet();
        // returns a Collection of V type:
        Collection mapValues = hashMap.values();
        
        // doesn't compile because the map keys are of type String, not Boolean
        // Set<Boolean> crazyKeys = hashMap.keySet();
        
        // doesn't compile because the map values are of type Long, not String
        // Collection<String> crazyValues = hashMap.values();

        String alphabet = mapKeys.toString();
        System.out.println(alphabet); // prints: "[A, B, C, D, E, ..., Z]"
        System.out.println(mapValues); // prints: "[1, 2, 3, 4, 5, ..., 26]"
        
        // returns the previous value associated with the key if the presented value is absent, or null if not
        Long isAbsent = hashMap.putIfAbsent("a", 1L);
        System.out.println("same value, different key: " + isAbsent); // null
        isAbsent = hashMap.putIfAbsent("A", 1L);
        System.out.println("same key, same value: " + isAbsent); // 1
        isAbsent = hashMap.putIfAbsent("A", 99L);
        System.out.println("same key, different value: " + isAbsent); // 1
        isAbsent = hashMap.putIfAbsent("a", 123L);
        System.out.println("same key, different value: " + isAbsent); // 1
        System.out.println(hashMap);

        // if v1 is even, return v1. if v2 is even, return v2. if none of them are even, return 1K
        BiFunction<Long, Long, Long> isEven = (v1, v2) -> v1 %2 == 0 ? v1 : v2 %2 == 0 ? v2 : 1000L;
        Long testMerge = hashMap.merge("a", 999L, isEven); // returns 1000
        System.out.println(hashMap);
        System.out.println(testMerge);
        testMerge = -1L;
        hashMap.put("a", 123L);
        testMerge = hashMap.merge("a", 12L, isEven); // returns 12
        System.out.println(testMerge);
        // throws NPE
        // isEven.andThen(null);
        System.out.println(isEven.apply(10l, 15l)); // 10
        System.out.println(isEven.apply(25l, 15l)); // 1000
        System.out.println(isEven.apply(40l, 20l)); // 40
        System.out.println(isEven.apply(15l, 250l)); // 250

        Map<String, String> names = new HashMap<>();
        names.put("Lucas", "Xavier");
        names.put("Marcia", null);
        System.out.println(names); // "{Lucas=Xavier, Marcia=null}"
        // insted of throwing nullPointer exception at null.contains, this will return the value "Inez"
        String surname = names.merge("Marcia", "Inez", (v1, v2) -> v1.contains("z") ? v1 : v2);
        System.out.println(surname); // "Inez"
        System.out.println(names); // "{Lucas=Xavier, Marcia=Inez}"
        names.putIfAbsent("Thomas", "Gabriel"); // "{Lucas=Xavier, Marcia=Inez, Thomas=Gabriel}"
        System.out.println(names);

         // returns Ferrreira because its length is bigger than Xavier
        names.merge("Lucas", "Ferreira", (v1, v2) -> v1.length() > v2.length() ? v1 : v2);
        System.out.println(names); // "{Lucas=Ferreira, Marcia=Inez, Thomas=Gabriel}"

        // this will remove the key "Lucas" from the map.
        // it happens because the biFunction states that if the value in key "Lucas"
        // isn't null, it should return null, otherwise it would return "999"
        names.merge("Lucas", "999", (v1, v2) -> v1 != null ? null : v2);
        System.out.println(names);

        // this will add a key "Lucas" of value "999" because the test within
        // the bifunction will assess to false, so the value "999" will be returned
        // and a new key will be added to the map
        names.merge("Lucas", "999", (v1, v2) -> v1 != null ? null : v2);
        names.merge("Felipe", "XF", (v1, v2) -> v1 != null ? null : v2);
        System.out.println(names); // {Felipe=XF, Lucas=999, Marcia=Inez, Thomas=Gabriel}
        
        BiFunction<String, String, String> presentBiFunction = (s1, s2) -> s1 != null ? "Skywalker" : s1;
        // since there's no "Luke" key yet, this will return null
        String absentValue = names.computeIfPresent("Luke", presentBiFunction); // {Felipe=XF, Lucas=999, Marcia=Inez, Thomas=Gabriel}
        System.out.println(absentValue); // null, because there's no "Luke" key to be computed
        System.out.println(names); // {Felipe=XF, Lucas=999, Marcia=Inez, Thomas=Gabriel}

        // this will add the value "Anakin" to the absent (that will be added) key "Luke"
        Function<String, String> absentFunction = key -> "Anakin";
        absentValue = names.computeIfAbsent("Luke", absentFunction); // returns "Anakin"
        System.out.println(absentValue); // Anakin
        System.out.println(names); // {Felipe=XF, Lucas=999, Marcia=Inez, Luke=Anakin, Thomas=Gabriel}
        names.compute("Anakin", presentBiFunction);
        System.out.println(names);
    }

    void testQueues() {
        // Array Deque is an List and 
        ArrayDeque<String> deck = new ArrayDeque<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        try {
            // will throw an exception (NoSuchElementException) because the queue is empty
            // returns next element (if not empty), otherwise, throws exception
            String s = deck.element();
            System.out.println("element: " + s);
        } catch (Exception e) {
            System.out.println("trying to get an element from an empty array deque:\n");
            e.printStackTrace();
        }
        // boolean, inserts at end
        boolean insertAtBack = deck.add("A"); // true / [A]
        System.out.println(insertAtBack + ": " + deck);
        deck.add("B"); // [A, B]
        String c = deck.offer("C")+ "x"; // true / [A, B, C]
        System.out.println(insertAtBack + ": " + deck + c); // prints: true: [A, B, C]truex
        // E - removes next element or throws an exception if emtpy queue
        String removedItem = deck.remove(); // A / [B, C]
        System.out.println(deck + " " + removedItem);
        deck.add("D");
        deck.add("E");
        deck.add("F"); // [B, C, D, E, F]

        removedItem = deck.removeLast(); // F / [B, C, D, E]
        System.out.println(removedItem + " - " + deck);
        // void - inserts at front
        deck.push("A"); // [A, B, C, D, E]
        deck.push("Z"); // [Z, A, B, C, D, E]
        System.out.println(deck);
        
        // E - removes and returns next element or returns null if empty queue
        System.out.println(deck.poll()); // Z - [A, B, C, D, E]
        
        System.out.println(deck); // [A, B, C, D, E]
        
        // returns next element or null if empty queue
        System.out.println(deck.peek()); // A - [A, B, C, D, E]

        // removes and returns next element or throws and exception if emtpy queue
        System.out.println(deck.pop()); // A - [B, C, D, E]
        System.out.println(deck.pop()); // B - [C, D, E]
        System.out.println(deck); // [C, D, E]
        deck.remove(); // [D, E]
        deck.remove(); //[E]
        System.out.println(deck);
        deck.remove(); // []
        System.out.println(deck);
        try {
            deck.remove(); // throws an exception (NoSuchElementException)
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            lower2, lower3, lower4, lower5, lower6, lower7));
        
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