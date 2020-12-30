package gem;

import centre.controller.MainController;
import manager.ImageManager;

public class PinkGem extends Gem{
    public PinkGem(int x, int y) {
        super(ImageManager.pinkGemSheet, x, y, 0, 1);
    }

    @Override
    protected void addUpdate(MainController mainController) {
        mainController.attributeGame.numberPinkGem++;
    }
}
