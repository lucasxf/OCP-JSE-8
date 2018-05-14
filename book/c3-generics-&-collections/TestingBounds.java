import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

interface Flyable { }

class Plane implements Flyable {
    @Override
    public String toString() {
        return "avião";
    }
 }

class Jet extends Plane {
    @Override
    public String toString() {
        return "jatinho";
    }
 }

 class Concorde extends Jet {
    @Override
    public String toString() {
        return super.toString() + " supersônico";
    }
 }
 
 class A { }
 class B extends A { }
 class C extends B { }

public class TestingBounds {

    public static void main( String[] args) {
        TestingBounds testingBounds = new TestingBounds();
        testingBounds.testArraysNLists();
        testingBounds.testUpperBounds();
        testingBounds.testLowerBounds();
        testingBounds.evilWorkingMethod(null);
        testingBounds.evilWorkingMethod2(null);
        testingBounds.evilWorkingMethod3(null);
    }

    // compiles and runs fine
    <B extends A> B evilWorkingMethod(List<B> list) {
        System.out.println("tricky 1");
        return null;
    }
    
    // compiles and runs fine
    <X extends A> B evilWorkingMethod2(List<B> list) {
        System.out.println("tricky 2");
        return new B();
    }

    // compiles and runs fine
    <X extends A> B evilWorkingMethod3(List<B> list) {
        System.out.println("tricky 3");
        return new C();
    }

    // won't compile because B here is the type parameter, not class B
    // <B extends A> B reallyTrickyMethod(List<B> list) {
    //     return new B();
    // }

    // regular perfect normal generic method
    <T> T genericMethod (List<? extends T> list) {
        return list.get(0);
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

    void testLowerBounds() {
        List<Plane> avioes = new ArrayList<>();

        avioes.add(new Concorde());
        avioes.add(new Plane());
        avioes.add(new Jet());
        System.out.println(avioes); // [jatinho supersônico, avião, jatinho]

        // compiles and runs without errors
        List<? super Concorde> hangar = new ArrayList<>();
        List<? super Concorde> hangar2 = new ArrayList<Concorde>();
        List<? super Concorde> hangar3 = new ArrayList<Plane>();
        List<? super Concorde> hangar4 = new ArrayList<Flyable>();

        // compiles and runs ok
        hangar2.add(0, new Concorde());
        hangar3.add(0, new Concorde());
        hangar4.add(0, new Concorde());

        // the following 3 statements will cause compiler errors
        // hangar.add(new Jet()); -> A "Jet" wouldn't "fit" in a "Concorde" List
        // hangar3.add(0, new Plane());
        // hangar4.add(0, new Jet());

        // works just fine. Lower bounded lists can grow
        hangar.add(0, new Concorde());
        hangar.add(0, new Concorde());
        System.out.println(hangar); // [jatinho supersônico, jatinho supersônico]

        hangar = new ArrayList(avioes);
        System.out.println(hangar); // [jatinho supersônico, avião, jatinho]
        // won't compile. Since Plane doesn't implement Comparable
        // Collections.sort(hangar);


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