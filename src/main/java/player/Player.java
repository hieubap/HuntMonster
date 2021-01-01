package player;

import centre.Rectangle;
import centre.controller.MainController;
import manager.EnvironmentVariable;
import manager.ImageManager;
import my_lib.Animation;
import my_lib.Direction;
import my_lib.ImageRender;
import my_lib.VariableEnvironment;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static manager.EnvironmentVariable.*;

public class Player extends KeyAdapter {
    private static final int SPEED = SPEED_PLAYER;
    private static final int INIT_MODE_PLAYER = INIT_MODE_PLAYER_;
    private static final int INIT_DIRECTION = INIT_DIRECTION_;
    private static final int SPEED_ANIMATION_PLAYER = SPEED_ANIMATION_PLAYER_;
    private static final int PIXEL = EnvironmentVariable.PIXEL;

    public final int width = 64, height = 64;
    public double xPosition, yPosition;
    public Direction direction;
    private Animation animationSheet;
    private int mode;
    public boolean dead = false;
    public boolean hasTarget = false;
    private Rectangle targetBorder; // rect render on monster target with border
    public int life = 100;

    public Player(int x, int y) {
        xPosition = x;
        yPosition = y;
        direction = new Direction(INIT_DIRECTION);
        mode = INIT_MODE_PLAYER;

        animationSheet = new Animation(ImageManager.adSheet, SPEED_ANIMATION_PLAYER);
        animationSheet.end = 1;
    }

    public void update(MainController game) {
        game.getCamera().positionX = (int)xPosition - game.getCamera().getWidth() / 2;
        game.getCamera().positionY = (int)yPosition - game.getCamera().getHeight() / 2;

        // move
        if (direction.moveUp && yPosition >= 0) yPosition -= SPEED;
        if (direction.moveLeft && xPosition >= 0) xPosition -= SPEED;
        if (direction.moveDown && yPosition < VariableEnvironment.SIZE_MAP_Y-PIXEL) yPosition += SPEED;
        if (direction.moveRight && xPosition < VariableEnvironment.SIZE_MAP_X-PIXEL) xPosition += SPEED;

        int directionRender = direction.getDirectionNumber() + mode; // determined index start at sprite array on sheet

        animationSheet.setBegin(directionRender); // set begin sprite in sheet if change direction
        animationSheet.update();
    }

    public void setTarget(int xPosition, int yPosition) {
        targetBorder = new Rectangle(xPosition, yPosition, PIXEL, PIXEL);
        targetBorder.generateGraphics(5, 0xFFFF00);

        hasTarget = true;
    }

    public void render(ImageRender render) {
        if (hasTarget) {
            render.renderArrayInt(targetBorder.getPixels(), targetBorder.w, targetBorder.h, targetBorder.x, targetBorder.y, 1, 1);
        }
        animationSheet.render(render, (int)xPosition, (int)yPosition, 1, 1);
    }

    public void setEndAnimationOnMode(int j) {
        mode = j;
        if (mode == 0)
            animationSheet.end = 7;
        if (mode == 4)
            animationSheet.end = 8;
        if (mode == 8)
            animationSheet.end = 9;
        if (mode == 12)
            animationSheet.end = 7;
        if (mode == 16)
            animationSheet.end = 13;
    }

    public void noTarget() {
        targetBorder = null;
        hasTarget = false;
    }

    // active to rush target
    long timeTest = 0;


    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_UP -> {
                direction.direction = 0;
                direction.moveUp = true;
                break;
            }
            case KeyEvent.VK_LEFT -> {
                direction.direction = 1;
                direction.moveLeft = true;
                break;
            }
            case KeyEvent.VK_DOWN -> {
                direction.direction = 2;
                direction.moveDown = true;
                break;
            }
            case KeyEvent.VK_RIGHT -> {
                direction.direction = 3;
                direction.moveRight = true;
            }
        }
        setEndAnimationOnMode(mode);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_UP -> {
                direction.moveUp = false;
            }
            case KeyEvent.VK_DOWN -> {
                direction.moveDown = false;
            }
            case KeyEvent.VK_LEFT -> {
                direction.moveLeft = false;
            }
            case KeyEvent.VK_RIGHT -> {
                direction.moveRight = false;
            }
        }
        if (!direction.isMoving())
            animationSheet.end = 1;
    }

    public void setSkin(int i) {
        System.gc();
        switch (i) {
            case 0 -> {
                animationSheet = new Animation(ImageManager.warriorSheet, SPEED_ANIMATION_PLAYER);
            }
            case 1 -> {
                animationSheet = new Animation(ImageManager.adSheet, SPEED_ANIMATION_PLAYER);
            }
            case 2 -> {
                animationSheet = new Animation(ImageManager.spellSheet, SPEED_ANIMATION_PLAYER);
            }
            case 3 -> {
                animationSheet = new Animation(ImageManager.playerSheet, SPEED_ANIMATION_PLAYER);
            }
        }
    }
}
