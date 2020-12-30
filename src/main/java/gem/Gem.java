package gem;

import animation.Sprite;
import manager.ImageRenderHandle;
import centre.controller.MainController;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Gem {
	public static Sprite sprite;
	protected int experienceOfGem = 1;
	public int xPosition,yPosition;
	protected boolean isTake=false;
	protected int type,mode;
	
	public Gem(Sprite sprite, int x, int y, int type, int mode) {
		xPosition=x;
		yPosition=y;
		this.type=type;
		this.mode=mode;
		this.sprite = sprite;
	}
	public void update(MainController mainController) {
		if(isTake) return;
		Rectangle2D rect = new Rectangle(xPosition-mainController.player.width+20,
				yPosition-mainController.player.height+20,
				sprite.getWidth() +mainController.player.height-20,
				sprite.getHeight() +mainController.player.height-20);

		// set attribute player
		if(rect.contains(mainController.player.xPosition,mainController.player.yPosition)) {
			mainController.attributeGame.experience+=experienceOfGem;
			mainController.attributeGame.level = mainController.attributeGame.experience/10 + 1;
			mainController.getGemArrayList().remove(this);
			isTake=true;
			addUpdate();
			addUpdate(mainController);
		}
	}
	protected void addUpdate(){
	}
	protected void addUpdate(MainController mainController){}
	public void render(ImageRenderHandle render) {
		if(isTake) {
			return;
		}
		render.renderSprite(sprite,xPosition,yPosition,1,1);
//		render.renderArray(pixels, width, height, xPosition, yPosition, 1,1);
	}
	public boolean destroy() {
		return isTake;
	}

	public static Gem createGemByType(int level,int xPosition,int yPosition){
		if (level == 0) return new GreenGem(xPosition,yPosition);
		if (level == 1) return new PinkGem(xPosition,yPosition);
		if (level == 2) return new RedGem(xPosition,yPosition);
		if (level == 3) return new BlueGem(xPosition,yPosition);
		if (level == 4) return new YellowGem(xPosition,yPosition);

		return null;
	}
}
