package testClone.algorithm.basicSort;

import java.util.Arrays;

/**
 * 归并排序（非递归方式）
 */
public class MergeSort01 {
    public static void main(String[] args) {
        int[] arr = {3, 6, 7, 9, 8};
        process(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void process(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;
        int length = arr.length;
        while (step < length) {
            int start = 0;
            while (start < length) {
                //左组处理边界
                int middle = 0;
                if (length - start >= step) {
                    middle = start + step - 1;
                } else {
                    middle = length - 1;
                }
                //start....middle
                if (middle == length - 1) {
                    break;
                }
                //右组处理边界   左：middle +1  右：middle + step
                int rightGroupEnd;
                if (length - 1 - middle >= step) {
                    rightGroupEnd = middle + step;
                } else {
                    rightGroupEnd = length - 1;
                }
                //合并  [start....middle,middle + 1.....rightGroupEnd]
                marge(arr, start, middle, rightGroupEnd);

                // 设置下一个左组的位置 及 处理边界
                if (rightGroupEnd == length - 1) {
                    break;
                } else {
                    start = rightGroupEnd + 1;
                }
            }
            //防止step越界（超出整数最大值）
            if (step > (length / 2)) {
                break;
            } else {
                step <<= 1;
            }
        }
    }

    public static void marge(int[] arr, int start, int middle, int rightGroupEnd) {
        int[] help = new int[rightGroupEnd - start + 1];
        int helpIndex = 0;
        int leftGroupStart = start;
        int rightGroupStart = middle + 1;
        while (leftGroupStart <= middle && rightGroupStart <= rightGroupEnd) {
            if (arr[leftGroupStart] > arr[rightGroupStart]) {
                help[helpIndex++] = arr[leftGroupStart++];
            } else {
                help[helpIndex++] = arr[rightGroupStart++];
            }
        }
        while (leftGroupStart <= middle){
            help[helpIndex++] = arr[leftGroupStart++];
        }
        while (rightGroupStart <= rightGroupEnd){
            help[helpIndex++] = arr[rightGroupStart++];
        }
        System.arraycopy(help, 0, arr, start, help.length);
    }
}
