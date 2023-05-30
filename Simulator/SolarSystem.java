package Simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OptionalDataException;
import GUI.GlobalState;
import Interface.IODESolver;

public class SolarSystem {

    public OptionalDataException celestialBody;
    private State[] states;

    private static double daySec = 60 * 24 * 60;

    public long timeFinal = 60 * 24 * 60 * 365 * 2; // 2 years
    private static double h = GlobalState.STEP_MULTIPLIER * daySec;

    public SolarSystem() {

        // inita; nodies
        new CelestialBodyValues();

        states = new State[((int) Math.round((timeFinal / h)) + 1)];
        int length = states.length;

        for (int i = 0; i < length; i++)
            states[i] = new State();
    }

    public void initialProcess() {
        // Initialize celestial bodies
        String filename = "Simulator/Values.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            ReadFile.updateCelestialBodyPositions(ReadFile.readSection(br, "POSITIONS"));
            ReadFile.updateCelestialBodyVelocities(ReadFile.readSection(br, "VELOCITY"));
            ReadFile.updateCelestialBodyMasses(ReadFile.readSection(br, "MASS"));

            states[0].inputState();
            IODESolver solver = new ODESolver();
            states = (State[]) solver.solve(new ODEFunction(), (State) states[0], h, timeFinal);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (ReadFile.SectionNotFoundException e) {
            System.out.println("Section not found: " + e.getMessage());
        }
    }

    // For genetic algorithm
    public void initialProcess(Vector3 velocity) {
        states[0].inputState();
        states[0].state[11][1] = velocity;
        IODESolver solver = new ODESolver();
        states = (State[]) solver.solve(new ODEFunction(), (State) states[0], h, timeFinal);
    }

    public void setStates(State[] states) {
        this.states = states;
    }

    public State[] getStates() {
        return states;
    }

    public CelestialBody[] getCelestialBodies() {
        return CelestialBody.celestialBodies;
    }

    public static void main(String[] args) {
        SolarSystem ss = new SolarSystem();
        ss.initialProcess();
        System.out.println(ss.states[1].state[1][0]);
    }
}