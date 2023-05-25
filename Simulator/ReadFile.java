package Simulator;
/*
 * Contains the method for reading the values from a file
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        String filename = "qq.txt";
        String sectionVelocity = "VELOCITY";
        String sectionMass = "MASS";
        String sectionPosition = "POSITIONS";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            double[][] position = ReadFile.readSection(br, sectionPosition);
            ReadFile.printArray(position);
            System.out.println();
            double[][] velocity = ReadFile.readSection(br, sectionVelocity);
            ReadFile.printArray(velocity);
            System.out.println();
            double[][] mass = ReadFile.readSection(br, sectionMass);
            ReadFile.printArray(mass);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (SectionNotFoundException e) {
            System.out.println("Section not found: ");
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

    static void printArray(double[][] array) {
        for (double[] row : array) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}

class SectionNotFoundException extends Exception {
    public SectionNotFoundException(String sectionName) {
        super(sectionName);
    }
}