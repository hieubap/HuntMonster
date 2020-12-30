package centre;

import centre.controller.MainController;

public class Rectangle {
	public int x,y,w,h;
	private int[] pixels;
	
	public Rectangle(int x, int y, int w, int h){
		this.x=x;
		this.y=y;
		this.h=h;
		this.w=w;
		
	}
	Rectangle(){
		this(0,0,0,0);
	}

	public void generateGraphics(int borderWidth,int color) {
		pixels= new int[w*h];
		
		for(int i=0;i<pixels.length;i++)
			pixels[i]= MainController.del;
		
		for(int y=0;y<borderWidth;y++)
			for(int x=0;x<w;x++)
				pixels[x+y*w]=color;
		for(int y=0;y<h;y++)
			for(int x=0;x<borderWidth;x++)
				pixels[x+y*w]=color;
		for(int y=0;y<h;y++)
			for(int x=w-borderWidth;x<w;x++)
				pixels[x+y*w]=color;
		for(int y=h-borderWidth;y<h;y++)
			for(int x=0;x<w;x++)
				pixels[x+y*w]=color;
		
	}
	public int[] getPixels() {
		if(pixels!= null) {
			return pixels;
		}
		else System.out.println("error");
		return null;
	}
	public void setXY(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public boolean contain(int x,int y){
		java.awt.Rectangle rectangle = new java.awt.Rectangle(this.x,this.y,w,h);
		return rectangle.contains(x,y);
	}
}