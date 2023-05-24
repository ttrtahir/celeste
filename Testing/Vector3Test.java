package Testing;

import Interface.IVector3;
import Simulator.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3Test {

    @Test
    void getX() {
        IVector3 v = new Vector3(-1.5, 0.2, 1.1);
        assertEquals(-1.5, v.getX());
    }

    @Test
    void setX() {
        IVector3 v = new Vector3();
        v.setX(-1.5);
        assertEquals(-1.5, v.getX());
    }

    @Test
    void getY() {
        IVector3 v = new Vector3(-1.5, 0.2, 1.1);
        assertEquals(0.2, v.getY());
    }

    @Test
    void setY() {
        IVector3 v = new Vector3();
        v.setY(0.2);
        assertEquals(0.2, v.getY());
    }

    @Test
    void getZ() {
        IVector3 v = new Vector3(-1.5, 0.2, 1.1);
        assertEquals(1.1, v.getZ());
    }

    @Test
    void setZ() {
        IVector3 v = new Vector3();
        v.setZ(1.1);
        assertEquals(1.1, v.getZ());
    }

    @Test
    void add() {
        IVector3 a = new Vector3(-1.5, 0.2, 1.1);
        IVector3 b = new Vector3(0.5, 0.6, 0.7);
        IVector3 ab = a.add(b);
        assertEquals(-1.5 + 0.5, ab.getX());
        assertEquals(0.2 + 0.6, ab.getY());
        assertEquals(1.1 + 0.7, ab.getZ());
    }

    @Test
    void subtract() {
        IVector3 a = new Vector3(-1.5, 0.2, 1.1);
        IVector3 b = new Vector3(0.5, 0.6, 0.7);
        IVector3 ab = a.subtract(b);
        assertEquals(-1.5 - 0.5, ab.getX());
        assertEquals(0.2 - 0.6, ab.getY());
        assertEquals(1.1 - 0.7, ab.getZ());
    }

    @Test
    void multiply() {
        IVector3 a = new Vector3(-1.5, 0.2, 1.1);
        IVector3 b = a.multiply(0.5);
        assertEquals(-1.5 * 0.5, b.getX());
        assertEquals(0.2 * 0.5, b.getY());
        assertEquals(1.1 * 0.5, b.getZ());
    }

    @Test
    void euclideanDist() {
        IVector3 a = new Vector3(3.0, 4.0, 8.0);
        IVector3 b = new Vector3(0.5, 0.25, 0.5);
        assertEquals(8.75, a.euclideanDist((b)));
    }

    @Test
    void addmultiply() {
        IVector3 a = new Vector3(0.7, 0.8, 0.9);
        IVector3 b = new Vector3(-1.2, 0.2, 1.1);
        IVector3 ab = a.addmultiply(0.5, b);
        assertEquals(0.7 + 0.5 * (-1.2), ab.getX());
        assertEquals(0.8 + 0.5 * 0.2, ab.getY());
        assertEquals(0.9 + 0.5 * 1.1, ab.getZ());
    }

    @Test
    void testToString() {
        IVector3 v = new Vector3(-1.2, 0.2, 1.1);
        String vecString = "Coordinate of this vector: x = -1.2, y= 0.2, z= 1.1";
        assertEquals(vecString, v.toString());
    }
}