package my_lib;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;

public class MapEdit extends Map implements MouseListener, KeyListener {
    protected final int PIXEL = VariableEnvironment.PIXEL_GAME;
    protected final int NUMBER_SPRITE_X = VariableEnvironment.NUMBER_SPRITE_WIDTH;
    protected final int NUMBER_SPRITE_Y = VariableEnvironment.NUMBER_SPRITE_HEIGHT;

    public MapEdit(Camera camera) {
        super(camera);
        super.loadMapData();
        this.camera = camera;
    }

    public void drawMap(ImageRender imageRender) {
        super.drawMap(imageRender);

//        if (isDesign){
//            drawDesign(imageRender);
//        }
//        try {
//            for (int i = camera.positionY / PIXEL; i < camera.positionY / PIXEL + camera.height / PIXEL + 2; i++)
//                for (int j = camera.positionX / PIXEL; j < camera.positionX / PIXEL + camera.width / PIXEL + 2; j++)
//                    if (i > -1 && j > -1 && j < numberTileX && i < numberTileY)
//                        g.drawImage(getImage(dataMap[j][i]), j * PIXEL - camera.positionX, i * PIXEL - camera.positionY, PIXEL, PIXEL, null);
//        } catch (ArrayIndexOutOfBoundsException ai) {
//            ai.printStackTrace();
//        }
    }

    public boolean canMove(int x, int y) {
        try {
            if (x % PIXEL == 0 && y % PIXEL == 0)
                return isMove[x / PIXEL][y / PIXEL];
            else {
                return isMove[x / PIXEL][y / PIXEL]
                        && isMove[x / PIXEL + 1][y / PIXEL]
                        && isMove[x / PIXEL][y / PIXEL + 1]
                        && isMove[x / PIXEL + 1][y / PIXEL + 1];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    //////////////////
    public boolean isDesign = false, isBackground = false;
    private boolean changePositionMap = true;
    private int positionMapMeshX = 0, positionMapMeshY = 0;
    private int xSetTile = 0, ySetTile = 0;

    public void saveMap() {
        try {
            System.out.println("Run save map to '" + VariableEnvironment.PATH_FILE_OPEN_MAP_DATA + "'. Successful");
            FileWriter fw = new FileWriter(VariableEnvironment.PATH_FILE_SAVE_MAP_DATA);

            fw.write("// size: \n");
            fw.write(numberTileXMap + "," + numberTileYMap + "\n");

            for (int i = 0; i < numberTileXMap; i++) {
                for (int j = 0; j < numberTileYMap; j++) {
                    fw.write(dataMap[j][i] + "-");
                }
                fw.write("\n");
            }
            fw.write("// end");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (changePositionMap) {
            positionMapMeshX = 0;
            positionMapMeshY = 0;
        } else {
            positionMapMeshX = VariableEnvironment.WIDTH - VariableEnvironment.NUMBER_SPRITE_WIDTH * (VariableEnvironment.PIXEL_DESIGN + 1);
            positionMapMeshY = VariableEnvironment.HEIGHT - VariableEnvironment.NUMBER_SPRITE_HEIGHT * (VariableEnvironment.PIXEL_DESIGN + 2);
        }
    }

    public void drawDesign(ImageRender imageRender) {
        if (!isDesign) return;

        imageRender.renderSprite(SpriteManager.mapSprite, positionMapMeshX + camera.getX(), positionMapMeshY + camera.getY(),
                (double) (NUMBER_SPRITE_X * VariableEnvironment.PIXEL_DESIGN) / SpriteManager.mapSprite.getWidth(),
                (double) (NUMBER_SPRITE_Y * VariableEnvironment.PIXEL_DESIGN) / SpriteManager.mapSprite.getHeight());

        for (int i = 0; i < NUMBER_SPRITE_X; i++)
            for (int j = 0; j < NUMBER_SPRITE_Y; j++)
                imageRender.renderRect(positionMapMeshX + i * VariableEnvironment.PIXEL_DESIGN, positionMapMeshY + j * VariableEnvironment.PIXEL_DESIGN,
                        VariableEnvironment.PIXEL_DESIGN, VariableEnvironment.PIXEL_DESIGN, 0xFF000000);

        if (isBackground) {
            imageRender.renderRect(0, 0, 10, 10, 0xFFFFFF00);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // set Tile Type
        int x = (e.getX() - positionMapMeshX) / VariableEnvironment.PIXEL_DESIGN;
        int y = (e.getY() - positionMapMeshY) / VariableEnvironment.PIXEL_DESIGN;

        if (x < NUMBER_SPRITE_X && x >= 0 && y < NUMBER_SPRITE_Y && y >= 0) {
            System.out.println("Set tile set x = " + x + " y = " + y);
            xSetTile = x;
            ySetTile = y;
            return;
        }

        // set map
        x = (e.getX() + camera.positionX) / PIXEL;
        y = (e.getY() + camera.positionY) / PIXEL;
        if (isDesign) {
            if (isBackground)
                dataMap[x][y] = xSetTile * 1000000 + ySetTile * 10000 + (dataMap[x][y]%10000);
            else
                dataMap[x][y] = xSetTile * 100 + ySetTile + (dataMap[x][y]/10000)*10000;
            System.out.println("set map = " + dataMap[x][y]);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_M -> isDesign = !isDesign;
            case KeyEvent.VK_C -> {
                changePositionMap = !changePositionMap;
                update();
            }
            case KeyEvent.VK_B -> {
                if (isDesign)
                    isBackground = !isBackground;
            }
            case KeyEvent.VK_N -> saveMap();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}
