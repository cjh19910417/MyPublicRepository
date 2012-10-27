package jian.DataStructrues;

import java.util.Random;
/**
 * 
 * @author Chan Jian
 *
 */
public class BasesortTest {

	public static void main(String []args){
		int data[] = new int[10];
		for(int i = 0;i<data.length;i++){
			data[i]=new Random().nextInt(10)+1;
		}
		insertionsort(data);//��������
		//selectionsort(data);//ѡ������
		for(int i:data){
			System.out.println(i);
			
		}
	}
	/**
	 * ���������ʵ��
	 * @param data
	 */
	public static void insertionsort(int []data){
		int tmp;
		int i,j;
		for(i = 1;i<data.length;i++){
			tmp = data[i];
			for(j = i;j > 0 && tmp < data[j-1];j--){
				data[j] = data[j-1];
			}
			data[j] = tmp;
		}
	}
	/**
	 * ѡ�������ʵ��
	 * @param data
	 */
	public static void selectionsort(int data[]){
		int i,j,least;
		for(i = 0;i<data.length-1;i++){
			for(j=i+1,least=i;j<data.length;j++){
				if(data[j]<data[least]){
					least = j;
				}
			swap(data,least,i);	
			}
		}
	}
	/**
	 * ���������е�������
	 * @param data
	 * @param e1
	 * @param e2
	 */
	public static void swap(int []data,int e1,int e2){
		int tmp = data[e1];
		data[e1] = data[e2];
		data[e2]  = tmp;
	}
}
