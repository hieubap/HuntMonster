package my_lib;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import static manager.EnvironmentVariable.PATHRESOUREASSET;

public class SpriteManager {
    public static Sprite mapSprite;

    static {
        mapSprite = new Sprite(SpriteManager.loadImage(VariableEnvironment.PATH_MAP_IMAGE));
    }

    public static BufferedImage loadImage(String path) {
        try {
            BufferedImage loadedImage = ImageIO.read(new File(PATHRESOUREASSET + path));
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

        g2d.transform(af);
        g2d.drawImage(image, 0, 0, image.getHeight(), image.getWidth(), null);

        return buffer;
    }
}
