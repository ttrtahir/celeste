package Physics;

import Interface.Vector3Interface;

public class Vector3 implements Vector3Interface {
    /**
     * 3d Vector data structure
     * */
    double x;
    double y;
    double z;

    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3(){
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

    @Override
    public Vector3Interface add(Vector3Interface another) {
        Vector3 sum = new Vector3();
        sum.setX(this.x + another.getX());
        sum.setY(this.y+ another.getY());
        sum.setZ(this.z + another.getZ());
        return sum;
    }

    @Override
    public Vector3Interface subtract(Vector3Interface another) {
        Vector3 sum = new Vector3();
        sum.setX(this.x - another.getX());
        sum.setY(this.y - another.getY());
        sum.setZ(this.z - another.getZ()) ;
        return sum;
    }

    @Override
    public Vector3Interface multiply(double scalar) {
        Vector3 product = new Vector3();
        product.setX(this.x * scalar);
        product.setY(this.y * scalar);
        product.setZ(this.z * scalar);
        return product;
    }

    @Override
    public double euclideanDist(Vector3Interface another) {
        double d = Math.sqrt((Math.pow((this.x - another.getX()), 2) + Math.pow((this.y - another.getY()),2) +Math.pow(this.z - another.getZ(),2)));
        return d;
    }

    @Override
    public Vector3Interface addmultiply(double scalar, Vector3Interface anotherVector) {
        return null;
    }

    @Override
    public String toString(){
        return "Coordinate of this vector: x = "+ x + ", y= "+ y + ", z= "+z;
    }
}
