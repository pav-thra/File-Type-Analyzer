import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Callable;

public class FileSearchTask implements Callable<PatternMatchResult> {
    private final File file;
    private final List<Pattern> patterns;

    public FileSearchTask(File file, List<Pattern> patterns) {
        this.file = file;
        this.patterns = patterns;
    }

    @Override
    public PatternMatchResult call() {
        try {
            byte[] data = readFileToByteArray(file);
            String fileContent = new String(data, StandardCharsets.UTF_8);
            Pattern bestMatch = null;

            for (Pattern pattern : patterns) {
                if (fileContent.contains(pattern.getSignature())) {
                    if (bestMatch == null || pattern.getId() > bestMatch.getId()) {
                        bestMatch = pattern;
                    }
                }
            }

            return new PatternMatchResult(file.getName(), bestMatch);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] readFileToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            return data;
        }
    }
}
