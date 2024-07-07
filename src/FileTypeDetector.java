import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileTypeDetector {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the path to your directory:");
        String dirString = sc.nextLine().replace("\"", "");
        File dirPath = new File(dirString);

        List<Pattern> patterns = getPatterns();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try {
            List<Future<PatternMatchResult>> futureResults = new ArrayList<>();
            File[] filesList = dirPath.listFiles();

            if (filesList != null) {
                for (File file : filesList) {
                    if (file.isFile()) {
                        FileSearchTask task = new FileSearchTask(file, patterns);
                        Future<PatternMatchResult> future = executorService.submit(task);
                        futureResults.add(future);
                    }
                }
            }

            for (Future<PatternMatchResult> future : futureResults) {
                PatternMatchResult result = future.get();
                if (result != null && result.pattern() != null) {
                    System.out.println(result.fileName() + " : " + result.pattern().getDescription());
                } else if (result != null) {
                    System.out.println(result.fileName() + " : Unknown");
                } else {
                    System.out.println("Error processing file.");
                }
            }

            executorService.shutdown();
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    private static List<Pattern> getPatterns() {
        List<Pattern> patterns = new ArrayList<>();
        patterns.add(new Pattern(1, "%PDF-", "PDF document"));
        patterns.add(new Pattern(2, "pmview", "PCP pmview config"));
        patterns.add(new Pattern(4, "PK", "Zip archive"));
        patterns.add(new Pattern(5, "vnd.oasis.opendocument.presentation", "OpenDocument presentation"));
        patterns.add(new Pattern(6, "W.o.r.d", "MS Office Word 2003"));
        patterns.add(new Pattern(6, "P.o.w.e.r.P.o.i", "MS Office PowerPoint 2003"));
        patterns.add(new Pattern(7, "word/_rels", "MS Office Word 2007+"));
        patterns.add(new Pattern(7, "ppt/_rels", "MS Office PowerPoint 2007+"));
        patterns.add(new Pattern(7, "xl/_rels", "MS Office Excel 2007+"));
        patterns.add(new Pattern(8, "-----BEGIN CERTIFICATE-----", "PEM certificate"));
        patterns.add(new Pattern(9, "ftypjp2", "ISO Media JPEG 2000"));
        patterns.add(new Pattern(9, "ftypiso2", "ISO Media MP4 Base Media v2"));
        return patterns;
    }
}
