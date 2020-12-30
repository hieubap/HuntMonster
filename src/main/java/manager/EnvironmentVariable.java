package manager;

import java.awt.*;

public class EnvironmentVariable {
    public static final String PATHRESOUREASSET = "D:\\IntelliJ\\Universal\\src\\asset";
    // environment
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    public static final int FPS = 60;
    public static final int DELAYPROCESS = 1000 / FPS;
    public static final Color COLOR_BACKGROUND_LIGHT = new Color(0x91b09a);
    public static final Color COLOR_BACKGROUND_BLACK = new Color(0x2f283a);
    public static final Color COLOR_GREEN_GEM = new Color(0x4c8b6d);
    public static final Color COLOR_PINK_GEM = new Color(0xcf0083);
    public static final Color COLOR_RED_GEM = new Color(0xb61717);
    public static final Color COLOR_BLUE_GEM = new Color(0x7590fc);
    public static final Color COLOR_GOLD_GEM = new Color(0xf8b13b);

    public static final int TIME_CREATE_ARROW_ = 2;
    public static final int MAX_NUMBER_ARROW_ = 100;


    // map
    public static final int PIXEL = 64;
    public static final int PIXEL_DESIGN = 20;
    public static final int NUMBER_SPRITE_WIDTH = 24;
    public static final int NUMBER_SPRITE_HEIGHT = 10;

    //player
    public static final int SPEED_PLAYER = 2;
    public static final int INIT_MODE_PLAYER_ = 8;
    public static final int INIT_DIRECTION_ = 3;
    public static final int SPEED_ANIMATION_PLAYER_ = 20;

    // weapon
    public static final int SPEED_ARROW_ = 10;
    public static final int DELAY_ARROW_ = 5;
    public static final int DELAY_CHASE_ = 5;
    public static final int SPEED_CHASE_ = 20;
    public static final int SPEED_SKILL_3_ = 10;
    public static final int INIT_NUMBER_ARROW = 30;
    public static final int DAMAGE_ARROW = 100;

    // monster
    public static final int DELAY_CREATE_MONSTER_ = 50;
    public static final int TIME_END_CREATE_MONSTER_ = 100;
    public static final int MAX_BLOOD_MONSTER = 10;

    // gem
    public static final double SCALE_CREATE_GEM_GREEN_ = 80;
    public static final double SCALE_CREATE_GEM_PINK_ = 12;
    public static final double SCALE_CREATE_GEM_RED_ = 5;
    public static final double SCALE_CREATE_GEM_BLUE_ = 2.5;
    public static final double SCALE_CREATE_GEM_YELLOW_ = 0.5;

    // update
    public static final int MAX_NUMBER_ARROW_TO_RENDER_ = 10;
    public static final int MAX_LEVEL_ARROW_UPGRADE = 7;
    public static final int[] NUMBER_GEM_GREEN_NEED_UPGRADE = {5, 10, 20, 35, 55, 80, 110};
    public static final int[] NUMBER_GEM_PINK_NEED_UPGRADE = {0, 0, 1, 2, 4, 8, 16};
    public static final int[] NUMBER_GEM_RED_NEED_UPGRADE = {0, 0, 0, 1, 2, 3, 4};
    public static final int[] NUMBER_GEM_BLUE_NEED_UPGRADE = {0, 0, 0, 0, 1, 2, 2};
    public static final int[] NUMBER_GEM_GOLD_NEED_UPGRADE = {0, 0, 0, 0, 0, 1, 2};
    public static final int[] DAMAGE_UPGRADE_ARROW_PER_LEVEL = {100, 200, 400, 800, 1600, 3200, 6400};

}
