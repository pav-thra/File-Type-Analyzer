import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileTypeDetector {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the path to your pdf file");
        String filepath = sc.nextLine();

        // Remove any surrounding quotes from the input
        filepath = filepath.replace("\"", "");

        try
        {
            long startTime = System.nanoTime();
            boolean isPDF = findIfPdf(filepath);
            long endTime = System.nanoTime();
            long timeTaken = endTime - startTime;

            System.out.println(filepath + " is a PDF? : " + isPDF);
            System.out.println("Time taken is "+ timeTaken + " nanoseconds");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            //this is used to see how the code was before the error occurred
        }
    }

    public static boolean findIfPdf(String filepath) throws IOException
    {
        try(FileInputStream fileInputStream = new FileInputStream(filepath))
        //File Input Stream is used to open the file
        {
            byte[] header = new byte[1024]; //to store the first 1024 bytes of the array
            //IN stage 1:First 5 coz file has a signature that tells us what kind of file it is

            if(fileInputStream.read(header) != -1)
            {
                //convert the byte array to String
                String headerString = new String(header);
                boolean result = KMPAlgorithm.KMPSearch("%PDF" , headerString);
                return result;
            }
        }
        return false;
    }
}
