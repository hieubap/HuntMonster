package manager;

import animation.Sheet;
import animation.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import static manager.EnvironmentVariable.PIXEL;

public class ImageManager {
    public static String resourcePath = EnvironmentVariable.PATHRESOUREASSET;

    public static Sheet monsterSheet;
    public static Sheet playerSheet;
    public static Sheet adSheet;
    public static Sheet warriorSheet;
    public static Sheet spellSheet;
    // weapon
    public static Sprite[] arrowSprite4Direction; // up left down right

    // map object
    public static BufferedImage mapImage;

    // gem
    public static final Sprite greenGemSheet;
    public static final Sprite pinkGemSheet;
    public static final Sprite redGemSheet;
    public static final Sprite blueGemSheet;
    public static final Sprite yellowGemSheet;


    static {
        // weapon
        arrowSprite4Direction = new Sprite[4];
        arrowSprite4Direction[0] = new Sprite(loadImage("/muiten-up.png"));
        arrowSprite4Direction[1] = new Sprite(loadImage("/muiten.png"));
        arrowSprite4Direction[2] = new Sprite(loadImage("/muiten-down.png"));
        arrowSprite4Direction[3] = new Sprite(loadImage("/muiten-right.png"));

        // player
        playerSheet = new Sheet(loadImage("/oldman.png"), PIXEL, PIXEL);
        warriorSheet = new Sheet(loadImage("/warior.png"), PIXEL, PIXEL);
        adSheet = new Sheet(loadImage("/adcharacter.png"), PIXEL, PIXEL);
        spellSheet = new Sheet(loadImage("/spell.png"), PIXEL, PIXEL);

        monsterSheet = new Sheet(loadImage("/monster.png"), PIXEL, PIXEL);
        mapImage = ImageManager.loadImage("/mapSprite.png");

        // gem
        greenGemSheet = new Sprite(eraserBackgroundImage(loadImage("/Gem/green.png"),0xFF000000));
        pinkGemSheet = new Sprite(eraserBackgroundImage(loadImage("/Gem/pink.png"),0xFF000000));
        redGemSheet = new Sprite(eraserBackgroundImage(loadImage("/Gem/red.png"),0xFF000000));
        blueGemSheet = new Sprite(eraserBackgroundImage(loadImage("/Gem/blue.png"),0xFF000000));
        yellowGemSheet = new Sprite(eraserBackgroundImage(loadImage("/Gem/yellow.png"),0xFF000000));

    }

    public static BufferedImage loadImage(String path) {

        try {
            BufferedImage loadedImage = ImageIO.read(new File(resourcePath + path));
            BufferedImage formatImage = new BufferedImage(loadedImage.getWidth(),
                    loadedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

            formatImage.getGraphics().drawImage(loadedImage, 0, 0, null);

            return formatImage;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage eraserBackgroundImage(BufferedImage image, int rgb) {
        int[] data = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        for (int i = 0; i < data.length; i++) {
            if (data[i] == rgb) {
                data[i] = 0;
            }
        }
        return image;
    }

    public static BufferedImage flipVertical(BufferedImage image) {
        BufferedImage buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = buffer.createGraphics();

        AffineTransform af = AffineTransform.getScaleInstance(-1, 1);
        af.translate(-image.getWidth(), 0);

        g2d.transform(af);
        g2d.drawImage(image, 0, 0, null);

        return buffer;
    }

    public static BufferedImage flipHorizontal(BufferedImage image) {
        BufferedImage buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = buffer.createGraphics();

        AffineTransform af = AffineTransform.getScaleInstance(1, -1);
        af.translate(0, -image.getHeight());

        g2d.transform(af);
        g2d.drawImage(image, 0, 0, null);

        return buffer;
    }

    public static BufferedImage rotation90(BufferedImage image) {
        BufferedImage buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = buffer.createGraphics();

        AffineTransform af = AffineTransform.getRotateInstance(Math.PI / 2);
        af.translate(0, -image.getWidth());
//        af.setToScale(1,1);

        g2d.transform(af);
        g2d.drawImage(image, 0, 0, image.getHeight(), image.getWidth(), null);

        return buffer;
    }
}
