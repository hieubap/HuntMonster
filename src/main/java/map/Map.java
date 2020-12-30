package map;

import manager.ImageRenderHandle;
import animation.Sheet;
import animation.Sprite;
import manager.ImageManager;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static manager.EnvironmentVariable.PIXEL;

public class Map {
    private ArrayList<Tile> tileset = new ArrayList<Tile>();
    private Sheet sheet;
    private File fileSaveMap;

    public Map() {
        sheet = new Sheet(ImageManager.loadImage("/dungeon_sheet.png"), 64, 64);
        fileSaveMap = new File("D:/tilesave.txt");

        try {
            Scanner sc = new Scanner(fileSaveMap);

            String line = sc.nextLine();

            while (sc.hasNextLine()) {
                if (line.startsWith("//"))
                    continue;
                String[] split = line.split(",");
                tileset.add(new Tile(sheet.getSprite(
                        Integer.parseInt(split[0]),
                        Integer.parseInt(split[1])),
                        Integer.parseInt(split[2]),
                        Integer.parseInt(split[3]),
                        Integer.parseInt(split[0]),
                        Integer.parseInt(split[1])));
                line = sc.nextLine();
            }
            sc.close();
            return;
        } catch (IOException e) {
            e.printStackTrace();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void render(ImageRenderHandle render) {
        Rectangle2D rect =
                new Rectangle2D.Float(render.camera.x - PIXEL, render.camera.y - PIXEL,
                        render.camera.w + PIXEL, render.camera.h + PIXEL);

        for (int i = 0; i < tileset.size(); i++)
            if (rect.contains(tileset.get(i).xPosition, tileset.get(i).yPosition))
                render.renderSprite(tileset.get(i).getSprite(),
                        tileset.get(i).xPosition,
                        tileset.get(i).yPosition,
                        1, 1);
    }

    public void saveMap() {
        try {
            FileWriter fw = new FileWriter(fileSaveMap);

            for (int i = 0; i < tileset.size(); i++)
                fw.write(tileset.get(i).tilex + ","
                        + tileset.get(i).tiley + ","
                        + tileset.get(i).xPosition + ","
                        + tileset.get(i).yPosition + "\n");

            fw.write("// end");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTile(int xPosition, int yPosition, int x, int y) {
        for (int i = 0; i < tileset.size(); i++)
            if (tileset.get(i).xPosition == xPosition &&
                    tileset.get(i).yPosition == yPosition) {
                tileset.remove(i);
                break;
            }
        tileset.add(new Tile(sheet.getSprite(x, y),
                xPosition, yPosition, x, y));
    }

    public class Tile {
        private Sprite sprite;
        public int xPosition, yPosition, tilex, tiley;

        public Tile(Sprite sprite, int xPosition, int yPosition, int x, int y) {
            this.sprite = sprite;
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            tilex = x;
            tiley = y;
        }

        public Sprite getSprite() {
            return sprite;
        }
    }

//    public BufferedImage loadImage(String path) {
//        try {
//            BufferedImage loadedImage = ImageIO.read(this.getClass().getResource(path));
//            BufferedImage formatImage = new BufferedImage(loadedImage.getWidth(),
//                    loadedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
//
//            formatImage.getGraphics().drawImage(loadedImage, 0, 0, null);
//
//            return formatImage;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
