package testClone.algorithm.basicSort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {3, 6, 7, 9, 8};
        process(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void process(int[] arr, int begin, int end) {
        if (begin == end) {
            return;
        }
        int mid = (end + begin) / 2;
        process(arr, begin, mid);
        process(arr, mid + 1, end);
        merge(arr, begin, mid, end);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        System.arraycopy(help, 0, arr, L, help.length);
    }
    /**
     *  arr = {3, 6, 7, 9, 8}
     * 左process(arr,0,2)
     * 左process(arr,0,1)
     * 左process(arr,0,0)
     * 左process(arr,1,1)
     *     merge(arr,0,0,1)       3 | 6
     * 右process(arr,2,2)
     *     merge(arr,0,1,2)    3  6 | 7 至此左边排好序
     * 右process(arr,3,4)
     * 左process(arr,3,3)
     * 左process(arr,4,4)
     *     merge(arr,3,3,4)       9 | 8  至此右边排好序
     *     merge(arr,0,2,4)   3 6 7 | 9 8
     */
}
