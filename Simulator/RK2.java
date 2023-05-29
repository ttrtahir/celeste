package Simulator;

import Interface.IODEFunction;
import Interface.IState;

/**
 * Class modifies the step method of the ODESolver class to calculate the state
 * after applying RK4
 */
public class RK2 extends ODESolver {

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
    public IState step(IODEFunction f, double t, IState y, double h, boolean thrustNeeded) {
        AccelerationRate k1 = (AccelerationRate) f.call(t, y);
        AccelerationRate k2 = (AccelerationRate) f.call(t + ((2 / 3) * h), y.addmultiply((2 / 3), k1));

        return y.addmultiply(h / 4, (k1).addMultiply(3, k2));
    }
    // hahah
}
