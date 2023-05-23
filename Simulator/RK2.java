package Simulator;

import Interface.IODEFunction;
import Interface.IState;

public class RK2 extends ODESolver {

    @Override
    public IState step(IODEFunction f, double t, IState y, double h) {
        AccelerationRate k1 = (AccelerationRate) f.call(t, y);
        AccelerationRate k2 = (AccelerationRate) f.call(t + ((2 / 3) * h), y.addmultiply((2 / 3), k1));

        return y.addmultiply(h / 4, (k1).addMultiply(3, k2));
    }

}
