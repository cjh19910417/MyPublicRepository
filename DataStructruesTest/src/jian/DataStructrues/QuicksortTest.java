package jian.DataStructrues;

import java.util.Calendar;
import java.util.Random;

/**
 * ��javaʵ�ֵĿ�������
 * @author Chan Jian
 * 
 */
public class QuicksortTest {

	
	public static void main(String[] args) {
		
		//�������10���1~1000����,���� �������� ����
		int Num[] = new int[100000];
		for(int i = 0;i<Num.length;i++){
			Num[i]=new Random().nextInt(1000)+1;
		}
		long time_0 = Calendar.getInstance().getTimeInMillis();
		quicksort(Num);//��������
		long time_1 = Calendar.getInstance().getTimeInMillis();
		long howlong = time_1 - time_0;
		System.out.println("����������ʱ:"+ howlong +"ms");
		
		//�������10���1~1000����,���� �������� ����
		int Num_1[] = new int[100000];
		for(int i = 0;i<Num_1.length;i++){
			Num_1[i]=new Random().nextInt(1000)+1;
		}
		long time_2 = Calendar.getInstance().getTimeInMillis();
		insertionsort(Num_1);//��������
		long time_3 = Calendar.getInstance().getTimeInMillis();
		long howlong_1 = time_3 - time_2;
		System.out.println("����������ʱ:"+howlong_1+"ms");
		
		//��ӡ������õ�����
//		for(int i:Num){
//			System.out.println(i);
//			
//		}
	}
	/**
	 * �����������������ֵ
	 * @param data
	 * @param index1
	 * @param index2
	 */
	static void swap(int []data,int index1,int index2){
		int tmp;
		tmp = data[index1];
		data[index1] = data[index2];
		data[index2] = tmp;
		
	}
	
	static void quicksort(int []data,int first,int last){
		
		int lower = first + 1; 
		int upper = last ;
		//swap(data,first,(first+last)/2);//Ϊ�˱������ᱻ������ȥ,���ǰ���������һ��λ��
		
		int pivot = data[first];
		
		while(lower <= upper){
			while(data[lower] < pivot){
				lower++;
			}
			while(data[upper] > pivot){
				upper--;
			}
			
			if(lower < upper){//���lower��upper�����,��ô��������
				swap(data,lower++,upper--);
			}else{
				lower++;
			}
		}
		
		swap(data,first,upper);//�����ỻ�ص�ԭλ
		if(first < upper-1){
			quicksort(data, first, upper-1);
		}
		if(upper+1 < last){
			quicksort(data, upper+1, last);
		}
	}
	
	static void quicksort(int []data){
		if(data.length<2){
			return;
		}
		
		int max = 0;
		for(int i=0;i<data.length;i++){
			if(((Comparable)data[max]).compareTo(data[i])<0){
				max = i;//�ҵ����ֵ���±�
			}
		}
		swap(data,data.length-1,max);
		quicksort(data, 0, data.length-1);
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
}
