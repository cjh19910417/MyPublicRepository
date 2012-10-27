
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
		//��ʼ��Ѱ�Թ�·������ӡ
		//0Ϊǽ�� 1Ϊ�����ߵ�· 2Ϊ·�� 3Ϊ������   
		//�����˳��ֱ���:��->��->��->��
		mp.FindPath();	
	
	}

	public void FindPath(){
		int x,y;
		//��ʼ��һ������·����ջ
		Stack<Path> stack=new Stack<Path>();
		//��ʼ��һ��̽����
		Explorer explorer=new Explorer();
		
		//���Ϊ(1,1)�յ�Ϊ(8,8)
		System.out.println("���:("+explorer.getX()+" ,"+explorer.getY()+")");
		System.out.println("�յ�:("+8+" ,"+8+")");
		
		//��ʼ��һ���Թ�
		Maze maze=new Maze();
		
		
		do{
			if(maze.Passable(explorer.getX(),explorer.getY()))      //����
			{
				//����߹��ĵ�
				x=explorer.getX();y=explorer.getY();
				maze.FootPrint(x, y);
				//�ѿ��ߵĵ���뵽ջ
				
				Path path=new Path(explorer.getX(), explorer.getY(), explorer.getDirection());
				
				stack.push(path);
				if(x==8 && y==8){
					System.out.println("�ҵ��յ�");
					break;
				}
				//����¸��������,�ƶ�����һ����
				if(maze.Passable(explorer,explorer.getDirection())){
					maze.nextPos(explorer);
				}
				
			}else{								//������,�򻻷���
				
				if(explorer.getDirection()<4){
					//ע:1 �� 2�� 3�� 4��
				switch(explorer.getDirection()){
				case 1:			
					explorer.setDirection(2);
					if(maze.Passable(explorer, explorer.getDirection())){
						//�ƶ�����ǰ�������һ��
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
					
					//��4��������·����ʱ,��ǵ�ǰ��Ϊ����ͨ,��ջ.
					maze.MarkPrint(explorer);
					stack.pop();
					
					explorer.setXY(stack.peek().getX(), stack.peek().getY());
					explorer.setDirection(1);
				}
				
			}
		}while(!stack.isEmpty());
		//��ӡ�Թ�·��
		System.out.println(maze.toString());
	}
	
	
}
