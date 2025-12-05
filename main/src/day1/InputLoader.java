package day1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputLoader {
    public static List<String> loadInputs() throws IOException, URISyntaxException {
        var url = InputLoader.class.getResource("/src/main/resources/input.txt");
        if (url == null) {
            throw new FileNotFoundException("Resource not found: /src/main/resources/input.txt");
        }
        Path path = Path.of(url.toURI());
        return Files.readAllLines(path, StandardCharsets.UTF_8).stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}
