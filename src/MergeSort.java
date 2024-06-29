import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static void sort(List<String> list) {
        if (list.size() > 1) {
            List<String> left = new ArrayList<>(list.subList(0, list.size() / 2));
            List<String> right = new ArrayList<>(list.subList(list.size() / 2, list.size()));

            sort(left);
            sort(right);

            merge(list, left, right);
        }
    }

    private static void merge(List<String> list, List<String> left, List<String> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }
}
