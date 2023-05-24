package Simulator;

import java.io.OptionalDataException;

import Interface.IODESolver;

public class SolarSystem {

    public OptionalDataException celestialBody;
    private State[] states;

    private static double daySec = 60 * 24 * 60;

    public long timeFinal = 60 * 24 * 60 * 365 * 2;
    public static double h = 0.1 * daySec;

    public SolarSystem() {
        // inita; nodies
        CelestialBodyValues cbv = new CelestialBodyValues();

        states = new State[((int) Math.round((timeFinal / h)) + 1)];
        int length = states.length;

        for (int i = 0; i < length; i++)
            states[i] = new State();
    }

    public void initialProcess() {
        /*
         * rewrite this method to initialize the solar system
         */

        states[0].inputState();
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
