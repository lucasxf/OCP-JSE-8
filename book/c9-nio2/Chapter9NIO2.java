import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Lucas
 */
public class Chapter9NIO2 {

    public static void main(String[] args) throws URISyntaxException {
        Chapter9NIO2 test = new Chapter9NIO2();
        test.testPaths();
    }

    void testPaths() throws URISyntaxException {
        Path path = Paths.get("c:", "test.txt");
        System.out.println(path); // prints "c:\test.txt"
        URI uri = path.toUri();
        System.out.println(uri); // prints "file:///c:/test.txt"

        Path path2 = Paths.get("c:", "dummy");
        System.out.println(path2); // prints "c:\dummy" even though dummy doesn't exist.
        URI uri2 = path2.toUri();
        System.out.println(uri2); // prints "file:///c:/dummy"

        Path path3 = Paths.get(new URI("file:///dummy"));
        System.out.println(path3); // should print "/dummy", prints "\dummy"
        // Path path4 = Paths.get(new URI("file://dummy")); throws URISyntaxException (paths must be absolute)
        
        String separator = System.getProperty("path.separator");
        System.out.println(String.format("separador: \"%s\"", separator)); // should print "\", actually prints ";"

        // Path path5 = Paths.get(new URI("https://www.google.com")); throws "Exception in thread "main" java.nio.file.FileSystemNotFoundException: Provider "https" not installed"
        // System.out.println(path5);

    }

}