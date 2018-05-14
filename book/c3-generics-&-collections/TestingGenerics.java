import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas Xavier Ferreira
 * 
 * 14.05.2018
 */
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
        Scissor tesoura = new Scissor();
        DuctTape silverTape = new DuctTape();
        Drawer<Scissor> gavetaTesouras = new Drawer();
        gavetaTesouras.putItem(tesoura);
        System.out.println(gavetaTesouras); // prints: "guardei um(a) tesoura afiada"

        Drawer<DuctTape> gavetaDurex = new Drawer();
        gavetaDurex.putItem(silverTape); 
        System.out.println(gavetaDurex); // prints: "guardei um(a) durex monstro"
        ShoppingCart<Scissor> carrinho = new ShoppingCart<>();

        boolean testInstance = carrinho instanceof Sellable; // true
        System.out.println(testInstance);
        testInstance = gavetaDurex.getItem() instanceof DuctTape;
        
        System.out.println(testInstance);
    }
}

class ShoppingCart<T> implements Sellable<T> {
    T product;
    @Override
    public void sell(T t) {
    }
    @Override
    public BigDecimal getPrice() {
        return null;
    }
} 
interface Sellable<T> {
    void sell(T t);
    BigDecimal getPrice();
}
class Scissor implements Sellable<Scissor> {
    
    // throws Arithmetic exception
    // private BigDecimal price = new BigDecimal(1.99).setScale(2);
    private BigDecimal price = new BigDecimal(1.99);

    @Override
    public String toString() {
        // also throws ArithmeticException (rounding necessary)
        // price.setScale(2);
        return "tesoura afiada";
    }
    @Override
    public void sell(Scissor t) {
         System.out.println("Sold for: " + t.getPrice());
    }
    public BigDecimal getPrice() {
        return price;
    }
}

class DuctTape {
    @Override
    public String toString() {
        return "durex monstro";
    }
}

class Drawer<T> {
    
    private T item;

    public T getItem() {
        return item;
    }

    // compiler error: T could be of a different type
    // public <T> T tradeItem(T oldItem) {
        // return item;
    // }

    public void putItem(T item) {
        this.item = item;
    }
    
    @Override
    public String toString() {
        return "Guardei um(a) " + item;
    }
}