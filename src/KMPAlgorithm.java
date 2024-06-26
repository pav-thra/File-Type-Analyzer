public class KMPAlgorithm
{
    public static int[] LPS(String pattern)
    {
        int length=0, i=1;
        int[] lps = new int[pattern.length()];
        lps[0] = 0;

        while(i < pattern.length())
        {
            if (pattern.charAt(i) == pattern.charAt(length))
            {
                length +=1;
                lps[i] = length;
                i +=1;
            }
            else
            {
                if (length != 0)
                    length = lps[length - 1];
                else
                {
                    lps[i] = 0;
                    i += 1;
                }
            }
        }
        return lps;
    }

    public static boolean KMPSearch(String pattern, String text)
    {
        int[] lps = LPS(pattern);
        int i=0, j=0;

        while(i < text.length())
        {
            if (pattern.charAt(j) == text.charAt(i))
            {
                i +=1;
                j +=1;
            }
            if(j == pattern.length())
                return true;
            else if (i < text.length() && pattern.charAt(j) != text.charAt(i))
            {
                if (j != 0)
                    j = lps[j-1];
                else
                    i +=1;
            }
        }
        return false;
    }
}
