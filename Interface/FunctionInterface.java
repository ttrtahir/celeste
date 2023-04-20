package Interface;

public interface FunctionInterface {
    //function f represents the differential equation dy/dt = f(t,y)
    //implement this function to represent calculation of acceleration

    //state y(t) comprises both position vector and velocity vector v(t)
    /*so
     * y(t) = (x(t))
     *         v(t)
     * */
    //x¨ = d^2x/dt^2 is its second derivative = acceleration a(t)
    public AccelerationInterface motion(double t, StateInterface y);
}
