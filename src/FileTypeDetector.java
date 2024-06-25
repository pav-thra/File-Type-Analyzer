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

        //Use a try catch block
        try {
            boolean isPDF = findIfPdf(filepath);
            System.out.println(filepath + " is a PDF? : " + isPDF);
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
            byte[] header = new byte[5]; //to store the first 5 bytes of the array
            //First 5 coz file has a signature that tells us what kind of file it is

            if(fileInputStream.read(header) != -1)
            {
                //convert the byte array to String
                String headerString = new String(header);
                boolean result = headerString.startsWith("%PDF");
                return result;
            }
        }
        return false;
    }
}
