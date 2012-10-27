package jian.DataStructrues;

/**
 * @author Chan Jian
 * @target 八皇后问题,利用回溯和递归,把每种摆置的方法都打印出来
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
		System.out.println("第 "+howMany+" 种:");
		//把放置皇后的位置打印成 @
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
				//说明该点可以放皇后
				positionInRow[row] = col;
				//把该点所在的列,左右对角都标记为不能放置的状态
				leftDiagonal[col+row] = !available;
				rightDiagonal[row-col+norm] = !available;
				column[col] = !available;
				
				if(row < squares-1){
					putQueens(row+1);
				}else{
					printQueens();
				}
				
				//回溯,如果在都没有合法地方放置皇后时,就回溯
				column[col] = available;
				leftDiagonal[col+row] = available;
				rightDiagonal[row-col+norm] = available;
							
			}
		}
	}
	public static void main(String[] args) {
		
		EightQueens eg = new EightQueens();
		eg.putQueens(0);
		System.out.println("一共找到了 "+howMany + " 种方法放置八皇后!");
		
	}

}
