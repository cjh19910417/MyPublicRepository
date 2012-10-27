package jian.DataStructrues;

import java.util.Calendar;
import java.util.Random;

/**
 * 用java实现的快速排序
 * @author Chan Jian
 * 
 */
public class QuicksortTest {

	
	public static void main(String[] args) {
		
		//随机生成10万个1~1000的数,并用 快速排序 排序
		int Num[] = new int[100000];
		for(int i = 0;i<Num.length;i++){
			Num[i]=new Random().nextInt(1000)+1;
		}
		long time_0 = Calendar.getInstance().getTimeInMillis();
		quicksort(Num);//快速排序
		long time_1 = Calendar.getInstance().getTimeInMillis();
		long howlong = time_1 - time_0;
		System.out.println("快速排序用时:"+ howlong +"ms");
		
		//随机生成10万个1~1000的数,并用 插入排序 排序
		int Num_1[] = new int[100000];
		for(int i = 0;i<Num_1.length;i++){
			Num_1[i]=new Random().nextInt(1000)+1;
		}
		long time_2 = Calendar.getInstance().getTimeInMillis();
		insertionsort(Num_1);//快速排序
		long time_3 = Calendar.getInstance().getTimeInMillis();
		long howlong_1 = time_3 - time_2;
		System.out.println("插入排序用时:"+howlong_1+"ms");
		
		//打印出排序好的数组
//		for(int i:Num){
//			System.out.println(i);
//			
//		}
	}
	/**
	 * 交换数组里面的两个值
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
		//swap(data,first,(first+last)/2);//为了避免枢轴被移来移去,我们把它换到第一个位置
		
		int pivot = data[first];
		
		while(lower <= upper){
			while(data[lower] < pivot){
				lower++;
			}
			while(data[upper] > pivot){
				upper--;
			}
			
			if(lower < upper){//如果lower在upper的左边,那么交换两数
				swap(data,lower++,upper--);
			}else{
				lower++;
			}
		}
		
		swap(data,first,upper);//把枢轴换回到原位
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
				max = i;//找到最大值的下标
			}
		}
		swap(data,data.length-1,max);
		quicksort(data, 0, data.length-1);
	}
	/**
	 * 插入排序的实现
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
