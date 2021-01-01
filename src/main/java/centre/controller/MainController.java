package centre.controller;

import centre.KeyBoard;
import centre.Mouse;
import centre.UpgradeStore;
import enemy.Monster;
import gem.Gem;
import gem.GreenGem;
import manager.EnvironmentVariable;
import manager.ImageRenderHandle;
import map.MiniMap;
import my_lib.Camera;
import my_lib.ImageRender;
import my_lib.MapEdit;
import player.AttributeGame;
import player.Player;
import skill.SkillManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import static manager.EnvironmentVariable.*;

public class MainController extends JFrame {
    public static final double SCALE_GREEN = SCALE_CREATE_GEM_GREEN_;
    public static final double SCALE_PINK = SCALE_CREATE_GEM_PINK_;
    public static final double SCALE_RED = SCALE_CREATE_GEM_RED_;
    public static final double SCALE_BLUE = SCALE_CREATE_GEM_BLUE_;
    public static final double SCALE_YELLOW = SCALE_CREATE_GEM_YELLOW_;

    public static final int TIME_CREATE_ARROW = TIME_CREATE_ARROW_;
    public static int MAX_NUMBER_ARROW = MAX_NUMBER_ARROW_;
    public static final int DELAY_CREATE_MONSTER = DELAY_CREATE_MONSTER_;
    public static final int TIME_END_CREATE_MONSTER = TIME_END_CREATE_MONSTER_;

    public int timeGame = 0;

    public static int del = 0xFF000000;
    public Canvas canvas = new Canvas();
    private ImageRenderHandle renderer;
    public UpgradeStore upgradeStore;
    public boolean isUpgrade = false;

    public Player player;
    public AttributeGame attributeGame = new AttributeGame();
    private ArrayList<Monster> monster = new ArrayList<Monster>();
    private ArrayList<Gem> gemArrayList;

    private KeyBoard key;
    private Mouse mouse;
    private MapEdit map;

    public SkillManager skillManager;
    private int target, chooseTarget, timeCountAddMonster = 0;
    private ArrayList<Integer> monsterOnRange = new ArrayList<Integer>();

    private boolean isCreateMonster = true, isCreateNewArrow = false;
    private MiniMap minimap;
    private Graphics graphics;
    private BufferStrategy buffer;

