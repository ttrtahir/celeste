package Interface;

public interface ICalculation {
    /*
     * class to solve differential equation dy/dt = f(t,y)
     * y(t): state of the system at time t
     * f(t,y(t)): derivative from y(t) at time t
     */
    public IState[] solve(IFunction f, IState y0, double[] timestep);

    public IState[] solve(IFunction f, IState y0, double h, double timefinal);

    public IState step(IFunction f, double t, IState st, double h);
}
