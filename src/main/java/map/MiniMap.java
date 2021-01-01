package map;

import manager.EnvironmentVariable;
import manager.ImageRenderHandle;
import centre.Rectangle;
import centre.controller.MainController;
import my_lib.ImageRender;

import java.util.ArrayList;

public class MiniMap {
    private Rectangle map;
    private Rectangle play;
    private ArrayList<Rectangle> monster;


    public MiniMap(MainController game) {
        monster = new ArrayList<Rectangle>();
    }

    public void update(MainController game) {
        map = new Rectangle(game.getCamera().getX(), game.getCamera().getY(), EnvironmentVariable.WIDTH_MINI_MAP, EnvironmentVariable.HEIGHT_MINI_MAP);
        map.generateGraphics(1, 0xCC3f3f);

        Rectangle rect;

        for (int i = 0; i < game.getMonster().size(); i++) {
            int x = (int) (game.getMonster().get(i).positionX * EnvironmentVariable.SCALE_MAP_TO_MINI_MAP_X);
            int y = (int) (game.getMonster().get(i).positionY * EnvironmentVariable.SCALE_MAP_TO_MINI_MAP_Y);
            rect = new Rectangle(game.getCamera().getX() + x, game.getCamera().getY() + y, 3, 3);
            rect.generateGraphics(2, 0x602fcc);

            monster.add(rect);

//			map.getPixels()[x+y*200]=0x602fcc;

        }
        int x = (int) (game.player.xPosition * EnvironmentVariable.SCALE_MAP_TO_MINI_MAP_X);
        int y = (int) (game.player.yPosition * EnvironmentVariable.SCALE_MAP_TO_MINI_MAP_Y);

        play = null;
        play = new Rectangle(game.getCamera().getX() + x, game.getCamera().getY() + y, 9, 9);
        play.generateGraphics(2, 0xFFFFFF);

//		map.getPixels()[x+y*200]=0xFFFFFF;

    }

    public void render(ImageRender render) {
        if (map.getPixels() != null)
            render.renderArrayInt(map.getPixels(), map.w, map.h, map.x, map.y, 1, 1);

        render.renderArrayInt(play.getPixels(), 9, 9, play.x, play.y, 1, 1);

        for (int i = 0; i < monster.size(); i++)
            render.renderArrayInt(monster.get(i).getPixels(), 3, 3,
                    monster.get(i).x, monster.get(i).y, 1, 1);
        monster.clear();
    }
}
