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

    /**
     * 返回最大树深
     */
    private static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = getTestTree();
        System.out.println(isSymmetric(root));
        System.out.println(maxDepth(root));

        //二进制中0代表false，1代表true
        System.out.println(true ^ true);
        System.out.println(true ^ false);
        System.out.println(false ^ false);


        System.out.println("******************************");
        System.out.println(true ^ true ^ true);
        System.out.println(true ^ false  ^ false);
        System.out.println(false ^ false  ^ false);
    }

    public static TreeNode getTestTree(){
        TreeNode root = new TreeNode(9);
        TreeNode left = new TreeNode(7);
        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(2);
        left.left = left1;
        left.right = right1;

        TreeNode right = new TreeNode(7);
        TreeNode left2 = new TreeNode(2);
        TreeNode right2 = new TreeNode(4);
        right.left = left2;
        right.right = right2;
        root.left = left;
        root.right = right;
        return root;
    }
}
