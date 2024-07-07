import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

class FileSearchTask implements Callable<PatternMatchResult> {
    private final File file;
    private final List<Pattern> patterns;
    private final RabinKarp rabinKarp = new RabinKarp();

    public FileSearchTask(File file, List<Pattern> patterns) {
        this.file = file;
        this.patterns = patterns;
    }

    @Override
    public PatternMatchResult call() {
        try {
            byte[] data = readHeader(file);
            Pattern bestMatch = findBestMatch(data);

            return new PatternMatchResult(file.getName(), bestMatch);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] readHeader(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[Math.min(1024, (int) file.length())];
            fis.read(data);
            return data;
        }
    }

    private Pattern findBestMatch(byte[] fileData) {
        Pattern bestMatch = null;

        for (Pattern pattern : patterns) {
            if (rabinKarp.search(fileData, pattern.getSignature())) {
                if (bestMatch == null || pattern.getId() > bestMatch.getId()) {
                    bestMatch = pattern;
                }
            }
        }

        return bestMatch;
    }
}