import java.util.Comparator;

public class FileExtensionComparator implements Comparator<String>
{
     public int compare(String file1, String file2)
     {
         String ext1 = getFileExtension(file1);
         String ext2 = getFileExtension(file2);

         return ext1.compareTo(ext2);
         // It returns a negative number if ext1 should come first, a positive number if ext2 should come first, and zero if they are the same.
     }

    private String getFileExtension(String fileName)
    {
        int dotIndex = fileName.lastIndexOf('.');   //finding position of last dot
        if(dotIndex == -1) // means if file has no extension
            return "";
        else
            return fileName.substring(dotIndex + 1);  //gives the part of the string starting just after the dot i.e. the extension(pdf, txt,..)
    }
}
