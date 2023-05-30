package Testing;

import Interface.IVector3;
import Simulator.Vector3;
import Simulator.CelestialBodies.CelestialBodyValues;
import Simulator.CelestialBodies.SpaceProbe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Draft {
    static CelestialBodyValues celestialBodyValues = new CelestialBodyValues();
    static IVector3 p0 = new Vector3(0, 0, 0);
    static IVector3 v0 = new Vector3(0, 0, 0);
    static Vector3 titanPos = new Vector3(1.254501624e+09, -7.61340299e+08, -3.6309613e+07);
    static Vector3 earthPos = new Vector3(-1.48186906e+08, -2.7823158e+07, 3.3746898e+04);
    static double timeFinal = 31536000;
    static double[] stepSizes = { 0.5, 1, 10, 20 };
    static double h;
    static SpaceProbe spaceProbe;

    static FileWriter writer;
    static BufferedWriter bufferedWriter;

    public static void main(String[] args) {
        // System.out.println("Testing");

        File outputFile = new File("C:\\celeste\\Testing\\testing");
        try {
            writer = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("solver, step size, dist_earthPos, dist_titanPos");
            bufferedWriter.newLine();

            for (int i = 0; i < stepSizes.length; i++) {
                spaceProbe = new SpaceProbe();
                h = stepSizes[i];
                spaceProbe.trajectory(p0, v0, timeFinal, h);

                // double distEarthPos = earthPos.euclideanDist(spaceProbe.earthPosOneYear);
                // double distTitanPos = titanPos.euclideanDist(spaceProbe.titanPosOneYear);

                // bufferedWriter.write("Euler Method ,"+h+","+distEarthPos+","+distTitanPos);
                bufferedWriter.newLine();

                System.out.println();
                System.out.println("Test done: Euler Method, " + h);
                spaceProbe = null;
                System.gc();
            }
            bufferedWriter.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Done");
        System.out.println();
    }
}
