package Simulator;

/*
 * Contains the method for reading the values from a file
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        String filename = "Simulator/Values.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            double[][] position = readSection(br, "POSITIONS");
            updateCelestialBodyPositions(position);

            double[][] velocity = readSection(br, "VELOCITY");
            updateCelestialBodyVelocities(velocity);

            double[][] mass = readSection(br, "MASS");
            updateCelestialBodyMasses(mass);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (SectionNotFoundException e) {
            System.out.println("Section not found: " + e.getMessage());
        }
    }

    static double[][] readSection(BufferedReader br, String sectionName) throws IOException, SectionNotFoundException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().equals(sectionName)) {
                return readData(br);
            }
        }
        throw new SectionNotFoundException(sectionName);
    }

    private static double[][] readData(BufferedReader br) throws IOException {
        String line;
        StringBuilder sectionData = new StringBuilder();

        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            sectionData.append(line.trim()).append("\n");
        }

        String[] rows = sectionData.toString().split("\n");
        double[][] data = new double[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String row = rows[i].trim();
            String[] values = row.substring(1, row.length() - 1).split(", ");
            data[i] = new double[values.length];

            for (int j = 0; j < values.length; j++) {
                String value = values[j].trim();
                if (value.endsWith(",") || value.endsWith("}")) {
                    value = value.substring(0, value.length() - 1);
                }
                data[i][j] = Double.parseDouble(value);
            }
        }

        return data;
    }

    static void updateCelestialBodyPositions(double[][] positions) {
        if (positions.length != CelestialBody.celestialBodies.length) {
            System.out.println("Number of positions does not match the number of celestial bodies.");
            return;
        }

        for (int i = 0; i < positions.length; i++) {
            CelestialBody celestialBody = CelestialBody.celestialBodies[i];
            celestialBody.posX = positions[i][0];
            celestialBody.posY = positions[i][1];
            celestialBody.posZ = positions[i][2];
            celestialBody.createVectors();
        }
    }

    static void updateCelestialBodyVelocities(double[][] velocities) {
        if (velocities.length != CelestialBody.celestialBodies.length) {
            System.out.println("Number of velocities does not match the number of celestial bodies.");
            return;
        }

        for (int i = 0; i < velocities.length; i++) {
            CelestialBody celestialBody = CelestialBody.celestialBodies[i];
            celestialBody.veloX = velocities[i][0];
            celestialBody.veloY = velocities[i][1];
            celestialBody.veloZ = velocities[i][2];
            celestialBody.createVectors();
        }
    }

    static void updateCelestialBodyMasses(double[][] masses) {
        if (masses.length != CelestialBody.celestialBodies.length) {
            System.out.println("Number of masses does not match the number of celestial bodies.");
            return;
        }

        for (int i = 0; i < masses.length; i++) {
            CelestialBody celestialBody = CelestialBody.celestialBodies[i];
            celestialBody.mass = masses[i][0];
        }
    }

    static class SectionNotFoundException extends Exception {
        public SectionNotFoundException(String sectionName) {
            super(sectionName);
        }
    }
}