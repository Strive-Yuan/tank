package algorithm.basicSort;

public class BasicSort {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 4, 8, 2, 8, 0, 4, 7, 6, 8, 9, 2, 5};
        selectSort(nums); //选择排序
//        int dichotomy = dichotomy(nums, 11);//二分查找
        int i = dichotomyFindLeft(nums, 2);
        for (int num : nums) {
            System.out.print(num);
        }
        System.out.println();
        System.out.println(i);
        if (i >= 0) {
            System.out.println(nums[i]);
        }
//        bubbleSort(nums); //冒泡排序
//        Arrays.stream(nums).forEach(System.out::println);
//        oneOdd();
//        twoOdd();
    }

    //找到数组中出现奇数次得数据（只有一种数字出现奇数次）
    public static void oneOdd() {
        int[] nums = {2, 2, 4, 4, 2, 8, 4, 8, 4, 0, 0};
        int eor = 0;
        for (int num : nums) {
            eor = eor ^ num;
        }
        System.out.println(eor);
    }

    //找到数组中出现奇数次得数据（有2种数字出现奇数次）
    public static void twoOdd() {
        int[] nums = {2, 2, 4, 4, 2, 8, 4, 8, 4, 7, 7, 7};
        int eor = 0;
        int eor1;
        for (int num : nums) {
            eor ^= num;
        }
        eor1 = eor & ((~eor) + 1);
        int eor2 = 0;
        for (int num : nums) {
            if ((eor1 & num) == 0) {
                eor2 ^= num;
            }
        }
        System.out.println(eor);
        System.out.println(eor1);
        System.out.println(eor2);
        System.out.println(eor ^ eor2);
    }

    //冒泡排序
    public static void bubbleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    //选择排序
    public static void selectSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    //二分查找

    //有序数组中找到<=num最右的位置 todo
    //局部最小值问题 todo
    public static int dichotomy(int[] nums, int num) {
        int begin = 0;
        int end = nums.length - 1;
        int middle;
        int index = -1;
        while (begin < end) {
            middle = (begin + end) / 2;
            if (nums[middle] == num) {
                index = middle;
                break;
            }
            if (nums[middle] > num) {
                end = middle - 1;
            }
            if (nums[middle] < num) {
                begin = middle + 1;
            }
        }
        return index;
    }

    //有序数组中找到>=num最左的位置
    public static int dichotomyFindLeft(int[] nums, int num) {
        //0 2 3 5 5 6 7 8 8 8 8 9
        int index = -1;
        if (nums == null || nums.length <= 1) {
            return index;
        }
        int begin = 0;
        if (nums[0] >= num) {
            return begin;
        }
        int end = nums.length - 1;
        int middle;
        while (begin <= end) {
            middle = (end + begin) / 2;
            if (nums[middle] >= num ) {
                index = middle;
                end = middle - 1;
            }
            if (nums[middle] < num) {
                begin = middle + 1;
            }
        }
        return index;
    }


    private static void swap(int[] nums, int i, int j) {
        int num = nums[i];
        nums[i] = nums[j];
        nums[j] = num;
    }


    // 假设结果数为A B
    public int[] singleNumbers(int[] nums) {
        int x = 0; // 用于记录 A B 的异或结果
        /** 得到A^B的结果
         基于异或运算的以下几个性质
         1. 交换律
         2. 结合律
         3. 对于任何数x，都有x^x=0，x^0=x
         */
        for (int val : nums) x ^= val;
        int flag = x & (-x);
        // x & (-x)本身的作用是得到最低位的1，
        // 而我们所需要的做到的是：利用这个1来进行分组，也就是做到将A和B区分开
        // 前面已经知道，x是我们需要的结果数A和B相异或的结果，也就是说，x的二进制串上的任何一个1，都能成为区分A和B的条件
        // 因此我们只需要得到x上的任意一个1，就可以做到将A和B区分开来
        int res = 0; // 用于记录A或B其中一者
        // 分组操作
        for (int val : nums) {
            // 根据二进制位上的那个“1”进行分组
            // 需要注意的是，分组的结果必然是相同的数在相同的组，且还有一个结果数
            // 因此每组的数再与res=0一路异或下去，最终会得到那个结果数A或B
            // 且由于异或运算具有自反性，因此只需得到其中一个数即可
            if ((flag & val) != 0) {
                res ^= val;
            }
        }
        // 利用先前的x进行异或运算得到另一个，即利用自反性
        return new int[]{res, x ^ res};
    }
}
