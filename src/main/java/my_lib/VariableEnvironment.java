package my_lib;

import java.awt.*;

public class VariableEnvironment {
    // environment
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    public static final int PIXEL_GAME = 64;
    public static final int SIZE_MAP_X = PIXEL_GAME*50;
    public static final int SIZE_MAP_Y = PIXEL_GAME*50;
    public static final int FPS = 60;
    public static final int DELAYPROCESS = 1000 / FPS;

    public static final String PATHRESOUREASSET = "D:/IntelliJ/HuntMonster/src/main/java/asset";
    public static final String PATH_FILE_SAVE_MAP_DATA = "D:/IntelliJ/HuntMonster/src/main/java/asset/dataMap2.txt";
    public static final String PATH_FILE_OPEN_MAP_DATA = "D:/IntelliJ/HuntMonster/src/main/java/asset/dataMap2.txt";
    public static final String PATH_MAP_IMAGE = "/terrain.png";



    // map
    public static final int PIXEL_DESIGN = 20;
    public static final int NUMBER_SPRITE_WIDTH = 32;
    public static final int NUMBER_SPRITE_HEIGHT = 32;
    public static final int WIDTH_CHILD_SHEET = SpriteManager.mapSprite.getWidth()/NUMBER_SPRITE_WIDTH;
    public static final int HEIGHT_CHILD_SHEET = SpriteManager.mapSprite.getHeight()/NUMBER_SPRITE_HEIGHT;
    public static final double SCALE_CHANGE_IMAGE_TO_GAME = (double) (NUMBER_SPRITE_WIDTH*PIXEL_GAME) / SpriteManager.mapSprite.getWidth();


    // speed
    public static final int SPEED_PLAYER = 5;
    public static final int SPEED_CAMERA = 5;
    public static final int SPEED_ENEMY = 5;
    public static final int SPEED_SHOT = 11;
    public static final int SPEED_NORMAL_SHOT = SPEED_SHOT;
    public static final int SPEED_FIRE_SHOT = SPEED_SHOT;
    public static final int SPEED_WATER_SHOT = SPEED_SHOT;
    public static final int SPEED_EARTH_SHOT = SPEED_SHOT;
    public static final int SPEED_METAL_SHOT = SPEED_SHOT;
    public static final int SPEED_WOOD_SHOT = SPEED_SHOT;

    // distance
    public static final int RANGE_TO_CAMERA_MOVE = 200;
}
