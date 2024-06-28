import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileTypeDetector {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the path to your directory");
        String dirString = sc.nextLine().replace("\"", "");
        File dirPath = new File(dirString);
        String[] extensions = {".pdf", ".txt", ".jpg", ".jpeg"};

        //Creating a manager to handle 4 tasks at the same time
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try
        {
            List<Future<List<String>>> future = new ArrayList<>();  //creates empty list to store futures
            for(String extension : extensions)
            {
                FileSearchTask fsTask = new FileSearchTask(dirPath, extension); //creates a new task to look for files of specific type in the folder
                Future<List<String>> f = executorService.submit(fsTask);    //gives task to executorService (manager) to start working and gives ypu the future
                future.add(f); //puts the future in the list of futures
            }

            Map<String, List<String>> filesByExtension = new HashMap<>();
            for (String extension : extensions)
            {
                filesByExtension.put(extension, new ArrayList<>());
            }

            for (Future<List<String>> f : future)
            {
                List <String> files =f.get();   //gets the list of found files
                for (String file : files)
                {
                    for (String extension : extensions)
                    {
                        if(file.endsWith(extension))
                        {
                            filesByExtension.get(extension).add(file);
                        }
                    }
                }
            }

            for (Map.Entry<String, List<String>> entry :filesByExtension.entrySet())
            {
                String extension = entry.getKey();
                List<String> files = entry.getValue();
                System.out.print(extension.substring(1) + " : ");
                for (String file : files)
                {
                    System.out.println(file);
                }
                System.out.println();
            }
        }
        catch (Exception e)
        {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();    //prints out the error
            //this is used to see how the code was before the error occurred
        }
        finally
        {
            executorService.shutdown();
        }
    }
}
