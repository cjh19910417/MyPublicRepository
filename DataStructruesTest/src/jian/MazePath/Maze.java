package jian.MazePath;



public class Maze {
	
	//��ʼ���Թ�����
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
	

	//�жϵ�ǰ���Ƿ��ͨ
	public boolean Passable(int x,int y){
		
		if(maze[x][y]==1){
			return true;
		}else{
			
			return false;
		}
	}
	//�жϵ�ǰ�������һ�����Ƿ��ͨ
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
	//������߹���·
	public void FootPrint(int x,int y){
		if(Passable(x,y)){
			maze[x][y]=2;
		}
	}
	
	//����ǰ�������һ����
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
	//��ǵ�ǰ����·����
	public void MarkPrint(Explorer explorer){
		
		maze[explorer.getX()][explorer.getY()]=3;
	}
	//��ӡ��ǰ�Թ�
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
