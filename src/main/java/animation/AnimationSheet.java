package animation;

import manager.ImageRenderHandle;
import manager.EnvironmentVariable;

public class AnimationSheet {
    private static final int PIXEL = EnvironmentVariable.PIXEL;
    protected Sprite[] sprite;
    protected final int column, line;
    protected int timeCountChange = 0;
    public int speed; // speed change next sprite
    public int current; // index current
    public int begin; // index start run
    public int end = 13;


    public AnimationSheet(Sheet sheet, int speed) {
        this.speed = speed;
        column = sheet.getColumn();
        line = sheet.getLine();
        sprite = new Sprite[column * line];

        for (int i = 0; i < line; i++)
            for (int j = 0; j < column; j++)
                sprite[i * column + j] = new Sprite(sheet, j * PIXEL, i * PIXEL, PIXEL,PIXEL);
    }

    public AnimationSheet(int column, int line) {
        this.column = column;
        this.line = line;
    }

    public void setBegin(int i) {
        if (i == begin)
            return;
        begin = i;
        current = begin * column;
    }

    public void update() {
        timeCountChange++;
        if (timeCountChange >= 4) {
            timeCountChange = 0;
            current++;
            if (current >= (begin ) * column + end)
                current = begin * column;
        }
    }

    public void render(ImageRenderHandle imageRenderHandle, int positionX,int positionY,int zoomX, int zoomY) {
        imageRenderHandle.renderSprite(sprite[current], positionX, positionY, zoomX, zoomY);
    }
}
