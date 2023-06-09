package celeste.Simulator.Solvers;

import celeste.Interface.IODEFunction;
import celeste.Interface.IState;
import celeste.Simulator.CelestialBodies.AccelerationRate;

/**
 * Class modifies the step method of the ODESolver class to calculate the state
 * after applying RK4
 */
public class RK3 extends ODESolver {

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
        AccelerationRate k2 = (AccelerationRate) f.call(t + (0.5 * h), y.addmultiply(0.5, k1));
        AccelerationRate k3 = (AccelerationRate) f.call(t + (0.5 * h), y.addmultiply(-1, k1).addmultiply(2, k2));

        return y.addmultiply(h / 6, (k1).addMultiply(4, k2).addMultiply(1, k3));
    }

}
