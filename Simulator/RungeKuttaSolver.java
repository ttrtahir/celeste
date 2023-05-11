package Simulator;

import Interface.IODEFunction;
import Interface.IODESolver;
import Interface.IState;

public class RungeKuttaSolver implements IODESolver {

    /**
     * For now the two solve methods are the same as the
     * ODESolver class. I'm not sure if it's correct put we can
     * check when Euler is finished and GUI is working
     */

    public State[] states;

    @Override
    public IState[] solve(IODEFunction f, IState y0, double[] timestep) {
        // TODO Auto-generated method stub
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
    public IState[] solve(IODEFunction f, IState y0, double timefinal, double h) {
        // TODO Auto-generated method stub
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
    public IState step(IODEFunction f, double t, IState y, double h) {
        // TODO Auto-generated method stub
        double k1 = (double) y.addmultiply(h, f.motion(t, y));
        State k2 = (State) y.addmultiply(h, f.motion(t + 0.5 * h, y + (0.5 * k1)));

        State newState = (State) y.addmultiply(h, f.motion(h, y));

    }

}
