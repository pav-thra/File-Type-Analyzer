import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatternFileReader {
    public static List<Pattern> readPatterns(String filePath) throws IOException {
        List<Pattern> patterns = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    int priority = Integer.parseInt(parts[0]);
                    String pattern = parts[1].replaceAll("^\"|\"$", "");
                    String description = parts[2].replaceAll("^\"|\"$", "");
                    patterns.add(new Pattern(priority, pattern, description));
                }
            }
        }
        return patterns;
    }
}
