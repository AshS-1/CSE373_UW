package problems;

import datastructures.IntTree;
// Checkstyle will complain that this is an unused import until you use it in your code.
import datastructures.IntTree.IntTreeNode;

/**
 * See the spec on the website for tips and example behavior.
 *
 * Also note: you may want to use private helper methods to help you solve these problems.
 * YOU MUST MAKE THE PRIVATE HELPER METHODS STATIC, or else your code will not compile.
 * This happens for reasons that aren't the focus of this assignment and are mostly skimmed over in
 * 142 and 143. If you want to know more, you can ask on the discussion board or at office hours.
 *
 * REMEMBER THE FOLLOWING RESTRICTIONS:
 * - do not call any methods on the `IntTree` objects
 * - do not construct new `IntTreeNode` objects (though you may have as many `IntTreeNode` variables
 *      as you like).
 * - do not construct any external data structures such as arrays, queues, lists, etc.
 * - do not mutate the `data` field of any node; instead, change the tree only by modifying
 *      links between nodes.
 */

public class IntTreeProblems {

    /**
     * Computes and returns the sum of the values multiplied by their depths in the given tree.
     * (The root node is treated as having depth 1.)
     */

    private static int depthSumHelper(IntTreeNode root, int n) {
        if (root == null) {
            return 0;
        } else {
            return root.data * n + depthSumHelper(root.left, n + 1) + depthSumHelper(root.right, n + 1);
        }
    }
    public static int depthSum(IntTree tree) {
        return depthSumHelper(tree.overallRoot, 1);
    }

    /**
     * Removes all leaf nodes from the given tree.
     */
    // private IntTreeNode removeTreeNodesHelper(IntTreeNode root) {
    //     if (root != null) {
    //         root.left = removeTreeNodesHelper(root.left);
    //         root.right = removeTreeNodesHelper(root.right);
    //         if (root.left == null && root.right != null) {
    //             root = root.right;
    //         } else if (root.left != null && root.right == null) {
    //             root = root.left;
    //         }
    //     }
    //     return root;
    // }
    private static IntTreeNode removeLeavesHelper(IntTreeNode root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                root = null;
            } else {
                root.left = removeLeavesHelper(root.left);
                root.right = removeLeavesHelper(root.right);
            }
        }
        return root;
    }
    public static void removeLeaves(IntTree tree) {
        tree.overallRoot = removeLeavesHelper(tree.overallRoot);

    }

    /**
     * Removes from the given BST all values less than `min` or greater than `max`.
     * (The resulting tree is still a BST.)
     */
    public static IntTreeNode trimHelper(IntTreeNode root, int min, int max) {
        if (root != null) {
            if (root.data < min) {
                root.left = null;
                root = trimHelper(root.right, min, max);
            } else if (root.data > max) {
                root.right = null;
                root = trimHelper(root.left, min, max);
            } else { //(root.data >= min && root.data <= max) {
                root.left = trimHelper(root.left, min, max);
                root.right = trimHelper(root.right, min, max);
            }
        }

        return root;
    }
    public static void trim(IntTree tree, int min, int max) {
        tree.overallRoot = trimHelper(tree.overallRoot, min, max);
    }
}
