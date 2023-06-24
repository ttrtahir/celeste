package Simulator.Solvers;

import Interface.IODEFunction;
import Interface.IODESolver;
import Interface.IState;
import Interface.IVector3;
import Simulator.State;
import Simulator.CelestialBodies.CelestialBody;
import Simulator.CelestialBodies.Engine;
import Simulator.LandingModule.SimulateLanding;

public class ODESolver implements IODESolver {
    /* class implements EULER's method */
    public State[] states;

    private Engine engine = new Engine();

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
            states[i] = (State) step(f, timestep[i], states[i - 1], (timestep[i] - timestep[i - 1]));
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
            states[i] = (State) step(f, timeStep[i], states[i - 1], (timeStep[i] - timeStep[i - 1]));
        }

        return states;
    }

    @Override
    public IState[] solveProbe(IODEFunction f, double h, double timefinal, State[] states,
            IVector3 probeVel) {
        /**
         * Euler's method specifically for Space Probe
         *
         * @param f         a Function calculate force
         * @param timefinal final time
         * @param h         step size
         * @param states    previously calculated states containing all planets'
         *                  positions
         * @param probePos  initial position of the probe
         * @param probeVel  initial velocity of the probe
         * @return states of Solar System in the given time interval
         */

        states[0].state[11][0] = CelestialBody.spaceProbe.posVec;
        states[0].state[11][1] = probeVel;

        double[] timeStep = new double[(int) (Math.round((timefinal / h) + 1))];
        timeStep[0] = 0;
        for (int i = 1; i < timeStep.length; i++) {
            timeStep[i] = timeStep[i - 1] + h;
        }

        // update positions of the probe for 1 step
        for (int i = 1; i < states.length; i++) {
            boolean thrustNeeded = false;
            if (i == 3493) {// || i == 4494
                thrustNeeded = true;
                SimulateLanding.initiateLanding(states[i - 1].state[11][0].getX(),
                        Math.abs(states[i - 1].state[11][0].getY()), states[i - 1].state[11][1].getX(),
                        states[i - 1].state[11][1].getY());
            }
            states[i].state[11] = stepProbe(f, timeStep[i], states[i - 1], (timeStep[i] - timeStep[i - 1]),
                    thrustNeeded);
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
    public IState step(IODEFunction f, double t, IState y, double h) {
        State newState = (State) y.addmultiply(h, f.call(h, y));

        return newState;
    }

    /* Same as step, only for probe + has engine thrusts */
    @Override
    public IVector3[] stepProbe(IODEFunction f, double t, IState y, double h, boolean thrustNeeded) {
        IVector3[] newState = y.addmultiplyProbe(h, f.callProbe(h, y));

        /* false to turn off engine for now */
        if (false && thrustNeeded) {
            newState[1] = this.engine.initiateThrust(newState[1]);
        }

        return newState;
    }
}
