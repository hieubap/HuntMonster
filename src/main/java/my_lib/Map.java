package my_lib;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Map {
    protected final int PIXEL = VariableEnvironment.PIXEL_GAME;
    protected static final Sheet sheet = new Sheet(SpriteManager.mapSprite, VariableEnvironment.WIDTH_CHILD_SHEET, VariableEnvironment.HEIGHT_CHILD_SHEET);

    public int[][] dataMap;
    protected boolean[][] isMove;

    protected int numberTileXMap, numberTileYMap;
    protected Camera camera;

    public Map(Camera camera) {
        this.camera = camera;
        loadMapData();
    }

    public void loadMapData() {
        try {
            int i = 0;
            File fileSaveDataMap = new File(VariableEnvironment.PATH_FILE_OPEN_MAP_DATA);
            Scanner sc = new Scanner(fileSaveDataMap);

            String line = sc.nextLine();

            while (sc.hasNextLine()) {
                if (line.startsWith("// size:")) {
                    line = sc.nextLine();
                    String[] split = line.split(",");

                    numberTileXMap = Integer.parseInt(split[0]);
                    numberTileYMap = Integer.parseInt(split[1]);

                    dataMap = new int[numberTileXMap][numberTileYMap];
                    isMove = new boolean[numberTileXMap][numberTileYMap];

                    line = sc.nextLine();
                    continue;
                }

                String[] split = line.split("-");

                for (int j = 0; j < numberTileXMap; j++) {
                    dataMap[j][i] = Integer.parseInt(split[j]);
                    System.out.print("-"+dataMap[j][i]);
                    isMove[j][i] = dataMap[j][i] == 101 || dataMap[j][i] == 602;
                }
                System.out.println();

                line = sc.nextLine();
                i++;
            }

            sc.close();
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void drawMap(ImageRender imageRender) {
        try {
            for (int i = camera.positionY / PIXEL; i < camera.positionY / PIXEL + camera.height / PIXEL + 2; i++)
                for (int j = camera.positionX / PIXEL; j < camera.positionX / PIXEL + camera.width / PIXEL + 2; j++)
                    if (i > -1 && j > -1 && j < numberTileXMap && i < numberTileYMap)
                    {
                        imageRender.renderSprite(sheet.getSprite(dataMap[j][i]/1000000,dataMap[j][i]/10000%100),
                                j * PIXEL , i * PIXEL ,
                                VariableEnvironment.SCALE_CHANGE_IMAGE_TO_GAME, VariableEnvironment.SCALE_CHANGE_IMAGE_TO_GAME);
                        imageRender.renderSprite(sheet.getSprite(dataMap[j][i]/100%100,dataMap[j][i]%100),
                                j * PIXEL , i * PIXEL ,
                                VariableEnvironment.SCALE_CHANGE_IMAGE_TO_GAME, VariableEnvironment.SCALE_CHANGE_IMAGE_TO_GAME);
                    }

        } catch (ArrayIndexOutOfBoundsException ai) {
            ai.printStackTrace();
        }
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

}
