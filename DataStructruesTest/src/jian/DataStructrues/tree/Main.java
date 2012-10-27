package jian.DataStructrues.tree;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main
{
    public static void main(String[] args)
    {
        BinNode root = createTree();
        System.out.println("二叉树的深度为-->"+GetDepth(root));
        printTree(root);
    }
    
    static BinNode createTree()
    {
        BinNode node3 = new BinNode(7, null, null);
        BinNode node1 = new BinNode(5, node3, null);
        BinNode node6 = new BinNode(13, null, null);
        BinNode node4 = new BinNode(9, null, node6 );
        BinNode node5 = new BinNode(11, null, null);
        BinNode node2 = new BinNode(1, node4, node5);
        BinNode root = new BinNode(3, node1, node2);
        
        return root;
    }
    
    /**
     * 1）前序遍历（preorder）：节点–>子节点Left（包括其子树）–>子节点Right（包括其子树）
     * @param root
     */
    static void PreOrder(BinNode root)
    {
        if (root == null)
            return;
        //visit current node
        System.out.println(root.Element);
        PreOrder(root.Left);
        PreOrder(root.Right);
    }


    /**
     * 2）后序遍历（postorder）：子节点Left（包括其子树）–>子节点Right（包括其子树）–>节点
     * @param root
     */
    static void PostOrder(BinNode root)
    {
        if (root == null)
            return;
        PostOrder(root.Left);
        PostOrder(root.Right);
        //visit current node
        System.out.println(root.Element);
    }


    /**
     * 3）中序遍历（inorder）：子节点Left（包括其子树）–>节点–>子节点Right（包括其子树）
     * @param root
     */
    static void InOrder(BinNode root)
    {
        if (root == null)
            return;
        InOrder(root.Left);
        //visit current node
        System.out.println(root.Element);
        InOrder(root.Right);
    }

    static void printTree(BinNode root)
    {
        if(root == null) return;
        
        Queue<BinNode> queue = new LinkedBlockingDeque<BinNode>();
        queue.add(root);
        
        while(queue.size()>0)
        {
            BinNode node = queue.poll();
            System.out.println(node.Element);
            
            if(node.Left!=null)
                queue.add(node.Left);
            if(node.Right!=null)
                queue.add(node.Right);
            
        }
        
        
    }
    /**
     * 得到二叉树的深度
     * @param root
     * @return
     */
    static int GetDepth(BinNode root)
    {
        if (root == null)
            return 0;
        int leftLength = GetDepth(root.Left);
        int rightLength = GetDepth(root.Right);
        return (leftLength > rightLength ? leftLength : rightLength) + 1;
    }

    /**
     * 判断二叉树是否为平衡二叉树
     */
    static boolean isBalanceTree(BinNode root)
    {
        if(root == null)
            return true;
        
        int leftLength = GetDepth(root.Left);
        int rightLength = GetDepth(root.Right);
        
        if(leftLength != rightLength) return false;
        else
            return isBalanceTree(root.Left) && isBalanceTree(root.Right);
        
    }
}
