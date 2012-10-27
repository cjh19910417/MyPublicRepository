
package jian.MazePath;

import java.util.*;

/**
 * @author Jian
 * @time 2011/10/13
 * @aim find mazepath
 *
 */
public class MazePath {

	
	public static void main(String[] args) {
		
		MazePath mp=new MazePath();
		//开始找寻迷宫路径并打印
		//0为墙壁 1为可行走的路 2为路径 3为不可走   
		//方向的顺序分别是:右->左->下->上
		mp.FindPath();	
	
	}

	public void FindPath(){
		int x,y;
		//初始化一个放置路径的栈
		Stack<Path> stack=new Stack<Path>();
		//初始化一个探索者
		Explorer explorer=new Explorer();
		
		//起点为(1,1)终点为(8,8)
		System.out.println("起点:("+explorer.getX()+" ,"+explorer.getY()+")");
		System.out.println("终点:("+8+" ,"+8+")");
		
		//初始化一个迷宫
		Maze maze=new Maze();
		
		
		do{
			if(maze.Passable(explorer.getX(),explorer.getY()))      //可以
			{
				//标记走过的点
				x=explorer.getX();y=explorer.getY();
				maze.FootPrint(x, y);
				//把可走的点加入到栈
				
				Path path=new Path(explorer.getX(), explorer.getY(), explorer.getDirection());
				
				stack.push(path);
				if(x==8 && y==8){
					System.out.println("找到终点");
					break;
				}
				//如果下个方向可行,移动到下一个点
				if(maze.Passable(explorer,explorer.getDirection())){
					maze.nextPos(explorer);
				}
				
			}else{								//不可以,则换方向
				
				if(explorer.getDirection()<4){
					//注:1 右 2左 3下 4上
				switch(explorer.getDirection()){
				case 1:			
					explorer.setDirection(2);
					if(maze.Passable(explorer, explorer.getDirection())){
						//移动到当前方向的下一个
						maze.nextPos(explorer);
					}					
					break;
				case 2:
					
					explorer.setDirection(3);
					if(maze.Passable(explorer, explorer.getDirection())){
						maze.nextPos(explorer);
					}
					break;
				case 3:
					explorer.setDirection(4);
					
					if(maze.Passable(explorer, explorer.getDirection())){
						
						maze.nextPos(explorer);
					}
					break;
					
				}
				}else if(explorer.getDirection()>=4 && !stack.isEmpty()){
					
					//当4个方向都无路可走时,标记当前点为不可通,出栈.
					maze.MarkPrint(explorer);
					stack.pop();
					
					explorer.setXY(stack.peek().getX(), stack.peek().getY());
					explorer.setDirection(1);
				}
				
			}
		}while(!stack.isEmpty());
		//打印迷宫路径
		System.out.println(maze.toString());
	}
	
	
}
