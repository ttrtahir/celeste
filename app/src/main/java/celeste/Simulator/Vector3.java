package celeste.Simulator;

import celeste.Interface.IVector3;

/**
 * Represents a 3D vector that can represent the velocities or positions in 3D space 
 */
public class Vector3 implements IVector3 {
    /**
     * 3d Vector data structure
     */
    double x;
    double y;
    double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Adds another vector to this vector and returns the resulting vector.
     *
     * @param another The vector to be added.
     * @return The resulting vector after addition.
     */
    @Override
    public IVector3 add(IVector3 another) {
        Vector3 sum = new Vector3();
        sum.setX(this.x + another.getX());
        sum.setY(this.y + another.getY());
        sum.setZ(this.z + another.getZ());
        return sum;
    }

    /**
     * Subtracts another vector from this vector and returns the resulting vector.
     *
     * @param another The vector to be subtracted.
     * @return The resulting vector after subtraction.
     */
    @Override
    public IVector3 subtract(IVector3 another) {
        Vector3 sum = new Vector3();
        sum.setX(this.x - another.getX());
        sum.setY(this.y - another.getY());
        sum.setZ(this.z - another.getZ());
        return sum;
    }

    /**
     * Multiplies this vector by a scalar value and returns the resulting vector.
     *
     * @param scalar The scalar value to multiply the vector by.
     * @return The resulting vector after multiplication.
     */
    @Override
    public IVector3 multiply(double scalar) {
        Vector3 product = new Vector3();
        product.setX(this.x * scalar);
        product.setY(this.y * scalar);
        product.setZ(this.z * scalar);
        return product;
    }

    /**
     * Calculates the Euclidean distance between this vector and another vector.
     *
     * @param another The vector to calculate the distance to.
     * @return The Euclidean distance between this vector and the given  vector.
     */
    @Override
    public double euclideanDist(IVector3 another) {
        double d = Math.sqrt((Math.pow((this.x - another.getX()), 2) + Math.pow((this.y - another.getY()), 2)
                + Math.pow((this.z - another.getZ()), 2)));
        return d;
    }

    /**
     * Adds the product of a scalar value and another vector to this vector and returns the resulting vector.
     *
     * @param scalar        The scalar value to multiply the other vector by.
     * @param anotherVector The other vector to be added after multiplication.
     * @return The resulting vector after addition and multiplication.
     */
    @Override
    public IVector3 addmultiply(double scalar, IVector3 anotherVector) {
        double x = this.getX() + scalar * anotherVector.getX();
        double y = this.getY() + scalar * anotherVector.getY();
        double z = this.getZ() + scalar * anotherVector.getZ();
        return (IVector3) new Vector3(x, y, z);
    }

    /**
     * Returns the magnitude of the vector, which is the Euclidean distance between this vector and the origin.
     *
     * @return The magnitude of the vector.
     */
    @Override
    public double getMagnitude() {
        return euclideanDist(new Vector3(0, 0, 0));
    }

    @Override
    public String toString() {
        return "Coordinate of this vector: x = " + x + ", y= " + y + ", z= " + z;
    }
}
