package Simulator;

import Interface.IODEFunction;
import Interface.IState;

public class RK3 extends ODESolver {

    @Override
    public IState step(IODEFunction f, double t, IState y, double h) {
        AccelerationRate k1 = (AccelerationRate) f.call(t, y);
        AccelerationRate k2 = (AccelerationRate) f.call(t + (0.5 * h), y.addmultiply(0.5, k1));
        AccelerationRate k3 = (AccelerationRate) f.call(t + (0.5 * h), y.addmultiply(-1, k1).addmultiply(2, k2));

        return y.addmultiply(h / 6, (k1).addMultiply(4, k2).addMultiply(1, k3));
    }

}
