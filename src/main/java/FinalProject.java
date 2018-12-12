import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FinalProject {

    public static void main(String[] argv) {
        Path path = null;
        try {
            path = Paths.get(FinalProject.class.getClassLoader()
                    .getResource("DBScript.sql").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Stream<String> lines = null;
        try {
            lines = Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();

        System.out.println(data);
    }
}
