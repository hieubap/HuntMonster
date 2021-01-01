package centre;

import centre.controller.MainController;
import my_lib.VariableEnvironment;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Mouse extends MouseAdapter {
    public int xPosition, yPosition, tilex, tiley;
    private Rectangle2D setMap = new Rectangle2D.Float(0, 0, 16 * 24, 16 * 10);
    private boolean settile = false, settype = false;

    public void mousePressed(MouseEvent mouse) {
        xPosition = mouse.getX();
        yPosition = mouse.getY();

        if (setMap.contains(xPosition, yPosition)) {
            tilex = xPosition / 16;
            tiley = yPosition / 16;
            settype = true;
        }
        settile = true;
    }

    public void mouseReleased(MouseEvent mouse) {

    }

    public void updateMouse(MainController game) {
        if (game.getMapEdit().isDesign && settype && settile && !setMap.contains(xPosition, yPosition)) {
            int pixel = VariableEnvironment.PIXEL_GAME;
            game.getMapEdit().dataMap[(xPosition + game.getCamera().positionX) / pixel * pixel]
                    [(yPosition + game.getCamera().positionY) / pixel * pixel] = tilex * 100 + tiley;
            settile = false;
        }
    }
}
