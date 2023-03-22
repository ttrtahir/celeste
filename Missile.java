import java.awt.*;

public class Missile extends CelestialBody{
    public Missile(double mass, int size, CelestialBody earth, CelestialBody titan) {
        super();
        double[] initialVelocity = earth.getV();
        initialVelocity[0] += 36;
        initialVelocity[1] += 48;
        double[] initialCoordinates = earth.getX();
        initialCoordinates[1] += 6370;

        this.x = initialCoordinates;
        this.v = initialVelocity;
        this.mass = mass;

        this.size = size;
        this.name = "Rocket";
        this.color = Color.RED;
    }
}
