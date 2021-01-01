package my_lib;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Sprite {
    protected int[] pixels;
    protected int width, height;

    public Sprite(BufferedImage buffer) {
        width = buffer.getWidth();
        height = buffer.getHeight();
        pixels = new int[width * height];

        pixels = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
    }

    public Sprite(Sprite buffer, int startX, int startY, int w, int h) {
        width = w;
        height = h;

        pixels = new int[w * h];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                pixels[j + i * w] = buffer.getPixel((j + startX) + (i + startY) * buffer.getWidth());
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getPixel(int i) {
        return pixels[i];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
