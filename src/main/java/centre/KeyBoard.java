package centre;

import centre.controller.MainController;

import java.awt.event.*;

public class KeyBoard extends KeyAdapter implements KeyListener, FocusListener {
    private boolean[] k = new boolean[120];
    public MainController mainController;

    public KeyBoard(MainController mainController) {
        this.mainController = mainController;
    }

    public void focusLost(FocusEvent fe) {
        for (int i = 0; i < 120; i++)
            k[i] = false;
    }

    public void focusGained(FocusEvent fe) {

    }

    public void keyPressed(KeyEvent key) {
        int ke = key.getKeyCode();
//		get=ke;

        k[ke] = true;

    }

    public void keyReleased(KeyEvent key) {
        int ke = key.getKeyCode();
        control(mainController);
//        k[ke] = false;
    }

    public boolean isRight() {
        return k[KeyEvent.VK_RIGHT];
    }

    public boolean isLeft() {
        return k[KeyEvent.VK_LEFT];
    }

    public boolean isUp() {
        return k[KeyEvent.VK_UP];
    }

    public boolean isDown() {
        return k[KeyEvent.VK_DOWN];
    }

    public void control(MainController game) {
//        if (k[KeyEvent.VK_M]) {
//            game.getMapEdit().saveMap();
//            k[KeyEvent.VK_M] = false;
//        }
//        if (k[KeyEvent.VK_N]) {
//            game.getMapEdit().isDesign = !game.getMapEdit().isDesign;
//            k[KeyEvent.VK_N] = false;
//        }

        // skill
        if (k[KeyEvent.VK_W]) {
            game.skillManager.firstSkill(0);
            k[KeyEvent.VK_W] = false;
        }
        if (k[KeyEvent.VK_A]) {
            game.skillManager.firstSkill(1);
            k[KeyEvent.VK_A] = false;
        }
        if (k[KeyEvent.VK_S]) {
            game.skillManager.firstSkill(2);
            k[KeyEvent.VK_S] = false;
        }
        if (k[KeyEvent.VK_D]) {
            game.skillManager.firstSkill(3);
            k[KeyEvent.VK_D] = false;
        }
        if (k[KeyEvent.VK_Z]) {
            game.skillManager.activeSkill(1);
            k[KeyEvent.VK_Z] = false;
        }
        if (k[KeyEvent.VK_X] && game.player.hasTarget) {
            game.skillManager.activeSkill(2);
            k[KeyEvent.VK_X] = false;
            System.out.println("active");
        }

        // update mode
        if (k[KeyEvent.VK_U]) {
            game.isUpgrade = !game.isUpgrade;
            if (game.isUpgrade) {
                game.canvas.addMouseListener(game.upgradeStore);
            } else
                game.canvas.removeMouseListener(game.upgradeStore);
            k[KeyEvent.VK_U] = false;
            System.out.println("active update");
        }
        if (k[KeyEvent.VK_ESCAPE]) {
            game.isUpgrade = false;
            game.canvas.removeMouseListener(game.upgradeStore);
            k[KeyEvent.VK_ESCAPE] = false;
        }
    }
}
