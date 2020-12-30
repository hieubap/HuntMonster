package gem;

import centre.controller.MainController;
import manager.ImageManager;

public class RedGem extends Gem{
    public RedGem(int x, int y) {
        super(ImageManager.redGemSheet, x, y, 0, 1);
    }

    @Override
    protected void addUpdate(MainController mainController) {
        mainController.attributeGame.numberRedGem++;
    }
}
