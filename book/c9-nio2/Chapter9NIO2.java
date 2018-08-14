import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

/**
 * @author Lucas
 */
public class Chapter9NIO2 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        Chapter9NIO2 test = new Chapter9NIO2();
        test.testPaths();
    }

    void testPaths() throws URISyntaxException, IOException {
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
        
        FileSystem fileSystem = FileSystems.getDefault();
        System.out.println(fileSystem); // prints "sun.nio.fs.WindowsFileSystem@7d4991ad"

        Path path6 = fileSystem.getPath("c:", "test.txt");
        System.out.println(path6); // prints "c:\test.txt"

        // code for remote file systems
        // throws Exception in thread "main" java.nio.file.ProviderNotFoundException: Provider "http" not found
        // FileSystem remoteSystem = FileSystems.getFileSystem(new URI("http://some-remote-file-system.com"));

        File file = new File("c:\test.txt");
        System.out.println(file); // prints "c:    est.txt" (\t = tab)
        // throws "Exception in thread "main" java.nio.file.InvalidPathException: Illegal char <   > at index 2: c:        est.txt"
        // Path path7 = file.toPath();
        // System.out.println(path7);

        File file2 = new File("c://test.txt");
        System.out.println(file2); // prints "c:\test.txt"
        Path path8 = file2.toPath();
        System.out.println(path8); // prints "c:\test.txt"

        File file3 = new File("dummy");
        System.out.println(file3); // prints "dummy"
        Path path9 = file3.toPath();
        System.out.println(path9); // prints "dummy"

        File file4 = new File("c:/test.txt");
        System.out.println(file4); // prints "c:\test.txt"
        Path path10 = file4.toPath();
        System.out.println(path10); // prints "c:\test.txt"
        File file5 = path10.toFile();
        System.out.println(file5); // prints "c:\test.txt"

        Path path11 = path10.getFileName();
        System.out.println(path11); // prints "test.txt"
        Path path12 = path10.getParent();
        System.out.println(path12); // prints "c:\"
        Path path13 = path10.getRoot();
        System.out.println(path13); // prints "c:\"
        Path path14 = path10.normalize();
        System.out.println(path14); // prints "c:\test.txt"

        int nameCount = path10.getNameCount();
        System.out.println(nameCount); // prints 1

        Path path15 = Paths.get("C:", "2nd", "3rd", "4th", "and so on");
        nameCount = path15.getNameCount();
        System.out.println(nameCount); // prints 4 (the root isn't considered a name)
        System.out.println(path15); // prints "c:\2nd\3rd\4th\and so on"
        System.out.println(path15.getName(3)); // prints "and so on" because its index is 0 based [0 = "2nd", 1 = "3rd", 2 = "4th", 3 = "and so son"]
        System.out.println(path15.isAbsolute()); // prints "true"
        System.out.println(path15.toAbsolutePath()); // since it's already an absolute path, prints "itself", meaning: "c:\2nd\3rd\4th\and so on"
        
        Path path16 = Paths.get("C:");
        System.out.println(path16); // prints "c:"
        System.out.println(path16.getParent()); // prints "null", as root has no parents
        System.out.println(path16.getRoot()); // prints "c:" as roots root is itself
        System.out.println(path16.getNameCount()); // prints "0"
        System.out.println(path16.isAbsolute()); // prints "false"

        Path path17 = path16.toAbsolutePath();
        System.out.println(path17.isAbsolute()); // it must print "true"
        System.out.println(path17); // it printed the path where the class was at the time this program was run: "C:\Users\lucas\repo\OCP\OCP-JSE-8\book\c9-nio2\"

        Path path18 = path15.subpath(0, 3);
        System.out.println(path18);
        // throws "Exception in thread "main" java.lang.IllegalArgumentException"
        // System.out.println(path15.subpath(1, 1));

        // Files.delete(Paths.get("c:", "xpto")); // throws NoSuchFileException (subtype of IOException)
        boolean exists = Files.deleteIfExists(Paths.get("c:", "xpto"));
        System.out.println(exists); // prints "false"

        Path filePath = Paths.get("c:", "test.txt");
        try ( BufferedReader reader = Files.newBufferedReader(filePath, Charset.defaultCharset())) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    interface MyInterface {
        String getStr();
    }

    enum MyEnum implements MyInterface {
        
        TEST;

        @Override
        public String getStr() {
            return TEST.name();
        }
    }
}