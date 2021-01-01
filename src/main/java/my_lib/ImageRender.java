package my_lib;

import centre.controller.MainController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class ImageRender {
    protected BufferedImage view;
    public Camera camera;
    protected int[] pixels;

    public ImageRender(int width, int height) {
        view = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        camera = new Camera(0, 0, width, height);

        pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();
    }

    public void renderGame(Graphics graphics) {
        graphics.drawImage(view, 0, 0, null);
    }

    public void reset() {
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = 0xFF156c99;
    }

    public void renderArrayInt(int[] pixels, int width, int height, int positionX, int positionY, double zoomX, double zoomY) {
        for (int y = 0; y < (height * zoomY); y++)
            for (int x = 0; x < (width * zoomX); x++)
                setPixel(pixels[(int) (x / zoomX) + (int) (y / zoomY) * (width)] | 0xFF000000, x + positionX, y + positionY);
    }

    private void setPixel(int pixel, int x, int y) {
        if (x >= camera.getX() && y >= camera.getY() && x <= camera.getX() + camera.getWidth() && y <= camera.getY() + camera.getHeight()) {
            int pixelIndex = (x - (int) camera.getX()) + (y - (int) camera.getY()) * view.getWidth();

            if (pixels.length > pixelIndex && pixel != MainController.del)
                pixels[pixelIndex] = pixel;
        }
    }

    public void renderSprite(Sprite sprite, int xPosition, int yPosition, double zoomX, double zoomY) {
        renderArrayInt(sprite.getPixels(), sprite.getWidth(), sprite.getHeight(), xPosition, yPosition, zoomX, zoomY);
    }

    public void renderRect(int x, int y, int w, int h, int color) {
        for (int i = 0; i < w; i++)
            pixels[x + i + view.getWidth() * y] = color;
        for (int i = 0; i < w; i++)
            pixels[x + i + view.getWidth() * (y + h)] = color;
        for (int j = 0; j < h; j++)
            pixels[x + view.getWidth() * (y + j)] = color;
        for (int j = 0; j < h; j++)
            pixels[x +w + view.getWidth() * (y + j)] = color;

    }

    public void setTransient(int value) {
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = (pixels[i] & 0xFFFFFF) | ((value % 0xFF) << 24);
    }

}