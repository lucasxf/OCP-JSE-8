import java.nio.Path;
import java.nio.Paths;

/**
 * @author Lucas
 */
public class Chapter9NIO2 {

    public static void main(String[] args) {
        Chapter9NIO2 test = new Chapter9NIO2();
        test.testPaths();
    }

    void testPaths() {
        Path path = java.nio.file.Paths.get("c:", "test.txt");
        System.out.println(path);
    }

}