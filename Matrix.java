public class Matrix {
    // Matrix instance varialbles, keeps tracks of rows columns and stores numbers in matrix 
    private int numRows;
    private int numCols;
    private double[][] data;

    // Basic Matrix Constructor 
    public Matrix(int row, int column) {
        numRows = row;
        numCols = column;
        data = new double[row][column];
    }

    // Overflow Matrix constructor for when numbers are given for the matrix 
    public Matrix(int row, int column, double[] linArr) {
        numRows = row;
        numCols = column;
        data = new double[row][column];

         
        int count = 0; //Counter to keep track of current element

        // loop that fills Matrix with given array 
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                data[i][j] = linArr[count];
                count++;
            }
        }
        
    }

    // getter for the number of rows in a matrix 
    public int getNumRows() {
        return numRows;
    }

    // getter for the number of columns in a matrix 
    public int getNumCols() {
        return numCols;
    }

    // getter for the Array of numbers in a matrix 
    public double[][] getData() {
        return data;
    }

    // getter for an element in a matrix 
    public double getElement(int row, int column) {
        return data[row][column];
    }

    // setter for a element in a matrix
    public void setElement(int row, int column, double val) {
        data[row][column] = val;
    }

    // changes the orientation of a matrix by making its column its rows and viseversa 
    public void transpose() {

        // created a temporary matrix with switched rows and columns of the matrix that calls the method
        Matrix tempMatrix = new Matrix(this.getNumCols(), this.getNumRows());

        // cycles through matrix and matching elements to new Matrix
        for (int i = 0; i < tempMatrix.getNumRows(); i++) {
            for (int j = 0; j < tempMatrix.getNumCols(); j++) {
                tempMatrix.setElement(i,j,this.getElement(j,i));
            }
        }

        // setting inputed array to new transposed version 
        this.data = tempMatrix.data;    // updates data
        this.numRows = tempMatrix.numRows;  //updates rows
        this.numCols = tempMatrix. numCols; //updates columns 
    }

    // Method to multiple an array by a scalar
    public Matrix multiply (double scalar) {

        // creates a new Matrix to hold the new multipled Matrix 
        Matrix newMatrix = new Matrix(this.getNumRows(),this.getNumCols());

        // loops through a matrix and multiples its values by the inputed scalar
        for (int i = 0; i < this.getNumRows(); i++) {
            for (int j = 0; j < this.getNumCols(); j++) {
                newMatrix.data[i][j] = this.getElement(i, j) * scalar ;     
            } 
        }
        return newMatrix; // returns new multipled Matrix 
    }

    // Method that multiplies two Matrix's 
    public Matrix multiply( Matrix other) {

        // checks if the number of columns of the first matrix is equal to the number of rows of the second matrix 
        if (this.getNumCols() != other.getNumRows()) return null;

        // creates matrix to store the new multiplied Matrix elements 
        Matrix newMatrix = new Matrix(this.getNumRows(), other.getNumCols());

        // computes matrix multiplication and assigns new values to newMatrix
        for (int i = 0; i < this.getNumRows(); i++) {
            for (int j = 0; j < other.getNumCols(); j++) {
                for (int k = 0; k < other.getNumRows();k++) {

                    newMatrix.data[i][j] += (this.getElement(i,k) * other.getElement(k,j) );
                }
            }
        }
        // returns the multiplied Matrix 
            return newMatrix;
    }

    //to string method for a simple image of a matrix 
    public String toString() {

        // check if the matrix has data 
        if( this.numCols ==0|| this.numRows == 0|| this.data == null) {
            return "Empty matrix";
        }

        String matrix = "";     // string to be returned with matrix information
        // adds data to string with each number being 8 characters apart and rounded to the 3rd decimal place
        for (int i = 0; i < this.getNumRows(); i++) {
            for (int j = 0; j < this.getNumCols(); j++) {
                matrix += String.format("%8.3f", this.data[i][j]);
            }
            
            // shifts down between each row 
            if(i != numRows -1) {
                matrix+= "\n";
            }
        }
       return matrix;   // the string of the matrix 
    }  


    public static void main(String[] args) {
        Matrix m1 = new Matrix(2, 1, new double[] { 1, 2 });
        Matrix m2 = new Matrix(2, 2, new double[] { 3, 4 , 5, 6});

        Matrix m3 = new Matrix(2, 4, new double[] { 1, 2, 3, 4 , 5, 6, 7, 8});
        Matrix m4 = new Matrix(4, 1, new double[] { 1, 2, 3, 4});

        System.out.println(m3);
        System.out.println();
        System.out.println(m4);
        System.out.println();
        //System.out.println(m2.multiply(m1));

        System.out.println(m3.multiply(m4));
    }

    
}
