public class RabinKarp {
    private final int prime = 101; // A prime number used for hashing

    public boolean search(byte[] text, byte[] pattern) {
        int m = pattern.length;
        int n = text.length;
        long patternHash = createHash(pattern, m);
        long textHash = createHash(text, m);

        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash && checkEqual(text, i, i + m - 1, pattern, 0, m - 1)) {
                return true;
            }
            if (i < n - m) {
                textHash = recalculateHash(text, i, i + m, textHash, m);
            }
        }
        return false;
    }

    private long createHash(byte[] str, int end) {
        long hash = 0;
        for (int i = 0; i < end; i++) {
            hash += str[i] * Math.pow(prime, i);
        }
        return hash;
    }

    private long recalculateHash(byte[] str, int oldIndex, int newIndex, long oldHash, int patternLen) {
        long newHash = oldHash - str[oldIndex];
        newHash /= prime;
        newHash += str[newIndex] * Math.pow(prime, patternLen - 1);
        return newHash;
    }

    private boolean checkEqual(byte[] str1, int start1, int end1, byte[] str2, int start2, int end2) {
        if (end1 - start1 != end2 - start2) {
            return false;
        }
        while (start1 <= end1 && start2 <= end2) {
            if (str1[start1] != str2[start2]) {
                return false;
            }
            start1++;
            start2++;
        }
        return true;
    }
}
