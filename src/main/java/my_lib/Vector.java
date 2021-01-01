package my_lib;

public class Vector {
    public double positionX, positionY, length;

    public Vector(double x, double y) {
        positionX = x;
        positionY = y;

        setLength(x, y);
    }

    public Vector(double xa, double ya, double xb, double yb) {
        positionX = xb - xa;
        positionY = yb - ya;
        setLength(positionX, positionY);
    }

    public void setLength(double x, double y) {
        length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public void setLength(double length) {
        positionX = positionX * length / this.length;
        positionY = positionY * length / this.length;
        this.length = length;
    }

    public void add(Vector a) {
        positionX = Math.round(positionX + a.positionX);
        positionY = Math.round(positionY + a.positionY);

    }

    public void mul(double a) {
        positionX = Math.round(positionX * a);
        positionY = Math.round(positionY * a);
    }

    public void rotate(double phi) {
        double ph = Math.atan2(positionY, positionX);
        ph -= phi;
        positionX = length * Math.cos(ph);
        positionY = length * Math.sin(ph);

    }

    public void setPosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }

    public void getReflex(Vector pp) {
        Vector p = new Vector(pp.positionX, pp.positionY);
        double cos = (positionX * p.positionX + positionY * p.positionY) / (length * p.length);
        if (cos == 1) {
            this.mul(-1);
            return;
        }
        double k = (length * cos * 2) / p.length;

        p.positionX = p.positionX * k;
        p.positionY = p.positionY * k;
        if (positionX * p.positionX + positionY * p.positionY > 0)
            p.mul(-1);

        add(p);
    }

}
