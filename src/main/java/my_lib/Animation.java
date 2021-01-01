package my_lib;

import manager.EnvironmentVariable;

public class Animation {
    protected Sheet sheet;
    protected final int column, line;
    protected int timeCountChange = 0;
    public int speed; // speed change next sprite
    public int current; // index current
    public int begin; // index start run
    public int end = 13;


    public Animation(Sheet sheet, int speed) {
        this.speed = speed;
        column = sheet.getColumn();
        line = sheet.getLine();
        this.sheet = sheet;
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

    public void render(ImageRender imageRenderHandle, int positionX,int positionY,double zoomX, double zoomY) {
        imageRenderHandle.renderSprite(sheet.getSprite(current), positionX, positionY, zoomX, zoomY);
    }
}
