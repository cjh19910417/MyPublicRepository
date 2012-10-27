package jian.DataStructrues;

public class Tree {

	/**
	 * @param args
	 * @author Jian Chan
	 * @target 实现了,先序遍历,中序遍历,后序遍历,按树形打印二叉树
	 */ 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//用笨方法建立一棵满二叉树
		TNode tn7=new TNode("G",null ,null);
		TNode tn6=new TNode("E",null ,null);
		TNode tn5=new TNode("F",null ,null);
		TNode tn4=new TNode("D",null ,null);
		TNode tn3=new TNode("C",tn6 ,tn7);
		TNode tn2=new TNode("B",tn4 ,tn5);
		TNode head=new TNode("A",tn2 ,tn3);
		
		System.out.print("先序遍历:   ");
		preorder(head);//先序遍历
		System.out.print("\n中序遍历:   ");
		inorder(head);//中序遍历
		System.out.print("\n后序遍历:   ");
		postorder(head);//后序遍历
		System.out.println("\n=========我是分割线=========");
		System.out.println("按树状打印的二叉树,是一棵横向树,方向-->");
		PrintTree(head,0);//按树状打印的二叉树,是一棵横向树
		
	}
	/**
	 * 先序遍历
	 * @param curNode
	 */
	static void preorder(TNode curNode)
	{
		if(curNode != null){
			System.out.print(curNode.getData()+" ");
			preorder(curNode.getLeftChild());
			preorder(curNode.getRightChild());
		}
	}
	/**
	 * 中序遍历
	 * @param curNode
	 */
	static void inorder(TNode curNode)
	{
		if(curNode != null){
			inorder(curNode.getLeftChild());
			System.out.print(curNode.getData()+" ");
			inorder(curNode.getRightChild());
		}
	}
	/**
	 * 后序遍历
	 * @param curNode
	 */
	static void postorder(TNode curNode)
	{
		if(curNode != null){
			postorder(curNode.getLeftChild());
			postorder(curNode.getRightChild());
			System.out.print(curNode.getData()+" ");
		}
	}
	
	/**
	 * 利用类似中序遍历的思想,按树状打印的二叉树,是一棵横向树
	 */
	static void PrintTree(TNode curNode,int nLayer)
	
	{
	if(curNode==null){ return;}
	
	PrintTree(curNode.getRightChild(),nLayer+1);
	for(int i=0;i<nLayer;i++){
		//几层深度,就打几个空格
	   System.out.print(" ");
	}
	System.out.println(curNode.getData());//打出内容
	PrintTree(curNode.getLeftChild(),nLayer+1);
	}
}

class TNode {
	private String data;
	private TNode leftChild;
	private TNode rightChild;

	public TNode(String data,TNode leftChild,TNode rightChild ){
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
		public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public TNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(TNode leftChild) {
		this.leftChild = leftChild;
	}
	public TNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(TNode rightChild) {
		this.rightChild = rightChild;
	}
}

