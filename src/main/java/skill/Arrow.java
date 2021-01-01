package skill;

import centre.controller.MainController;
import manager.EnvironmentVariable;
import manager.ImageManager;
import my_lib.ImageRender;
import my_lib.Sprite;
import my_lib.VariableEnvironment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Arrow implements ActionListener {
    private static final int SPEED_ARROW = EnvironmentVariable.SPEED_ARROW_;
    public static int DAMAGE = EnvironmentVariable.DAMAGE_ARROW;

    private SkillManager power;
    public int xPosition, yPosition, direction;
    private MainController game;
    private ArrayList<Arrow> arrowList;

    public Arrow(int xpo, int ypo, int direction, SkillManager supo, MainController game) {
        arrowList = supo.arrow;
        this.power = supo;
        this.game = game;
        xPosition = xpo;
        yPosition = ypo;
        this.direction = direction;
    }

    long timeTest;

    public void actionPerformed(ActionEvent action) {
//        System.out.println("size = " + arrowList.size() + " timeTest = "+ arrowList.indexOf(this)+" -- " + (System.currentTimeMillis()-timeTest));
        timeTest = System.currentTimeMillis();
        update();
        attackMonster();
    }

    public void update() {
        switch (direction) {
            case 0:
                yPosition -= SPEED_ARROW;
                break;
            case 1:
                xPosition -= SPEED_ARROW;
                break;
            case 2:
                yPosition += SPEED_ARROW;
                break;
            case 3:
                xPosition += SPEED_ARROW;
                break;
        }
    }

    public void attackMonster() {
        if (isDestroy()) {
            power.arrow.remove(this);
            power.timeArrow.removeActionListener(this);

            if (power.arrow.size() == 0)
                power.timeArrow.stop();
        }
        for (int i = 0; i < game.getMonster().size(); i++) {
            if (game.getMonster().get(i).isCollision(xPosition, yPosition)) {
//                time.stop();
                power.arrow.remove(this);
                power.timeArrow.removeActionListener(this);
                game.getMonster().get(i).blood -= DAMAGE;

                if (game.getMonster().get(i).blood <= 0) {
                    Random ran = new Random();

                    for (int j = 0; j < 10; j++)
                        game.createGem(game.getMonster().get(i).positionX + ran.nextInt() % 100,
                                game.getMonster().get(i).positionY + ran.nextInt() % 100);

                    game.getMonster().remove(i);
                }
                return;
            }
        }
    }

    public void render(ImageRender render) {
        switch (direction) {
            case 0: {
                render.renderSprite(ImageManager.arrowSprite4Direction[0], xPosition, yPosition, 1, 1);
                break;
            }
            case 1: {
                render.renderSprite(ImageManager.arrowSprite4Direction[1], xPosition, yPosition, 1, 1);
                break;
            }
            case 2: {
                render.renderSprite(ImageManager.arrowSprite4Direction[2], xPosition, yPosition, 1, 1);
                break;
            }
            case 3: {
                render.renderSprite(ImageManager.arrowSprite4Direction[3], xPosition, yPosition, 1, 1);
                break;
            }
        }
    }

    public boolean isDestroy() {
        if (xPosition > VariableEnvironment.SIZE_MAP_X || yPosition > VariableEnvironment.SIZE_MAP_Y || xPosition < 0 || yPosition < 0)
            return true;
        return false;
    }
}