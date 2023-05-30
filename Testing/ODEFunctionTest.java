package Testing;

import Simulator.CelestialBodies.CelestialBodyValues;
import Simulator.CelestialBodies.ODEFunction;
import Simulator.Solvers.EulerSolver;
import Simulator.Solvers.RK2;
import Simulator.Solvers.RK3;
import Simulator.Solvers.RK4;
import Simulator.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ODEFunctionTest {
    static final double ACCURACY = 1.0; // titan
    static final double EXPECTED_X = 1.2545017149186268E+09; // after 1 time step
    static final double EXPECTED_Y = -7.613401879821144E+08;
    static final double EXPECTED_Z = -3.630963635090902E+07;
    static CelestialBodyValues celestialBodyValues = new CelestialBodyValues();
    static final int T = 0; // time point
    static final int H = 10; // step size
    static final ODEFunction odeF = new ODEFunction();

    @Test
    void testEulerX() {
        EulerSolver eulersolver = new EulerSolver();
        State y0 = new State();
        y0.inputState();
        State eulersolverStep = (State) (eulersolver.step(odeF, T, y0, H, false));
        assertEquals(EXPECTED_X, eulersolverStep.state[8][0].getX(), ACCURACY);

    }

    @Test
    void testEulerY() {
        EulerSolver eulersolver = new EulerSolver();
        State y0 = new State();
        y0.inputState();
        State eulersolverStep = (State) (eulersolver.step(odeF, T, y0, H, false));
        assertEquals(EXPECTED_Y, eulersolverStep.state[8][0].getY(), ACCURACY);
    }

    @Test
    void testEulerZ() {
        EulerSolver eulersolver = new EulerSolver();
        State y0 = new State();
        y0.inputState();
        State eulersolverStep = (State) (eulersolver.step(odeF, T, y0, H, false));
        assertEquals(EXPECTED_Z, eulersolverStep.state[8][0].getZ(), ACCURACY);
    }

    @Test
    void RK4X() {
        RK4 rungeKutta4 = new RK4();
        State y0 = new State();
        y0.inputState();
        State rkStep = (State) (rungeKutta4.step(odeF, T, y0, H, false));
        assertEquals(EXPECTED_X, rkStep.state[8][0].getX(), ACCURACY);
    }

    @Test
    void RK4Y() {
        RK4 rungeKutta4 = new RK4();
        State y0 = new State();
        y0.inputState();
        State rkStep = (State) (rungeKutta4.step(odeF, T, y0, H, false));
        assertEquals(EXPECTED_Y, rkStep.state[8][0].getY(), ACCURACY);
    }

    @Test
    void RK4Z() {
        RK4 rungeKutta4 = new RK4();
        State y0 = new State();
        y0.inputState();
        State rkStep = (State) (rungeKutta4.step(odeF, T, y0, H, false));
        assertEquals(EXPECTED_Z, rkStep.state[8][0].getZ(), ACCURACY);
    }

    @Test
    void RK2X() {
        RK2 rungeKutta2 = new RK2();
        State y0 = new State();
        y0.inputState();
        State rkStep = (State) (rungeKutta2.step(odeF, T, y0, H, false));
        assertEquals(EXPECTED_X, rkStep.state[8][0].getX(), ACCURACY);
    }

    @Test
    void RK2Y() {
        RK2 rungeKutta2 = new RK2();
        State y0 = new State();
        y0.inputState();
        State rkStep = (State) (rungeKutta2.step(odeF, T, y0, H, false));
        assertEquals(EXPECTED_Y, rkStep.state[8][0].getY(), ACCURACY);
    }

    @Test
    void RK2Z() {
        RK2 rungeKutta2 = new RK2();
        State y0 = new State();
        y0.inputState();
        State rkStep = (State) (rungeKutta2.step(odeF, T, y0, H, false));
        assertEquals(EXPECTED_Z, rkStep.state[8][0].getZ(), ACCURACY);
    }

    @Test
    void RK3X() {
        RK3 rungeKutta3 = new RK3();
        State y0 = new State();
        y0.inputState();
        State rkStep = (State) (rungeKutta3.step(odeF, T, y0, H, false));
        assertEquals(EXPECTED_X, rkStep.state[8][0].getX(), ACCURACY);
    }

    @Test
    void RK3Y() {
        RK3 rungeKutta3 = new RK3();
        State y0 = new State();
        y0.inputState();
        State rkStep = (State) (rungeKutta3.step(odeF, T, y0, H, false));
        assertEquals(EXPECTED_Y, rkStep.state[8][0].getY(), ACCURACY);
    }

    @Test
    void RK3Z() {
        RK3 rungeKutta3 = new RK3();
        State y0 = new State();
        y0.inputState();
        State rkStep = (State) (rungeKutta3.step(odeF, T, y0, H, false));
        assertEquals(EXPECTED_Z, rkStep.state[8][0].getZ(), ACCURACY);
    }

}