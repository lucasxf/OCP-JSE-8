import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Car {  }

class Ferrari extends Car {
    
    @Override
    public String toString() {
        return "Ferrari Modena";
    }
}
class Porsche extends Car {
    @Override
    public String toString() {
        return "Porsche Cayenne S";
    }
}
public class TestingBounds {

    public static void main( String[] args) {
        TestingBounds testingBounds = new TestingBounds();
        testingBounds.testArraysNLists();
        testingBounds.testUpperBounds();
    }

    void testUpperBounds() {
        // won't compile, incompatible types.
        // List<Car> cars = new ArrayList<Ferrari>();
        List<? extends Car> cars = new ArrayList<>(); // [Ferrari Modena]
        // even though both Ferrari and Porsche are Car subclasses, 
        // this won't compile because upper bounded or unbounded wildcards
        // makes the list logically immutable, so it cannot "grow", it 
        // could "shrink", however, had it had any elements.
        // cars.add(new Porsche());
        // cars.add(new Ferrari());

        // works just fine and prints: "[Porsche Cayenne S, Ferrari Modena, Porsche Cayenne S]"
        cars = Arrays.asList(new Porsche(), new Ferrari(), new Porsche());
        System.out.println(cars);

        // false because it's trying to remove a new Porsche object
        // not the very same within the list
        boolean canRemoveAPorsche = cars.remove(new Porsche());
        System.out.println(canRemoveAPorsche); // false
        // throws UnsupportedOperationException
        // because Arrays.asList returns immutable list
        // canRemoveAPorsche = (cars.remove(0) instanceof Porsche);
        System.out.println(cars); // "[Ferrari Modena, Porsche Cayenne S]"

        List<Car> carros = new ArrayList<>();
        carros.add(new Porsche());
        carros.add(new Ferrari());
        carros.add(new Porsche());
        carros.add(new Ferrari());

        List<? extends Car> garagem = carros;
        List<? extends Car> garage = new ArrayList<>(carros);
        System.out.println(garagem);
        System.out.println(garage);

        // works just fine, an upper-bounded list can lose elements
        garage.remove(0);
        System.out.println("After removing:");
        System.out.println(garage); // [Porsche, Ferrari, Porsche]
        System.out.println(carros);  // [Porsche, Ferrari, Porsche, Ferrari] 
        System.out.println(garagem); // [Porsche, Ferrari, Porsche, Ferrari] 

        // compiler error, upper-bounded lists cannot grow.
        // garage.add(new Porsche());
        // garage.add(new Porsche());
    }

    void testArraysNLists() {
        // doesn't compile, because otherwise we would be able to:
        // List<Object> l = new List<Integer>();
        // l.add(1);
        // List<String> ls = new List<>();
        // l = ls;
        // ls.add("crazy casting");
        // List<Object> crazyNumbers = new List<Integer>();

        // compiles, but throws runtime exceptions:
        Object[] numbers = new Integer[3]; // ugly, but ok
        Integer[] inteiros = { 1, 2, 3}; // reglular array
        numbers = inteiros; // also ok
        // throws ArrayStoreException
        // numbers[0] = "123"; // not ok
    }
}