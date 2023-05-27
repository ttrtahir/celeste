package Simulator;

import Interface.IODEFunction;
import Interface.IODESolver;
import Interface.IState;

public class ODESolver implements IODESolver {
    /* class implements EULER's method */
    public State[] states;

    @Override
    public IState[] solve(IODEFunction f, IState y0, double[] timestep) {
        /**
         * Euler's method
         * 
         * @param f  a Function calculating force
         * @param y0 initial state
         * @param ts updated time steps
         * @return states of Solar System at different time
         */

        states = new State[timestep.length];
        states[0] = (State) y0;

        // update positions of each planet for 1 step
        for (int i = 1; i < states.length; i++) {
            states[i] = (State) step(f, timestep[i], states[i - 1], (timestep[i] - timestep[i - 1]), false);
        }
        return states;
    }

    @Override
    public IState[] solve(IODEFunction f, IState y0, double h, double timefinal) {
        /**
         * Euler's method
         *
         * @param f         a Function calculate force
         * @param y0        initial state
         * @param timefinal final time
         * @param h         step size
         * @return states of Solar System in the given time interval
         */

        double[] timeStep = new double[(int) (Math.round((timefinal / h) + 1))];
        timeStep[0] = 0;
        for (int i = 1; i < timeStep.length; i++) {
            timeStep[i] = timeStep[i - 1] + h;
        }

        states = new State[(int) (Math.round(timefinal / h) + 1)];
        states[0] = (State) y0;

        // update positions of each planet for 1 step
        for (int i = 1; i < states.length; i++) {
            boolean isAboutToOrbit = false;
            if (i == 3493) {
                isAboutToOrbit = true;
            }
            states[i] = (State) step(f, timeStep[i], states[i - 1], (timeStep[i] - timeStep[i - 1]), isAboutToOrbit);
        }

        return states;
    }

    /**
     * to calculate each step
     * 
     * @param f a Function calculating force
     * @param t time
     * @param y state
     * @param h step size
     * @return new state after update 1 step
     */

    @Override
    public IState step(IODEFunction f, double t, IState y, double h, boolean isAboutToOrbit) {
        State newState = (State) y.addmultiply(h, f.call(h, y));
        if (isAboutToOrbit) {
            Vector3 titanVelocity = (Vector3) newState.state[8][1];
            Vector3 spaceProbeVelocity = (Vector3) newState.state[11][1];

            Vector3 newVelocity = new Vector3(-0.3, 11, -0.86);

            newState.state[11][1] = newVelocity;
        }

        return newState;
    }
}
