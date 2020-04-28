public class MatrixChecker {
    Matrix matrix;

    public MatrixChecker(Matrix matrix) {
        this.matrix = matrix;
    }


    public boolean isNotZeroDiagonal() {
        boolean flag = true;
        for (int i = 0; i < matrix.getSize(); i++) {
            flag &= (matrix.getA()[i][i] != 0);
        }
        return flag;
    }

    public boolean isDiagonallyDominant() {
        double[][] element = matrix.getA();
        boolean matrixOk = true;
        boolean strict = false;
        for (int i = 0; i < element.length; i++) {
            double otherElements = findSumOtherElements(i);
            if (Math.abs(element[i][i]) > otherElements) {
                strict = true;
            } else {
                if ((Math.abs(element[i][i]) > otherElements)) {
                    matrixOk = false;
                    break;
                }
            }
        }
        return matrixOk && strict;
    }

    private double findSumOtherElements(int row) {
        double[][] matrixA = matrix.getA();
        double sum = 0;
        for (int column = 0; column < matrixA.length; column++) {
            if (row != column) {
                sum += Math.abs(matrixA[row][column]);
            }
        }
        return sum;
    }

    public int findRowOfMaxCoefficient(int column) {
        double[][] element = matrix.getA();
        double max = element[column][column];
        int row = column;
        for (int i = column; i < element.length; i++) {
            if (element[i][column] > max) {
                max = element[i][column];
                row = i;
            }
        }
        return row;
    }

    public boolean isMatrixCorrect(){
        if (isNotZeroDiagonal()){
            if (matrix.calculateDeterminant() != 0){
                if (isDiagonallyDominant()){
                    MatrixOutput output = new MatrixOutput(System.out);
                    output.printSystem(matrix);
                    return true;
                }
            }
        }
        return false;
    }
}
