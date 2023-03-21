
public class SolarSystem {
    public CelestialBody[] celestialBody;
    private int celestialBodyCount;
    private final double GRAVITATIONAL_CONSTANT = 6.6743E-20; // km^3/(kg s^2)
    private double timeStep;

    public SolarSystem() {

        // Amount of celestial entities in a solar system (including space probes)
        this.celestialBodyCount = Values.positions.length;
        this.celestialBody = new CelestialBody[celestialBodyCount];
        this.timeStep = 10000;

        // Without probe
        for (int i = 0; i < celestialBodyCount; i++)
            this.celestialBody[i] = new CelestialBody(Values.positions[i], Values.velocity[i], Values.mass[i][0],
                    Values.SIZES[i], Values.NAMES[i], Values.COLORS[i]);

        // calculateForce();
        // updateAcceleration();
        // updateVelocity();
        // updatePosition();
    }

    /**
     * Calculates the force on each celestial unit and updates the force on them in
     * each call using their position, mass, and gravitational constant. Newtons
     * formula. <br>
     * Fx = (G * m1 * m2 * dx) / r^3
     */
    public void calculateForce() {

        for (int i = 1; i < celestialBodyCount; i++) {
            double totalForceOnIth = 0;
            double distance = 0;
            double[] tempF = new double[3];
            for (int j = 0; j < celestialBodyCount; j++) {
                if (j == i)
                    continue;
                distance = calculateDistanceBetweenCelestials(i, j);
                totalForceOnIth += -(GRAVITATIONAL_CONSTANT * celestialBody[i].getMass() * (celestialBody[j].getMass())
                        / (distance * distance));
                for (int k = 0; k < 3; k++) {
                    tempF[k] = totalForceOnIth * (celestialBody[j].getX()[k] - celestialBody[i].getX()[k]) / distance;
                }
            }
            celestialBody[i].setTotalForce(totalForceOnIth);
            celestialBody[i].setForces(tempF);
            // System.out.println("Total Force on "+ i + " is : " + totalForceOnIth + " with
            // distance : " +distance + " acceleration: " + totalForceOnIth /
            // celestialBody[i].getMass());
        }
    }

    /**
     * Helper method for calculateTheForceForEach() method. Calculates the distance
     * between celestials with this formula:<br>
     * ||u - v|| = sqrt((u1 - v1)^2 + (u2 - v2)^2 + (u3 - v3)^2)
     */
    public double calculateDistanceBetweenCelestials(int i, int j) {
        double x1[] = getCelestialBody()[i].getX();
        double x2[] = getCelestialBody()[j].getX();
        double totalNeedsSquared = 0;

        for (int k = 0; k <= 2; k++)
            totalNeedsSquared = Math.pow((x1[k] - x2[k]), 2);

        return Math.sqrt(totalNeedsSquared);
    }

    /**
     * A simple method for updating the acceleration using mass and force on the
     * celestials.
     */
    public void updateAcceleration() {
        for (int i = 1; i < celestialBodyCount; i++) {
            double[] tempF = celestialBody[i].getForces();
            double[] tempA = new double[3];
            double mass = celestialBody[i].getMass();
            for (int j = 0; j < 3; j++) {
                double forceOnJ = tempF[j];
                tempA[j] = forceOnJ / mass;
            }
            celestialBody[i].setAcceleration(tempA);
        }
    }

    public void updatePosition() {
        for (int i = 1; i < celestialBodyCount; i++) {
            double[] tempV = celestialBody[i].getV();
            double[] tempP = celestialBody[i].getX();
            for (int j = 0; j < 3; j++)
                tempP[j] += tempV[j] * timeStep;
            celestialBody[i].setX(tempP);
        }
    }

    public void updateVelocity() {
        for (int i = 1; i < celestialBodyCount; i++) {
            double[] tempA = celestialBody[i].getAcceleration();
            double[] tempV = celestialBody[i].getV();
            for (int j = 0; j < 3; j++)
                tempV[j] += tempA[j] * timeStep;
            celestialBody[i].setV(tempV);
        }
    }

    public CelestialBody[] getCelestialBody() {
        return celestialBody;
    }
}
