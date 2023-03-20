public class SolarSystem {

    public static double[][] positions = { { 0, 0, 0 },
            { 7833268.43923962, 44885949.3703908, 2867693.20054382 },
            { -28216773.9426889, 103994008.541512, 3012326.64296788 },
            { -148186906.893642, -27823158.5715694, 33746.8987977113 },
            { -148458048.395164, -27524868.1841142, 70233.6499287411 },
            { -159116303.422552, 189235671.561057, 7870476.08522969 },
            { 692722875.928222, 258560760.813524, -16570817.7105996 },
            { 1253801723.95465, -760453007.810989, -36697431.1565206 },
            { 1254501624.95946, -761340299.067828, -36309613.8378104 },
            { 4454487339.09447, -397895128.763904, -94464151.3421107 },
            { 1958732435.99338, 2191808553.21893, -17235283.8321992 } };
    public static double[][] velocity = { { 0, 0, 0 },
            { -57.4967480139828, 11.52095127176, 6.21695374334136 },
            { -34.0236737066136, -8.96521274688838, 1.84061735279188 },
            { 5.05251577575409, -29.3926687625899, 0.00170974277401292 },
            { 4.34032634654904, -30.0480834180741, -0.0116103535014229 },
            { -17.6954469224752, -13.4635253412947, 0.152331928200531 },
            { -4.71443059866156, 12.8555096964427, 0.0522118126939208 },
            { 4.46781341335014, 8.23989540475628, -0.320745376969732 },
            { 8.99593229549645, 11.1085713608453, -2.25130986174761 },
            { 0.447991656952326, 5.44610697514907, -0.122638125365954 },
            { -5.12766216337626, 4.22055347264457, 0.0821190336403063 } };
    public static double[][] mass = { { 1.9885e30 }, { 3.302e23 }, { 48.685e23 }, { 5.97219e24 }, { 7.349e22 },
            { 6.4171e23 },
            { 189818722e19 }, { 5.6834e26 }, { 13455e19 }, { 102.409e24 }, { 86.813e24 } };

    private CelestialBody[] celestialBody;
    private int celestialBodyCount;
    private final double GRAVITATIONAL_CONSTANT = 6.6743E-11; // km^3/(kg s^2)
    private double timeStep;

    public SolarSystem() {

        // Amount of celestial entities in a solar system (including space probes)
        this.celestialBodyCount = 11;
        this.celestialBody = new CelestialBody[celestialBodyCount];
        this.timeStep = 0.1;

        // Without probe
        for (int i = 0; i < celestialBodyCount; i++)
            this.celestialBody[i] = new CelestialBody(positions[i], velocity[i], mass[i][0]);

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
    private void calculateForce() {

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
    private double calculateDistanceBetweenCelestials(int i, int j) {
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
    private void updateAcceleration() {
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

    private void updatePosition() {
        for (int i = 1; i < celestialBodyCount; i++) {
            double[] tempV = celestialBody[i].getV();
            double[] tempP = celestialBody[i].getX();
            for (int j = 0; j < 3; j++)
                tempP[j] += tempV[j] * timeStep;
            celestialBody[i].setX(tempP);
        }
    }

    private void updateVelocity() {
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
