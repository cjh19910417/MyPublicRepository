package jian.MazePath;

import java.awt.Point;

public class Explorer {

	private Point explorer;
	private int direction; // 1 ср 2об 3вС 4ио
	
	public Explorer(){
		explorer=new Point(1, 1);
		this.direction=1;
	}
	
	public Explorer(int x,int y){
		explorer=new Point(x, y);
		this.direction=1;
	}
	
	
	public void setXY(int x, int y){
		explorer.setLocation(x, y);
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	
	
	public int getX(){
		return (int)explorer.getX();
	}
	
	public int getY(){
		return (int)explorer.getY();	
	}
	
	
}
