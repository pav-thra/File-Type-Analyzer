import java.util.Comparator;
import java.util.List;

public class PriorityFileExtensionComparator implements Comparator<String>
{
    private final List<String> priorityExtension;

    public PriorityFileExtensionComparator(List<String> priorityExtension) {
        this.priorityExtension = priorityExtension;
    }

    @Override
    public int compare(String file1, String file2)
    {
        String ext1 = getFileExtension(file1);
        String ext2 = getFileExtension(file2);

        int prior1 = getPriority(ext1);
        int prior2 = getPriority(ext2);

        if(prior1 != prior2)
            return Integer.compare(prior1, prior2);
            //Integer class has a compare method
            /*The compare method takes two integer values and returns:
                A negative integer if prior1 is less than prior2.
                Zero if prior1 is equal to prior2.
                A positive integer if prior1 is greater than prior2.
            * */

        return ext1.compareTo(ext2); //uses compareTo string function if integer priorities are same
    }

    private int getPriority(String extension)
    {
        int index = priorityExtension.indexOf(extension);  //position of extension in the list
        if(index == -1)  //if extension is not in the list
            return Integer.MAX_VALUE;
        return index;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
