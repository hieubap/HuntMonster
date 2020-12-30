package centre;

import centre.controller.MainController;

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
        if (game.getRender().isDesign() && settype && settile && !setMap.contains(xPosition, yPosition)) {
            game.getMap().addTile((xPosition + game.getCamera().x) / 64 * 64,
                    (yPosition + game.getCamera().y) / 64 * 64, tilex, tiley);
            settile = false;
        }
    }
}
