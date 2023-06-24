package FileReader;

/*
 * Contains the method for reading the values from a file
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import GUI.Resources.StyleValues;
import Simulator.CelestialBodies.CelestialBody;

public class ReadFile {
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

    public static void updateCelestialBodyValues() {
        try (BufferedReader br = new BufferedReader(
                new FileReader("FileReader/Values.txt"))) {
            double[][] positions = ReadFile.readSection(br, "POSITIONS");
            double[][] velocities = ReadFile.readSection(br, "VELOCITY");
            double[][] masses = ReadFile.readSection(br, "MASS");

            if (positions.length != velocities.length || positions.length != masses.length) {
                System.out.println("Number of positions, velocities and masses does not match.");
                return;
            }

            CelestialBody.celestialBodies = new CelestialBody[positions.length - 1];

            for (int i = 0; i < positions.length; i++) {
                CelestialBody celestialBody = new CelestialBody(StyleValues.NAMES[i]);
                celestialBody.posX = positions[i][0];
                celestialBody.posY = positions[i][1];
                celestialBody.posZ = positions[i][2];

                celestialBody.veloX = velocities[i][0];
                celestialBody.veloY = velocities[i][1];
                celestialBody.veloZ = velocities[i][2];

                celestialBody.mass = masses[i][0];

                celestialBody.createVectors();

                if (i == positions.length - 1) {
                    CelestialBody.spaceProbe = celestialBody;
                    continue;
                }
                CelestialBody.celestialBodies[i] = celestialBody;
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (ReadFile.SectionNotFoundException e) {
            System.out.println("Section not found: " + e.getMessage());
        }
    }

    static class SectionNotFoundException extends Exception {
        public SectionNotFoundException(String sectionName) {
            super(sectionName);
        }
    }
}