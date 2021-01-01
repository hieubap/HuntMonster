package manager;

import my_lib.ImageRender;
import my_lib.Sprite;
import centre.Rectangle;
import centre.controller.MainController;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import static manager.EnvironmentVariable.*;

public class ImageRenderHandle extends ImageRender {
    public static final int MAX_NUMBER_ARROW_TO_CHANGE_RENDER = MAX_NUMBER_ARROW_TO_RENDER_;

    private BufferedImage rotateObject;
    private int[]  pixelRotate;
//    public Rectangle2D tileType;
//    private Sprite tileSetMap;

    private boolean design = false;

    public ImageRenderHandle(int width, int height) {
        super(width, height);
        rotateObject = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);

        pixelRotate = ((DataBufferInt) rotateObject.getRaster().getDataBuffer()).getData();
    }

//    public void reset() {
//        for (int i = 0; i < pixels.length; i++)
//            pixels[i] = 0xFF91B09A;
//    }
//
//    public void renderArrayInt(int[] renderpixel, int renderwidth, int renderheight, int xposition, int yposition, double xzoom, double yzoom) {
//        for (int y = 0; y < (renderheight * yzoom); y++)
//            for (int x = 0; x < (renderwidth * xzoom); x++)
////                for (int yz = 0; yz < yzoom; yz++)
////                    for (int xz = 0; xz < xzoom; xz++)
//                setPixel(renderpixel[(int)(x/xzoom) + (int)(y/yzoom) * (renderwidth)] | 0xFF000000, x + xposition, y + yposition);
//
//    }
//
//
    public void renderGame(Graphics graphics, MainController game) {
        // render number arrow
        if (game.attributeGame.numberArrow <= MAX_NUMBER_ARROW_TO_CHANGE_RENDER)
            for (int i = 0; i < game.attributeGame.numberArrow; i++) {
                renderSprite(ImageManager.arrowSprite4Direction[2], game.getCamera().getX() + WIDTH - (i + 4) * 16, game.getCamera().getY() + HEIGHT - 32 * 3, 1, 1);
            }
        else
            renderSprite(ImageManager.arrowSprite4Direction[2], game.getCamera().getX() + WIDTH - PIXEL * 5 / 2, game.getCamera().getY() + HEIGHT - 32 * 3, 1, 1);

        // render gem attribute
        renderSprite(ImageManager.greenGemSheet, game.getCamera().getX(), game.getCamera().getY() + HEIGHT - PIXEL * 2, 1, 1);
        renderSprite(ImageManager.pinkGemSheet, 100 + game.getCamera().getX(), game.getCamera().getY() + HEIGHT - PIXEL * 2, 1, 1);
        renderSprite(ImageManager.redGemSheet, 200 + game.getCamera().getX(), game.getCamera().getY() + HEIGHT - PIXEL * 2, 1, 1);
        renderSprite(ImageManager.blueGemSheet, 300 + game.getCamera().getX(), game.getCamera().getY() + HEIGHT - PIXEL * 2, 1, 1);
        renderSprite(ImageManager.yellowGemSheet, 400 + game.getCamera().getX(), game.getCamera().getY() + HEIGHT - PIXEL * 2, 1, 1);

        graphics.drawImage(view, 0, 0, view.getWidth(), view.getHeight(), null);

        drawAttribute(graphics, game);

        if (design)
            for (int i = 0; i < NUMBER_SPRITE_HEIGHT; i++)
                for (int j = 0; j < NUMBER_SPRITE_WIDTH; j++) {
                    graphics.drawRect(j * PIXEL_DESIGN, i * PIXEL_DESIGN, PIXEL_DESIGN, PIXEL_DESIGN);
                }
    }

    public void drawAttribute(Graphics graphics, MainController game) {
        graphics.setFont(new Font(Font.SANS_SERIF, 5, 20));
        graphics.setColor(Color.white);

        // render time play
        graphics.drawString(game.timeGame / 60 + ":" + game.timeGame % 60, WIDTH / 2, PIXEL / 2);

        graphics.drawString("exp : " + game.attributeGame.experience, WIDTH - 100, 20);
        graphics.drawString("life: " + game.attributeGame.bloodPlayer, WIDTH - 100, 50);
        graphics.drawString("level: " + game.attributeGame.level, WIDTH - 100, 80);

        if (game.attributeGame.numberArrow > MAX_NUMBER_ARROW_TO_CHANGE_RENDER)
            graphics.drawString("" + game.attributeGame.numberArrow + "/" + game.MAX_NUMBER_ARROW, WIDTH - PIXEL * 2, HEIGHT - PIXEL);

        graphics.setColor(COLOR_GREEN_GEM);
        graphics.drawString("" + game.attributeGame.numberGreenGem, PIXEL, HEIGHT - PIXEL * 3 / 2);
        graphics.setColor(COLOR_PINK_GEM);
        graphics.drawString("" + game.attributeGame.numberPinkGem, 100 + PIXEL, HEIGHT - PIXEL * 3 / 2);
        graphics.setColor(COLOR_RED_GEM);
        graphics.drawString("" + game.attributeGame.numberRedGem, 200 + PIXEL, HEIGHT - PIXEL * 3 / 2);
        graphics.setColor(COLOR_BLUE_GEM);
        graphics.drawString("" + game.attributeGame.numberBlueGem, 300 + PIXEL, HEIGHT - PIXEL * 3 / 2);
        graphics.setColor(COLOR_GOLD_GEM);
        graphics.drawString("" + game.attributeGame.numberYellowGem, 400 + PIXEL, HEIGHT - PIXEL * 3 / 2);
    }
