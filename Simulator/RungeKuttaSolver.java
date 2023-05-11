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

        /**
         * TODO redo the casting part and variables
         * It might work or not
         * we not really sure because we don't know why it's working
         */
        AccelerationRate k1 = (AccelerationRate) f.call(t, y);
        AccelerationRate k2 = (AccelerationRate) f.call(t + (0.5 * h), y.addmultiply(0.5, k1));
        AccelerationRate k3 = (AccelerationRate) f.call(t + 0.5 * h, y.addmultiply(0.5, k2));
        AccelerationRate k4 = (AccelerationRate) f.call(t + 0.5 * h, y.addmultiply(1, k3));

        IState newState = y.addmultiply(h / 6,
                (AccelerationRate) ((IState) k1).addmultiply(2, k2).addmultiply(2, k3).addmultiply(1, k4));

        return newState;

    }

}
