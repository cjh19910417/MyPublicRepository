package jian.DataStructrues;

public class Tree {

	/**
	 * @param args
	 * @author Jian Chan
	 * @target ʵ����,�������,�������,�������,�����δ�ӡ������
	 */ 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//�ñ���������һ����������
		TNode tn7=new TNode("G",null ,null);
		TNode tn6=new TNode("E",null ,null);
		TNode tn5=new TNode("F",null ,null);
		TNode tn4=new TNode("D",null ,null);
		TNode tn3=new TNode("C",tn6 ,tn7);
		TNode tn2=new TNode("B",tn4 ,tn5);
		TNode head=new TNode("A",tn2 ,tn3);
		
		System.out.print("�������:   ");
		preorder(head);//�������
		System.out.print("\n�������:   ");
		inorder(head);//�������
		System.out.print("\n�������:   ");
		postorder(head);//�������
		System.out.println("\n=========���Ƿָ���=========");
		System.out.println("����״��ӡ�Ķ�����,��һ�ú�����,����-->");
		PrintTree(head,0);//����״��ӡ�Ķ�����,��һ�ú�����
		
	}
	/**
	 * �������
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
	 * �������
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
	 * �������
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
	 * �����������������˼��,����״��ӡ�Ķ�����,��һ�ú�����
	 */
	static void PrintTree(TNode curNode,int nLayer)
	
	{
	if(curNode==null){ return;}
	
	PrintTree(curNode.getRightChild(),nLayer+1);
	for(int i=0;i<nLayer;i++){
		//�������,�ʹ򼸸��ո�
	   System.out.print(" ");
	}
	System.out.println(curNode.getData());//�������
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

