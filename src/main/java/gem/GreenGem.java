package gem;

import centre.controller.MainController;
import manager.ImageManager;

public class GreenGem extends Gem{
    public GreenGem(int x, int y) {
        super(ImageManager.greenGemSheet, x, y, 0, 2);
    }

    @Override
    protected void addUpdate(MainController mainController) {
        mainController.attributeGame.numberGreenGem++;
    }
}
