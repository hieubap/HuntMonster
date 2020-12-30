package gem;

import centre.controller.MainController;
import manager.ImageManager;

public class YellowGem extends Gem {
    public YellowGem(int x, int y) {
        super(ImageManager.yellowGemSheet, x, y, 0, 4);
    }

    @Override
    protected void addUpdate(MainController mainController) {
        mainController.attributeGame.numberYellowGem++;
    }
}