    public MainController() {
        setTitle("Universal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(EnvironmentVariable.WIDTH,EnvironmentVariable.HEIGHT);
        setLocationRelativeTo(null);

        add(canvas);
        setVisible(true);

        key = new KeyBoard(this);
        mouse = new Mouse();
        upgradeStore = new UpgradeStore(this);

        canvas.createBufferStrategy(3);

        System.out.println(this.getClass().getResource(""));

        // create monster
        for (int i = 0; i < 2; i++)
            createMonster();

        buffer = canvas.getBufferStrategy();
        graphics = buffer.getDrawGraphics();
        renderer = new ImageRenderHandle(EnvironmentVariable.WIDTH, EnvironmentVariable.HEIGHT);
        map = new MapEdit(getCamera());
        minimap = new MiniMap(this);

        // create gem
        Random ran = new Random();
        gemArrayList = new ArrayList<Gem>();
        for (int i = 0; i < 20; i++)
            gemArrayList.add(new GreenGem(Math.abs(ran.nextInt()) % 2000,
                    Math.abs(ran.nextInt()) % 2000));

        player = new Player(1500, 1500);
        skillManager = new SkillManager(this);

        canvas.addMouseListener(map);
        canvas.addKeyListener(map);
        canvas.addKeyListener(key);
        canvas.addKeyListener(player);
        this.setFocusable(true);
    }

    public void update() {
        key.control(this);
        mouse.updateMouse(this);

        // create monster by time
        if (isCreateMonster) {
            timeCountAddMonster++;
            if (timeCountAddMonster % DELAY_CREATE_MONSTER == 0)
                createMonster();

            if (timeCountAddMonster > TIME_END_CREATE_MONSTER)
                isCreateMonster = false;
        }

        // update monster
        for (int i = 0; i < monster.size(); i++) {
            monster.get(i).update(this);
            monster.get(i).isTarget(this);
        }

        chooseTarget = -1;

        for (int i = 0; i < monsterOnRange.size(); i++)
            if (monsterOnRange.get(i) != 0) {
                target = monsterOnRange.get(i);
                chooseTarget = i;

                for (int j = i; j < monsterOnRange.size(); j++)
                    if (monsterOnRange.get(j) != 0 && monsterOnRange.get(j) < target) {
                        target = monsterOnRange.get(j);
                        skillManager.indexTargetMonster = j;
                        chooseTarget = j;
                    }
                break;
            }
        monsterOnRange.clear();

        // update player
        if (chooseTarget != -1) {
            player.setTarget(monster.get(chooseTarget).positionX, monster.get(chooseTarget).positionY);
            skillManager.targetX = monster.get(chooseTarget).positionX;
            skillManager.targetY = monster.get(chooseTarget).positionY;
        } else {
            player.noTarget();
        }
        player.update(this);

        // update gem
        for (int i = 0; i < gemArrayList.size(); i++) {
            if (gemArrayList.get(i).destroy()) {
                gemArrayList.remove(i);
                continue;
            }
            gemArrayList.get(i).update(this);
        }

        // update add arrow by time
        if (timeGame % TIME_CREATE_ARROW == 0 && !isCreateNewArrow && attributeGame.numberArrow < MAX_NUMBER_ARROW) {
            attributeGame.numberArrow++;
            isCreateNewArrow = true;
        }
        else if (timeGame % TIME_CREATE_ARROW != 0){
            isCreateNewArrow = false;
        }

        minimap.update(this);
        this.setFocusable(true);
    }

    public void render() {
        renderer.reset();
        map.drawMap(renderer);

        minimap.render(renderer);

        skillManager.render(renderer);

        for (Gem t : gemArrayList)
            t.render(renderer);

        for (int i = 0;i<monster.size();i++)
            monster.get(i).render(renderer, 1, 1);

        player.render(renderer);
        if (isUpgrade)
            renderer.setTransient(0x70);
        map.drawDesign(renderer);
    }

    public void draw() {
        buffer = canvas.getBufferStrategy();
        graphics = buffer.getDrawGraphics();
        super.paint(graphics);

        renderer.renderGame(graphics,this);
        if (isUpgrade){
            upgradeStore.draw(graphics);
        }
        graphics.dispose();
        buffer.show();
//        renderer.clear();
    }

    public static void main(String[] args) {
        MainController game = new MainController();
        ThreadGame threadGame = new ThreadGame(game);
        threadGame.start();
    }

    public Camera getCamera() {
        return renderer.camera;
    }

    public MapEdit getMapEdit() {
        return map;
    }

    public ImageRender getRender() {
        return renderer;
    }

    public void setTarget(int x, int y) {
        monsterOnRange.add(x + y);
    }

    public ArrayList<Monster> getMonster() {
        return monster;
    }

    public ArrayList<Gem> getGemArrayList() {
        return gemArrayList;
    }

    public void createGem(int a, int b) {
        int rangeRandom = 1000;
        int type = (int) (Math.random() * rangeRandom);

        if (type < (rangeRandom * SCALE_YELLOW) / 100) type = 4;
        else if (type < (rangeRandom * SCALE_BLUE) / 100) type = 3;
        else if (type < (rangeRandom * SCALE_RED) / 100) type = 2;
        else if (type < (rangeRandom * SCALE_PINK) / 100) type = 1;
        else type = 0;

        gemArrayList.add(Gem.createGemByType(type, a, b));
    }

    public void createMonster() {
        monster.add(new Monster(950, 1280, 1, false));
    }
}
