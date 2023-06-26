package celeste.Simulator;
/*
 *  2D Vector data structure that is used for landing
 */
public class Vector2 {
    public double x;
	public double y;

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return this.y;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

     /**
     * Adds another vector to this vector and returns the resulting vector.
     *
     * @param another The vector to be added.
     * @return The resulting vector after addition.
     */
    public Vector2 add(Vector2 another) {
        Vector2 sum = new Vector2();
        sum.setX(this.x + another.getX());
        sum.setY(this.y + another.getY());
        
        return sum;
    }
	
    /**
     * Subtracts another vector from this vector and returns the resulting vector.
     *
     * @param another The vector to be subtracted.
     * @return The resulting vector after subtraction.
     */
    public Vector2 subtract(Vector2 another) {
        Vector2 sum = new Vector2();
        sum.setX(this.x - another.getX());
        sum.setY(this.y - another.getY());
        return sum;
    }

    public Vector2(Vector2 another) {
        this.x = another.x;
        this.y = another.y;
    }
	
    /**
     * Multiplies this vector by a scalar value and returns the resulting vector.
     *
     * @param scalar The scalar value to multiply the vector by.
     * @return The resulting vector after multiplication.
     */
    public Vector2 multiply(double scalar) {
        Vector2 product = new Vector2();
        product.setX(this.x * scalar);
        product.setY(this.y * scalar);
        return product;
    }

	/**
     * Divides this vector by a scalar value and returns the resulting vector.
     *
     * @param scalar The scalar value to divide the vector by.
     * @return The resulting vector after division.
     */
    public Vector2 divide(double scalar) {
        Vector2 product = new Vector2();
        product.setX(this.x / scalar);
        product.setY(this.y / scalar);
        return product;
    }

	/**
     * Creates a new Vector2 object with the same coordinates as this vector.
     *
     * @return A new Vector2 object with the same coordinates.
     */
    public Vector2 duplicate() {
        Vector2 duplicate = new Vector2(this.x, this.y);
        return duplicate;
    }

    @Override
    public String toString() {
        return String.format("xAxis = %f, yAxis = %f", x, y);
    }

   
}
