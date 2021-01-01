package skill;

import manager.ImageRenderHandle;
import centre.Rectangle;
import centre.controller.MainController;
import my_lib.ImageRender;
import player.Player;
import my_lib.Vector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static manager.EnvironmentVariable.*;

public class SkillManager {
    private static final int SPEED_CHASE = SPEED_CHASE_;
    private static final int TIMER_ARROW = DELAY_ARROW_;
    private static final int TIMER_CHASE = DELAY_CHASE_;
    private static final int SPEED_SKILL_3 = SPEED_SKILL_3_;

    // skill 1

    // skill 2
    private int tickPointX, tickPointY;
    private Rectangle tickRectPositionSkill2;
    private boolean r, activeSkill2 = false, activeSkill1 = false;

    // skill 3
    public int targetX, targetY ,indexTargetMonster;
    public Vector vectorToTarget;
    private final Player player;
    private final MainController mainController;

    public Timer timeArrow, timeSkill3;

    public ArrayList<Arrow> arrow = new ArrayList<>();

    public SkillManager(MainController game) {
        this.player = game.player;
        this.mainController = game;

        // active to shot arrow
        timeArrow = new Timer(TIMER_ARROW, null);

        // active to chase monster
        timeSkill3 = new Timer(TIMER_CHASE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent action) {
                vectorToTarget = new Vector(player.xPosition, player.yPosition, targetX,targetY);
                vectorToTarget.setLength(SPEED_SKILL_3);

                player.xPosition += vectorToTarget.positionX;
                player.yPosition += vectorToTarget.positionY;

                if (Math.abs(player.xPosition - targetX) < 20 && Math.abs(player.yPosition - targetY) < 20)
                {
                    mainController.getMonster().remove(indexTargetMonster);
                    timeSkill3.stop();
                }
            }
        });

    }

    public void activeSkill(int numberSkill) {
        switch (numberSkill) {
            case 0: {
                firstSkill(player.direction.getDirectionNumber());
                break;
            }
            case 1: {
                secondSkill((int) player.xPosition, (int) player.yPosition);
                break;
            }
            case 2: {
                thirdSkill();
                break;
            }
        }
    }

    public void render(ImageRender render) {
        if (activeSkill1) {
            for (int i = 0; i < arrow.size(); i++)
                arrow.get(i).render(render);
        }
        if (activeSkill2) {
            render.renderArrayInt(tickRectPositionSkill2.getPixels(), 20, 20, tickPointX, tickPointY, 1, 1);
        }
    }

    public void firstSkill(int direction) {
        // create arrow and add final in list arrow instance of action Listener to timeArrow
        if (--mainController.attributeGame.numberArrow < 0) {
            mainController.attributeGame.numberArrow = 0;
            return;
        }
        arrow.add(new Arrow((int) player.xPosition, (int) player.yPosition + 20, direction, this, mainController));
        timeArrow.addActionListener(arrow.get(arrow.size() - 1));
        timeArrow.start();
        activeSkill1 = true;

    }

    private void secondSkill(int x, int y) {
        if (!activeSkill2) {
            tickPointX = x;
            tickPointY = y;
            tickRectPositionSkill2 = new Rectangle(x, y, 20, 20);
            tickRectPositionSkill2.generateGraphics(3, 0xFF00FF);
            activeSkill2 = true;
        } else {
            player.xPosition = tickPointX;
            player.yPosition = tickPointY;
            activeSkill2 = false;
        }
    }

    public void thirdSkill() {
        timeSkill3.start();
    }

    public void fourSkill() {

    }

    public void fifthSkill() {

    }

    public void sixSkill() {

    }
}
