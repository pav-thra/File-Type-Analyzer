import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

//This returns a list of strings
public class FileSearchTask implements Callable<List<String>> {
    private final File directory; //which folder to search in
    private final String extensions; //what kind of files to look for like pdf, txt, etc

    public FileSearchTask(File directory, String extensions) {
        this.directory = directory;
        this.extensions = extensions;
    }

    @Override
    public List<String> call() throws Exception {
        // create empty list of type sting to pu result in
        List<String> res = new ArrayList<>();
        searchDirectory(directory, extensions, res);
        return res;
    }

    private void searchDirectory(File directory, String extensions, List<String> res)
    {
        //Opens the folder and gives all things inside the folder
        File[] fileArr = directory.listFiles();

        if (fileArr != null)
        {
            for(File eachFile : fileArr)
            {
                if(eachFile.isDirectory())
                    searchDirectory(eachFile, extensions, res); //goes inside that folder and searches it too
                else if (eachFile.getName().endsWith(extensions)) //checks if that file ends with the correct type
                    res.add(eachFile.getAbsolutePath()); //found the file and puts in the result
            }
        }
    }
}
