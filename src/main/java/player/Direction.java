package player;

public class Direction {
    /*
    up = 0
    left = 1
    down = 2
    right = 3
     */
    public int direction;
    public boolean moveUp, moveDown, moveLeft, moveRight;

    public Direction(int direction) {
        this.direction = direction;
        moveUp = false;
        moveDown = false;
        moveLeft = false;
        moveRight = false;
    }

    public void setAllFalse() {
        moveUp = false;
        moveDown = false;
        moveLeft = false;
        moveRight = false;
    }

    public int getDirectionNumber() {
        return direction;
    }

    public boolean isMoving(){
        return moveUp||moveDown||moveRight||moveLeft;
    }

    public void setDirectionByNumber(int number){
        direction = number;
        if (number == 0) moveUp = true;
        if (number == 1) moveLeft = true;
        if (number == 2) moveDown = true;
        if (number == 3) moveRight = true;

    }
}