//
//    private void setPixel(int pixel, int x, int y) {
//        if (x >= camera.getX() && y >= camera.getY() && x <= camera.getX() + camera.width && y <= camera.getY() + camera.height) {
//            int pixelIndex = (x - camera.getX()) + (y - camera.getY()) * view.getWidth();
//
//            if (pixels.length > pixelIndex && pixel != MainController.del)
//                pixels[pixelIndex] = pixel;
//        }
//
//    }
//
//    public void renderSprite(Sprite sprite, int xPosition, int yPosition, double xZoom, double yZoom) {
//        renderArrayInt(sprite.getPixels(), sprite.getWidth(), sprite.getHeight(), xPosition, yPosition, xZoom, yZoom);
//    }
//
//    public void renderDesign() {
//        if (design) {
//            renderSprite(tileSetMap, camera.getX(), camera.getY(), (double) (PIXEL_DESIGN * NUMBER_SPRITE_WIDTH) / 1024, (double) (PIXEL_DESIGN * NUMBER_SPRITE_HEIGHT) / 1024);
//        }
//    }
//
//    public void changeDesign() {
//        if (design) {
//
//            design = false;
//            return;
//        }
//        design = true;
//    }
//
//    public void setTransient(int value) {
//        for (int i = 0; i < pixels.length; i++)
//            pixels[i] = (pixels[i] & 0xFFFFFF) | ((value % 0xFF) << 24);
//    }
//
//    public boolean isDesign() {
//        return design;
//    }
//
//    //
//    public void renderArrayRotate(int[] renderpixel, int renderwidth, int renderheight, int xposition, int yposition, int xzoom, int yzoom) {
//
//        for (int y = 0; y < renderheight; y++)
//            for (int x = 0; x < renderwidth; x++)
//                for (int yz = 0; yz < yzoom; yz++)
//                    for (int xz = 0; xz < xzoom; xz++)
//                        setPixelRotate(renderpixel[x + y * renderwidth], (x * xzoom) + xposition + xz, (y * yzoom) + yposition + yz);
//
//    }
//
//    private void setPixelRotate(int pixel, int x, int y) {
//        if (x >= camera.getX() && y >= camera.getY() && x <= camera.getX() + camera.width && y <= camera.getY() + camera.height) {
//            int pixelIndex = (x - camera.getX()) + (y - camera.getY()) * view.getWidth();
//
//            if (pixels.length > pixelIndex && pixel != MainController.del)
//                pixelRotate[pixelIndex] = pixel;
//        }
//
//    }
}