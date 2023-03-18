public class CelestialBody {
    private double x1;
    private double x2;
    private double x3;
    private double v1;
    private double v2;
    private double v3;
    private double mass;
    private String name;

    private final double GRAVITATIONAL_CONSTANT = 6.6743E-20; // km^3/(kg s^2)

    public CelestialBody(String name, double x1, double x2, double x3, double v1, double v2, double v3, double mass){
        this.name = name;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.mass = mass;
    }

    // F = G * (m1 * m2) / r^2
    // Math.atan2() method to separate x and y forces
//    private double calculateForce(CelestialBody anotherCB){
//        double d1 = this.x1 - anotherCB.getX1();
//        double d2 = this.x2 - anotherCB.getX2();
//        double d3 = this.x3 - anotherCB.getX3();
//        double distance = Math.sqrt(d1*d1 + d2*d2 + d3*d3);
//        double force = GRAVITATIONAL_CONSTANT * (this.getMass() * anotherCB.getMass()) / (distance * distance);
//        return force;
//    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getV1() {
        return v1;
    }

    public void setV1(double v1) {
        this.v1 = v1;
    }

    public double getV2() {
        return v2;
    }

    public void setV2(double v2) {
        this.v2 = v2;
    }

    public double getV3() {
        return v3;
    }

    public void setV3(double v3) {
        this.v3 = v3;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
