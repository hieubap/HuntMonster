package my_lib;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera implements KeyListener, ObjectGame {
    public int positionX, positionY;
    public int width, height;
    public Direction direction;

    public Camera(int positionX, int positionY, int width, int height) { // player = null
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    public Camera(int positionX, int positionY) {// player = null
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = VariableEnvironment.WIDTH;
        this.height = VariableEnvironment.HEIGHT;
        direction = new Direction(0);
    }

    public boolean canDraw(int x, int y) {
        Rectangle rectangle = new Rectangle(positionX, positionY, VariableEnvironment.WIDTH, VariableEnvironment.HEIGHT);
        return rectangle.contains(x, y);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_UP -> direction.moveUp = true;
            case KeyEvent.VK_DOWN -> direction.moveDown = true;
            case KeyEvent.VK_LEFT -> direction.moveLeft = true;
            case KeyEvent.VK_RIGHT -> direction.moveRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_UP -> direction.moveUp = false;
            case KeyEvent.VK_DOWN -> direction.moveDown = false;
            case KeyEvent.VK_LEFT -> direction.moveLeft = false;
            case KeyEvent.VK_RIGHT -> direction.moveRight = false;
        }
    }

    @Override
    public void update() {
        if (direction.moveUp) positionY -= VariableEnvironment.SPEED_CAMERA;
        if (direction.moveDown) positionY += VariableEnvironment.SPEED_CAMERA;
        if (direction.moveLeft) positionX -= VariableEnvironment.SPEED_CAMERA;
        if (direction.moveRight) positionX += VariableEnvironment.SPEED_CAMERA;
    }

    @Override
    public void draw(Graphics g) {

    }

    public int getX() {
        return positionX;
    }

    public int getY() {
        return positionY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
