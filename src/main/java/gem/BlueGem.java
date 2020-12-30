package gem;

import centre.controller.MainController;
import manager.ImageManager;

public class BlueGem extends Gem {
    public BlueGem(int x, int y) {
        super(ImageManager.blueGemSheet, x, y, 0, 3);
    }

    @Override
    protected void addUpdate(MainController mainController) {
        mainController.attributeGame.numberBlueGem++;
    }
}
