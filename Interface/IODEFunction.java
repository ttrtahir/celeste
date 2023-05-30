package Interface;

public interface IODEFunction {
    // function f represents the differential equation dy/dt = f(t,y)
    // implement this function to represent calculation of acceleration

    // state y(t) comprises both position vector and velocity vector v(t)
    /*
     * so
     * y(t) = (x(t))
     * v(t)
     */
    // x¨ = d^2x/dt^2 is its second derivative = acceleration a(t)

    /**
     * @param t at the time t of using function
     * @param y at the state of using function
     * @return motion of each planet
     */
    public IAccelerationRate call(double t, IState y);
}
