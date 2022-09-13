package algorithm.myTree;

/**
 * 判断是否是镜面树
 */
public class MyTree01 {

    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private static boolean isMirror(TreeNode h1, TreeNode h2) {
        if (h1 == null ^ h2 == null) {
            return false;
        }
        if (h1 == null && h2 == null) {
            return true;
        }
        return h1.val == h2.val && isMirror(h1.left, h2.right) && isMirror(h1.right, h2.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.val = 9;
        TreeNode left = new TreeNode();
        left.val = 7;
        TreeNode left1 = new TreeNode();
        left1.val = 4;
        TreeNode right1 = new TreeNode();
        right1.val = 2;
        left.left = left1;
        left.right = right1;

        TreeNode right = new TreeNode();
        right.val = 7;
        TreeNode left2 = new TreeNode();
        left2.val = 2;
        TreeNode right2 = new TreeNode();
        right2.val = 4;
        right.left = left2;
        right.right = right2;
        root.left = left;
        root.right = right;
        System.out.println(isSymmetric(root));


        //二进制中0代表false，1代表true
        System.out.println(true ^ true);
        System.out.println(true ^ false);
        System.out.println(false ^ false);
    }
}
