package celeste.Interface;

public interface IODESolver {
    /*
     * class to solve differential equation dy/dt = f(t,y)
     * y(t): state of the system at time t
     * f(t,y(t)): derivative from y(t) at time t
     */
    public IState[] solve(IODEFunction f, IState y0, double[] timeStep);

    public IState[] solve(IODEFunction f, IState y0, double timeFinal, double h);

    public IState step(IODEFunction f, double t, IState y, double h, boolean thrustNeeded);
}
