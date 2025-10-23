package UDP;

import java.util.Arrays;

public class bai1 {
    public static void main(String[] args) {
        int arr[] = new int[100];

        Arrays.sort(arr, new Comparable<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
    }
}
