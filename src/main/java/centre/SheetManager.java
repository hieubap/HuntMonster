package centre;

import manager.ImageManager;
import my_lib.Sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SheetManager {
	public Sprite[] sprite = new Sprite[8];
	
	public SheetManager() {
		sprite[0] = new Sprite( ImageManager.loadImage("/asset/rip.jpg"));
		sprite[1] = new Sprite( ImageManager.loadImage("/iconarrow.png"));
		sprite[2] = new Sprite( ImageManager.loadImage("/asset/iconspear.png"));
		sprite[3] = new Sprite( ImageManager.loadImage("/asset/Gem/blue.png"));
		sprite[4] = new Sprite( ImageManager.loadImage("/asset/Gem/green.png"));
		sprite[5] = new Sprite( ImageManager.loadImage("/asset/Gem/pink.png"));
		sprite[6] = new Sprite( ImageManager.loadImage("/asset/Gem/yellow.png"));
		sprite[7] = new Sprite( ImageManager.loadImage("/asset/monster.png"));
		
//		background = loadImage("/Image/rip.jpg");
//		
//		backg= new int[background.getWidth()*background.getHeight()];
//		backg= ((DataBufferInt) background.getRaster().getDataBuffer()).getData();
	}
//	public BufferedImage loadImage(String path) {
//		try {
//			BufferedImage loadedImage = ImageIO.read(this.getClass().getResource(path));
//			BufferedImage formatImage = new BufferedImage(loadedImage.getWidth(),
//					loadedImage.getHeight(),BufferedImage.TYPE_INT_RGB);
//
//			formatImage.getGraphics().drawImage(loadedImage,0,0,null);
//
//			return formatImage;
//
//		}catch(IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}
