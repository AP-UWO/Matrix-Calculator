public class Vector extends Matrix {

    // simple vector constructor making a 1 dimensional Matrix 
    public Vector(int columns) {
        super(1, columns);
    }

    // overflow constructor that adds the given array into the vectors data
    public Vector(int columns, double[] linArr) {
        super(1, columns, linArr);
    }

    // getter for an element in a vector 
    public double getElement(int columns) {
        return super.getElement(0, columns);
    }

}


