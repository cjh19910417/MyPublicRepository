package jian.DataStructrues.tree;
/**
 * 二叉树节点
 * @author JOJO
 * @date 2012-10-25
 */
public class BinNode
{
    public int Element;
    public BinNode Left;
    public BinNode Right;
    public BinNode(int element, BinNode left, BinNode right)
    {
        this.Element = element;
        this.Left = left;
        this.Right = right;
    }

    public boolean IsLeaf()
    {
        return this.Left == null && this.Right == null;
    }

}
