import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Lucas
 */
public class Chapter9NIO2 {

    public static void main(String[] args) {
        Chapter9NIO2 test = new Chapter9NIO2();
        test.testPaths();
    }

    void testPaths() {
        Path path = Paths.get("c:", "test.txt");
        System.out.println(path); // prints "c:\test.txt"

        Path path2 = Paths.get("c:", "dummy"); // prints "c:\dummy" even though dummy doesn't exist.
        System.out.println(path2); 
    }

}