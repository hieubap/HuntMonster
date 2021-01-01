package enemy;

import animation.AnimationSheet;
import centre.controller.MainController;
import manager.EnvironmentVariable;
import manager.ImageManager;
import manager.ImageRenderHandle;
import my_lib.Animation;
import my_lib.ImageRender;
import my_lib.VariableEnvironment;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

public class Monster {
    public static final int PIXEL = EnvironmentVariable.PIXEL;
    public static final int maxBlood = EnvironmentVariable.MAX_BLOOD_MONSTER;
    private static final int rangePlayer = 500;
    public int blood = maxBlood;

    private int direction, timeStop = 0;
    private boolean stop = false;
    private boolean dead = false, attack = false;
    public int positionX, positionY;

    private Animation animation;

    public Monster(int xPosition, int yPosition, int type, boolean mother) {
        animation = new Animation(ImageManager.monsterSheet, 13);

        this.positionX = xPosition;
        this.positionY = yPosition;

        direction = (int)(Math.random()*4) % 4;
        animation.setBegin(8 + direction);
        animation.end = 6;
        animation.current = animation.begin * 13; // column = 13
        animation.speed = 20;
    }

    public void isTarget(MainController game) {
        Rectangle2D check = new Rectangle2D.Float(positionX - rangePlayer, positionY - rangePlayer,
                rangePlayer * 2 + 64, rangePlayer * 2 + 64);
        if (check.contains(game.player.xPosition, game.player.yPosition)) {
            game.setTarget(Math.abs(positionX - (int) game.player.xPosition),
                    Math.abs(positionY - (int) game.player.yPosition));
        } else {
            game.setTarget(0, 0);
        }

    }

    public boolean isCollision(int x, int y) {
        Rectangle2D check = new Rectangle2D.Float(positionX, positionY,
                PIXEL, PIXEL);
        if (check.contains(x, y)) {
            return true;
        }
        return false;
    }

    public void update(MainController mainController) {
        Rectangle2D rect = null;
        switch (direction) {
            case 0:
                rect = new Rectangle2D.Float(positionX - 200,
                        positionY - 200,
                        464, 300);
                break;
            case 1:
                rect = new Rectangle2D.Float(positionX - 300,
                        positionY - 200,
                        300, 464);
                break;
            case 2:
                rect = new Rectangle2D.Float(positionX - 200,
                        positionY + 64,
                        464, 300);
                break;
            case 3:
                rect = new Rectangle2D.Float(positionX + 64,
                        positionY - 200,
                        300, 464);
                break;
        }

        if (rect.contains(mainController.player.xPosition, mainController.player.yPosition)) {
            attack = true;
        } else attack = false;

        rect = null;
        rect = new Rectangle2D.Float(positionX + 10, positionY + 10, 64, 64);

        if (stop) {
            timeStop++;
            if (timeStop > 100) {
                timeStop = 0;
                stop = false;
            }
            return;
        }

        if (attack && rect.contains(mainController.player.xPosition, mainController.player.yPosition)) {
            mainController.player.life -= 3;
            if (mainController.player.life < 0)
                mainController.player.dead = true;
            attack = false;
            stop = true;

        }
        rect = null;

//		if(life<0) {
//			dead=true;
//			return;
//		}
//		time++;
//		timeChange++;
//		end=4;
//
//
//		if(timeChange >= timeMaxChange) {
//
//			timeChange =0;
//			direction=Math.abs(ran.nextInt())%4;
//			begin=8+direction;
//			current=begin*column;
//		}
//		if(time>=8) {
//			time=0;
//			current++;
//
//			if(current>=(begin+1)*column-end)
//				current=begin*column;
////			sprite=null;
////			sprite = game.getSheet(false).getSprite(current);
//		}

        if (attack) {
            if (positionX < mainController.player.xPosition) {
                direction = 3;
                positionX += 3;
            } else if (positionX > mainController.player.xPosition) {
                direction = 1;
                positionX -= 3;
            }
            if (positionY < mainController.player.yPosition) {
                direction = 2;
                positionY += 3;
            } else if (positionY > mainController.player.yPosition) {
                direction = 0;
                positionY -= 3;
            }
        } else
            switch (direction) {
                case 0: {
                    if (positionY > 0)
                        positionY -= 1;
                    break;
                }
                case 1: {
                    if (positionX > 0)
                        positionX -= 1;
                    break;
                }
                case 2: {
                    if (positionY < VariableEnvironment.SIZE_MAP_Y)
                        positionY += 1;
                    break;
                }
                case 3: {
                    if (positionX < VariableEnvironment.SIZE_MAP_Y)
                        positionX += 1;
                    break;
                }
            }

        animation.update();
    }

    public void render(ImageRender imageRenderHandle, int zoomX, int zoomY) {
        double wArray = (double)(blood*PIXEL)/maxBlood;
        int[] arrayBlood = new int[(int)wArray * PIXEL * 3];
        Arrays.fill(arrayBlood, Color.RED.getRGB()|0xFF000000);

        imageRenderHandle.renderArrayInt(arrayBlood, (int)wArray, 3, positionX, positionY - 5, 1, 1);
        animation.render(imageRenderHandle, positionX, positionY, zoomX, zoomY);

        arrayBlood = null;
//		imageRenderHandle.renderSprite(sprite[current], positionX, positionY, zoomX, zoomY);
//		Rectangle rect = new Rectangle(xPosition-200,
//				yPosition-200,464,200);
//		rect.generateGraphics(1, 0x00FF00);
//		render.renderArray(rect.getPixels(), 464, 200, xPosition-200, yPosition+64, 1,1);
    }

    public boolean isDead() {
        return dead;
    }

}
