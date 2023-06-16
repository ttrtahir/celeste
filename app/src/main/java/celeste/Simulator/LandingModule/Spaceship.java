package celeste.Simulator.LandingModule;

public class Spaceship {
    private double x, y, theta, u, v, vX, vY, vTheta;

    public Spaceship(double x, double y, double theta, double u, double v, double vX, double vY, double vTheta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
        this.u = u;
        this.v = v;
        this.vX = vX;
        this.vY = vY;
        this.vTheta = vTheta;
    }

    public void updateState(double dt) {
        this.vX = u * Math.sin(theta);
        this.vY = u * Math.cos(theta) - Environment.GRAVITY;
        this.vTheta = v;
        this.x += vX * dt;
        this.y += vY * dt;
        this.theta += vTheta * dt;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTheta() {
        return theta;
    }

    public double getU() {
        return u;
    }

    public double getV() {
        return v;
    }

    public double getVX() {
        return vX;
    }

    public double getVY() {
        return vY;
    }

    public double getVTheta() {
        return vTheta;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public void setU(double u) {
        this.u = u;
    }

    public void setV(double v) {
        this.v = v;
    }

    public void setVX(double vX) {
        this.vX = vX;
    }

    public void setVY(double vY) {
        this.vY = vY;
    }

    public void setVTheta(double vTheta) {
        this.vTheta = vTheta;
    }
}
