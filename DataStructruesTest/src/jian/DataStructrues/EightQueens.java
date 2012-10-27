package jian.DataStructrues;

/**
 * @author Chan Jian
 * @target �˻ʺ�����,���û��ݺ͵ݹ�,��ÿ�ְ��õķ�������ӡ����
 * @time 11/11/07
 */
public class EightQueens {

	static final boolean available = true;
	static final int squares = 8 , norm = squares - 1;
	static int[] positionInRow = new int[squares];
	static boolean[] column = new boolean[squares];
	static boolean[] leftDiagonal = new boolean[squares * 2 - 1];
	static boolean[] rightDiagonal = new boolean[squares * 2 - 1];
	static int howMany = 0;
	public EightQueens(){
		for(int i = 0;i<squares * 2 - 1;i++){
			leftDiagonal[i] = available;
			rightDiagonal[i] = available;
		}
		
		for(int i = 0;i<squares;i++){
			positionInRow[i] = -1;
			column[i] = available;
		}
		
		
	}
	public void printQueens(){
		char[][] borad = new char[squares][squares];
		for(int i = 0;i<squares;i++){
			for(int j = 0;j<squares;j++){
				borad[i][j] = '*';
			}
		}
		howMany++;
		System.out.println("�� "+howMany+" ��:");
		//�ѷ��ûʺ��λ�ô�ӡ�� @
		for(int t= 0;t<squares;t++){
			int k =positionInRow[t];
			borad[t][k] = 'Q';
		}
		
		for(int i=0 ;i<squares ;i++){
			for(int j=0; j<squares ;j++){
				System.out.print(borad[i][j]+" ");
			}
			System.out.println("");
		}
	}
	
	public void putQueens(int row){
		for(int col = 0;col < squares ;col++){
			if(leftDiagonal[col+row] == available && 
					rightDiagonal[row-col+norm] == available &&
					column[col] == available){
				//˵���õ���ԷŻʺ�
				positionInRow[row] = col;
				//�Ѹõ����ڵ���,���ҶԽǶ����Ϊ���ܷ��õ�״̬
				leftDiagonal[col+row] = !available;
				rightDiagonal[row-col+norm] = !available;
				column[col] = !available;
				
				if(row < squares-1){
					putQueens(row+1);
				}else{
					printQueens();
				}
				
				//����,����ڶ�û�кϷ��ط����ûʺ�ʱ,�ͻ���
				column[col] = available;
				leftDiagonal[col+row] = available;
				rightDiagonal[row-col+norm] = available;
							
			}
		}
	}
	public static void main(String[] args) {
		
		EightQueens eg = new EightQueens();
		eg.putQueens(0);
		System.out.println("һ���ҵ��� "+howMany + " �ַ������ð˻ʺ�!");
		
	}

}
