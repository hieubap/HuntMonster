package my_lib;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Sheet {
    public int column, line, width, height;
    private Sprite[] listChildSprite;

    public Sheet(BufferedImage buffer, int widthChildSheet, int heightChildSheet) {
        width = buffer.getWidth();
        height = buffer.getHeight();

        column = width / widthChildSheet;
        line = height / heightChildSheet;
        listChildSprite = new Sprite[column * line];

        Sprite sprite = new Sprite(buffer);

        for (int i = 0; i < line; i++)
            for (int j = 0; j < column; j++)
                listChildSprite[i * column + j] = new Sprite(sprite, j * widthChildSheet, i * heightChildSheet, widthChildSheet, heightChildSheet);
    }
    public Sheet(Sprite buffer, int widthChildSheet, int heightChildSheet) {
        width = buffer.getWidth();
        height = buffer.getHeight();

        column = width / widthChildSheet;
        line = height / heightChildSheet;
        listChildSprite = new Sprite[column * line];

        for (int i = 0; i < line; i++)
            for (int j = 0; j < column; j++)
                listChildSprite[i * column + j] = new Sprite(buffer, j * widthChildSheet, i * heightChildSheet, widthChildSheet, heightChildSheet);
    }

    public Sprite getSprite(int column, int line) throws ArrayIndexOutOfBoundsException{
        return listChildSprite[column + line * this.column];
    }

    public Sprite getSprite(int index) {
        return listChildSprite[index];
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

}
