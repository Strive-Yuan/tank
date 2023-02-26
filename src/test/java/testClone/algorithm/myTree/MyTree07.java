package testClone.algorithm.myTree;

/**
 * 能否组成路径和
 * https://leetcode.com/problems/path-sum/submissions/897845804/
 */
public class MyTree07 {

    private static boolean hasSum;

    public static void main(String[] args) {
        int sum = 20;
        TreeNode root = MyTreeUtils.getTree();
        System.out.println(process(root, sum));

    }

    public static boolean process(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        hasSum = false;
        hasPathSum(root, targetSum, 0);
        return hasSum;
    }

    private static void hasPathSum(TreeNode root, int sum, int processValue) {
        if (root.left == null && root.right == null) {
            if (processValue + root.val == sum) {
                hasSum = true;
                return;
            }
        }
        processValue += root.val;
        if (root.left != null) {
            hasPathSum(root.left, sum, processValue);
        }
        if (root.right != null) {
            hasPathSum(root.right, sum, processValue);
        }
    }

}
