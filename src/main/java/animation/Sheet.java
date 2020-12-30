package animation;

import animation.Sprite;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Sheet {
    private int[] pixels;
    public int column, line, width, height;
    private Sprite[] listChildSheet;

    public Sheet(BufferedImage buffer, int widthChildSheet, int heightChildSheet) {
        width = buffer.getWidth();
        height = buffer.getHeight();
        pixels = new int[width * height];
        pixels = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();

        column = width / widthChildSheet;
        line = height / heightChildSheet;
        listChildSheet = new Sprite[column * line];

        for (int i = 0; i < line; i++)
            for (int j = 0; j < column; j++)
                listChildSheet[i * column + j] = new Sprite(this, j * widthChildSheet, i * heightChildSheet, widthChildSheet, heightChildSheet);
    }

    public Sprite getSprite(int i, int j) {
        return listChildSheet[i + j * column];
    }

    public Sprite getSprite(int i) {
        return listChildSheet[i];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

    public int getPixel(int i) {
        return pixels[i];
    }

}
