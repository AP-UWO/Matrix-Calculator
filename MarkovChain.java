public class MarkovChain {
    // MarkovChain Instance Variables 
    private Vector stateVector;
    private Matrix transitionMatrix;

    // constructor 
    public MarkovChain(Vector sVector, Matrix tMatrix) {
        stateVector = sVector;
        transitionMatrix = tMatrix;
    }

    // methods that checks if a MarkovChain is valid 
    public boolean isValid() {
        
        // checks if the Matrix is square and the vector has equal columns to the matrix
        if (!(transitionMatrix.getNumCols() == transitionMatrix.getNumRows() && stateVector.getNumCols() == transitionMatrix.getNumCols())) return false;
        
        double equalOne= 0.01;   // Eplsilon to catch rounding errors 
        double vectorSum = 0;   //track the sum of the elements in the vector 

        // loops through matrix and summs up the numbers of each row 
        for (int i = 0; i < transitionMatrix.getNumRows(); i++) {
            double matrixSum = 0;

            for (int j = 0; j < transitionMatrix.getNumCols(); j++) {
                matrixSum += transitionMatrix.getElement(i, j);
            }  

            //checks if the sum of values = 1 
            if (Math.abs(matrixSum-1) > equalOne) {
                return false;
            }
        }

        // adds all the elements in the vector 
        for(int i = 0; i < stateVector.getNumCols(); i++) {
            vectorSum += stateVector.getElement(i);
        }  
        // checks if the elements add up to 1
        if (Math.abs(vectorSum-1)  > equalOne) {
            return false;
        }

        // if it passes all the tests returns true 
        return true;
    }

    // Method to compute the probability of a matrix given a number of steps
    public Matrix computeProbabilityMatrix (int numSteps) {

        // checks if the matrix is vaild 
        if (!(this.isValid()))return null; 
        
        // creates new matrix to hold the probability of the matrix 
        Matrix newMatrix = transitionMatrix;

        // multiplies the transition matrix by itself "number of Steps" times 
        for(int i = 0; i < numSteps -1; i++){
            newMatrix = newMatrix.multiply(transitionMatrix);
        }
        
        //finalty multiplies it my the state verctor 
        newMatrix = stateVector.multiply(newMatrix);
        
        // returns the matrix with the new probability 
        return newMatrix;
    }
}
