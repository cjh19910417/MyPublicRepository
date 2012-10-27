package jian.MazePath;



public class Maze {
	
	//初始化迷宫数组
	private int [][]maze={
			{0,0,0,0,0,0,0,0,0,0},
		    {0,1,1,0,1,1,1,0,1,0},
		    {0,1,1,0,1,1,1,0,1,0},
		    {0,1,1,1,1,0,0,1,1,0},
		    {0,1,0,0,0,1,1,1,1,0},
		    {0,1,1,1,0,1,1,1,1,0},
		    {0,1,0,1,1,1,0,1,1,0},
		    {0,1,0,0,0,1,0,0,1,0},
		    {0,0,1,1,1,1,1,1,1,0},
		    {0,0,0,0,0,0,0,0,0,0}
		};
	

	//判断当前点是否可通
	public boolean Passable(int x,int y){
		
		if(maze[x][y]==1){
			return true;
		}else{
			
			return false;
		}
	}
	//判断当前方向的下一个点是否可通
	public boolean Passable(Explorer explorer,int direction){
		
				if(direction==1 && maze[explorer.getX()][explorer.getY()+1]==1){
					return true;
				}else if(direction==2 && maze[explorer.getX()][explorer.getY()-1]==1){
						return true;
					}else if(direction==3 && maze[explorer.getX()+1][explorer.getY()]==1){
							return true;
					}else if(direction==4 && maze[explorer.getX()-1][explorer.getY()]==1){
					return true;
			}else{
				if(direction<4){	
					direction++;
				}
					return false;
			}
		
	
		
		
	}
	//标记已走过的路
	public void FootPrint(int x,int y){
		if(Passable(x,y)){
			maze[x][y]=2;
		}
	}
	
	//到当前方向的下一个点
	public void nextPos(Explorer explorer){
		switch(explorer.getDirection()){
		case 1:
			explorer.setXY(explorer.getX(), explorer.getY()+1);
			explorer.setDirection(1);
			break;
		case 2:
			explorer.setXY(explorer.getX(), explorer.getY()-1);
			explorer.setDirection(1);
			
			break;
		case 3:
			explorer.setXY(explorer.getX()+1, explorer.getY());
			explorer.setDirection(1);
			break;
		case 4:
			explorer.setXY(explorer.getX()-1, explorer.getY());
			explorer.setDirection(1);
			break;
			
		}
		
	}
	//标记当前点无路可走
	public void MarkPrint(Explorer explorer){
		
		maze[explorer.getX()][explorer.getY()]=3;
	}
	//打印当前迷宫
	public String toString(){
		String str="";
		for(int i=0;i<10;i++){
			
			for(int j=0;j<10;j++){
				str += " "+maze[i][j];
				
			}
			str += "\n";
		}
		
		return str;
	}
}
