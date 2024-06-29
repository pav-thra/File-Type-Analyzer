import java.util.Comparator;

public class FileExtensionComparator implements Comparator<String>
{
     public int compare(String file1, String file2)
     {
         String ext1 = getFileExtension(file1);
         String ext2 = getFileExtension(file2);

         return ext1.compareTo(ext2);
     }

    private String getFileExtension(String fileName)
    {
        int dotIndex = fileName.lastIndexOf('.');
        if(dotIndex == -1)
            return "";
        else
            return fileName.substring(dotIndex + 1);
    }
}
