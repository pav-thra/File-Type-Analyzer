import java.util.Comparator;
import java.util.List;

public class PriorityFileExtensionComparator implements Comparator<String>
{
    private List<String> priorityExtension;

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

        return ext1.compareTo(ext2);
    }

    private int getPriority(String extension)
    {
        int index = priorityExtension.indexOf(extension);
        if(index == -1)
            return Integer.MAX_VALUE;
        return index;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
