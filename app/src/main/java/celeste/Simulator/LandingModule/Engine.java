package celeste.Simulator.LandingModule;

public class Engine {
    private double uMax;
    private double vMax;

    public Engine(double uMax, double vMax) {
        this.uMax = uMax;
        this.vMax = vMax;
    }

    public double getUMax() {
        return uMax;
    }

    public void setUMax(double uMax) {
        this.uMax = uMax;
    }

    public double getVMax() {
        return vMax;
    }

    public void setVMax(double vMax) {
        this.vMax = vMax;
    }
}
