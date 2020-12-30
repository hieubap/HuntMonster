package centre;

import centre.controller.MainController;
import manager.EnvironmentVariable;
import skill.Arrow;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpgradeStore extends MouseAdapter {
    public static final int DAMAGE_ARROW_PER_UP = EnvironmentVariable.DAMAGE_UPGRADE_ARROW_PER_LEVEL[0];
    public static final int NUMBER_ARROW_PER_UP = 10;
    public static int LEVEL_ARROW = 1;
    public static int NUMBER_GEM_GREEN_UP_ARROW = EnvironmentVariable.NUMBER_GEM_GREEN_NEED_UPGRADE[0];
    public static int NUMBER_GEM_PINK_UP_ARROW = EnvironmentVariable.NUMBER_GEM_PINK_NEED_UPGRADE[0];
    public static int NUMBER_GEM_RED_UP_ARROW = EnvironmentVariable.NUMBER_GEM_RED_NEED_UPGRADE[0];
    public static int NUMBER_GEM_BLUE_UP_ARROW = EnvironmentVariable.NUMBER_GEM_BLUE_NEED_UPGRADE[0];
    public static int NUMBER_GEM_GOLD_UP_ARROW = EnvironmentVariable.NUMBER_GEM_GOLD_NEED_UPGRADE[0];

    public final int WIDTH = 800, HEIGHT = 400;
    public int positionX, positionY;
    private MainController mainController;

    int positionXArrowUp = 350;
    int positionYArrowUp = 0;

    // button
    private Rectangle updateArrowButton;

    public UpgradeStore(MainController mainController) {
        this.mainController = mainController;
        this.positionX = (EnvironmentVariable.WIDTH - WIDTH) / 2;
        this.positionY = (EnvironmentVariable.HEIGHT - HEIGHT) / 2;

        updateArrowButton = new Rectangle(positionX + positionXArrowUp + 40, positionY + positionYArrowUp + 300, 100, 50);
    }

    public void draw(Graphics g) {
        g.setColor(EnvironmentVariable.COLOR_BACKGROUND_BLACK);
        g.fillRect(positionX, positionY, WIDTH, HEIGHT);
        g.setColor(Color.white);
        g.drawString("UPDATE", positionX + WIDTH / 2 - 10, positionY + 20);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));

        g.drawString("  arrow", positionX + positionXArrowUp + 50, positionY + positionYArrowUp + 50);
        g.drawString("dame:   " + Arrow.DAMAGE, positionX + positionXArrowUp + 50, positionY + positionYArrowUp + 70);
        g.drawString("number: " + mainController.MAX_NUMBER_ARROW, positionX + positionXArrowUp + 50, positionY + positionYArrowUp + 90);
        g.drawString("level:  " + LEVEL_ARROW, positionX + positionXArrowUp + 50, positionY + positionYArrowUp + 110);

        g.setColor(EnvironmentVariable.COLOR_GREEN_GEM);
        g.drawString("green gem: " + NUMBER_GEM_GREEN_UP_ARROW, positionX + positionXArrowUp + 45, positionY + positionYArrowUp + 130);
        if (NUMBER_GEM_GREEN_UP_ARROW > mainController.attributeGame.numberGreenGem)
            g.drawLine(positionX + positionXArrowUp + 40, positionY + positionYArrowUp + 128, positionX + positionXArrowUp + 125, positionY + positionYArrowUp + 128);
        g.setColor(EnvironmentVariable.COLOR_PINK_GEM);
        g.drawString("pink gem : " + NUMBER_GEM_PINK_UP_ARROW, positionX + positionXArrowUp + 45, positionY + positionYArrowUp + 150);
        if (NUMBER_GEM_PINK_UP_ARROW > mainController.attributeGame.numberPinkGem)
            g.drawLine(positionX + positionXArrowUp + 40, positionY + positionYArrowUp + 148, positionX + positionXArrowUp + 125, positionY + positionYArrowUp + 148);
        g.setColor(EnvironmentVariable.COLOR_RED_GEM);
        g.drawString("red gem  : " + NUMBER_GEM_RED_UP_ARROW, positionX + positionXArrowUp + 45, positionY + positionYArrowUp + 170);
        if (NUMBER_GEM_RED_UP_ARROW > mainController.attributeGame.numberRedGem)
            g.drawLine(positionX + positionXArrowUp + 40, positionY + positionYArrowUp + 168, positionX + positionXArrowUp + 125, positionY + positionYArrowUp + 168);
        g.setColor(EnvironmentVariable.COLOR_BLUE_GEM);
        g.drawString("blue gem : " + NUMBER_GEM_BLUE_UP_ARROW, positionX + positionXArrowUp + 45, positionY + positionYArrowUp + 190);
        if (NUMBER_GEM_BLUE_UP_ARROW > mainController.attributeGame.numberBlueGem)
            g.drawLine(positionX + positionXArrowUp + 40, positionY + positionYArrowUp + 188, positionX + positionXArrowUp + 125, positionY + positionYArrowUp + 188);
        g.setColor(EnvironmentVariable.COLOR_GOLD_GEM);
        g.drawString("gold gem : " + NUMBER_GEM_GOLD_UP_ARROW, positionX + positionXArrowUp + 45, positionY + positionYArrowUp + 210);
        if (NUMBER_GEM_GOLD_UP_ARROW > mainController.attributeGame.numberYellowGem)
            g.drawLine(positionX + positionXArrowUp + 40, positionY + positionYArrowUp + 208, positionX + positionXArrowUp + 125, positionY + positionYArrowUp + 208);

        if (NUMBER_GEM_GREEN_UP_ARROW <= mainController.attributeGame.numberGreenGem &&
                NUMBER_GEM_PINK_UP_ARROW <= mainController.attributeGame.numberPinkGem &&
                NUMBER_GEM_RED_UP_ARROW <= mainController.attributeGame.numberRedGem &&
                NUMBER_GEM_BLUE_UP_ARROW <= mainController.attributeGame.numberBlueGem &&
                NUMBER_GEM_GOLD_UP_ARROW <= mainController.attributeGame.numberYellowGem) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(updateArrowButton.x , updateArrowButton.y , updateArrowButton.w, updateArrowButton.h);
            g.setColor(Color.WHITE);
            g.drawString("update", updateArrowButton.x  + 20, updateArrowButton.y  + 30);
        }
    }

    public void updateArrow() {
        LEVEL_ARROW++;
        MainController.MAX_NUMBER_ARROW += NUMBER_ARROW_PER_UP;

        // decrease gem
        mainController.attributeGame.numberGreenGem -= NUMBER_GEM_GREEN_UP_ARROW;
        mainController.attributeGame.numberPinkGem -= NUMBER_GEM_PINK_UP_ARROW;
        mainController.attributeGame.numberRedGem -= NUMBER_GEM_RED_UP_ARROW;
        mainController.attributeGame.numberBlueGem -= NUMBER_GEM_BLUE_UP_ARROW;
        mainController.attributeGame.numberYellowGem -= NUMBER_GEM_GOLD_UP_ARROW;

        // up arrow
        Arrow.DAMAGE += EnvironmentVariable.DAMAGE_UPGRADE_ARROW_PER_LEVEL[LEVEL_ARROW - 1];

        // up number gem need up level
        NUMBER_GEM_GREEN_UP_ARROW = EnvironmentVariable.NUMBER_GEM_GREEN_NEED_UPGRADE[LEVEL_ARROW - 1];
        NUMBER_GEM_PINK_UP_ARROW = EnvironmentVariable.NUMBER_GEM_PINK_NEED_UPGRADE[LEVEL_ARROW - 1];
        NUMBER_GEM_RED_UP_ARROW = EnvironmentVariable.NUMBER_GEM_RED_NEED_UPGRADE[LEVEL_ARROW - 1];
        NUMBER_GEM_BLUE_UP_ARROW = EnvironmentVariable.NUMBER_GEM_BLUE_NEED_UPGRADE[LEVEL_ARROW - 1];
        NUMBER_GEM_GOLD_UP_ARROW = EnvironmentVariable.NUMBER_GEM_GOLD_NEED_UPGRADE[LEVEL_ARROW - 1];
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (NUMBER_GEM_GREEN_UP_ARROW <= mainController.attributeGame.numberGreenGem &&
                NUMBER_GEM_PINK_UP_ARROW <= mainController.attributeGame.numberPinkGem &&
                NUMBER_GEM_RED_UP_ARROW <= mainController.attributeGame.numberRedGem &&
                NUMBER_GEM_BLUE_UP_ARROW <= mainController.attributeGame.numberBlueGem &&
                NUMBER_GEM_GOLD_UP_ARROW <= mainController.attributeGame.numberYellowGem &&
                updateArrowButton.contain(e.getX(), e.getY())) {
            updateArrow();
        }
    }
}
