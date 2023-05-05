package Physics;

import Interface.ICalculation;
import Interface.IFunction;
import Interface.IState;

public class Calculation implements ICalculation {
    /* class implements EULER's method */
    public State[] states;

    @Override
    public IState[] solve(IFunction f, IState y0, double[] timestep) {
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
    public IState[] solve(IFunction f, IState y0, double h, double timefinal) {
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
    public IState step(IFunction f, double t, IState y, double h) {
        State newState = (State) y.addmultiply(h, f.motion(h, y));

        return newState;
    }
}
