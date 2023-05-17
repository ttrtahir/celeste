package Simulator;

import Interface.IODEFunction;
import Interface.IState;

/*
* class implementing Euler's method to calculate motion of objects in the solar system
* */

public class EulerSolver extends ODESolver {

    /**
     * calculating one step
     *
     * @param f simulator.ODEFunction implementing Newton's law
     * @param t time
     * @param y state
     * @param h step size
     * @return new state of the system after one update step
     */
    @Override
    public IState step(IODEFunction f, double t, IState y, double h) {
        State newState = (State) y.addmultiply(h, f.call(t,y));
        return newState;
    }


}
