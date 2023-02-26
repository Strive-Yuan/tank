package testClone.algorithm.myTree;

/**
 * 判断是否是平衡树
 * https://leetcode.cn/problems/balanced-binary-tree/
 */
public class MyTree04 {


    /**
     * 判断是否是平衡数
     * 平衡数定义：在树中每一颗子树是否左树的高度-右树的高度差不超过1(<= 1)  满足此条件的树为平衡树
     */
    public static void main(String[] args) {
        TreeNode node = MyTreeUtils.getTree();
        MyTreeUtils.inorderTraversal(node);
        Info balanceTree = isBalanceTree(node);
        System.out.println(balanceTree.isBalance);

    }

    public static Info isBalanceTree(TreeNode node) {
        if (node == null) {
            return new Info(true, 0);
        }
        //左子树是否是平衡树
        Info leftTreeInfo = isBalanceTree(node.left);
        //右子树是否是平衡树
        Info rightTreeInfo = isBalanceTree(node.right);
        int depth = Math.max(leftTreeInfo.depth, rightTreeInfo.depth) + 1;
        //判断当前节点数是否是平行树(左树的高度-右树的高度差不超过1(<= 1))
        boolean isBalance = leftTreeInfo.isBalance && rightTreeInfo.isBalance
                && Math.abs(leftTreeInfo.depth - rightTreeInfo.depth) <= 1;
        return new Info(isBalance, depth);
    }


    static class Info {
        public boolean isBalance; //是否平衡
        public Integer depth; //树高

        public Info(boolean isBalance, Integer depth) {
            this.isBalance = isBalance;
            this.depth = depth;
        }
    }
}
