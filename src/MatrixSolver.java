public class MatrixSolver {
    private Matrix matrix;

    private double accuracy;
    private int iterationCount = 0;
    private int numberOfX;
    private double[] newX;
    private double[] x;

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double[] solve() throws ArithmeticException {
        MatrixChecker checker = new MatrixChecker(matrix);
        if (checker.isMatrixCorrect()) {
            double oldAccuracy = 0;
            double newAccuracy = 0;
            int errorCount = 0;
            MatrixOutput matrixOutput = new MatrixOutput(System.out);
            matrixOutput.writeTableHeader();
            newX = matrix.getB().clone();
            x = matrix.getB().clone();
            do {
                if (newAccuracy > oldAccuracy) {
                    errorCount++;
                    if (errorCount > 3) {
                        throw new ArithmeticException();
                    }
                }
                makeIteration();
                oldAccuracy = newAccuracy;
                newAccuracy = countMaxDifference();

            } while (newAccuracy > accuracy);
            return newX;
        }else {
            throw new ArithmeticException();
        }
    }

    private void makeIteration() {
        x = newX.clone();
        double[] b = matrix.getB().clone();
        double[][] a = matrix.getA().clone();
        for (int i = 0; i < numberOfX; i++) {
            newX[i] = b[i] / a[i][i] - calculateSumX(i);
        }
        iterationCount++;
    }

    private double calculateSumX(int i) {
        double[][] a = matrix.getA().clone();
        double sum = 0;
        for (int j = 0; j < numberOfX; j++) {
            if (j != i) {
                sum += a[i][j] / a[i][i] * x[j];
            }
        }
        return sum;
    }

    private double countMaxDifference() {
        double difference = 0;
        for (int i = 0; i < numberOfX; i++) {
            double currentDifference = Math.abs(newX[i] - x[i]);
            if (currentDifference > difference) {
                difference = currentDifference;
            }
        }
        return difference;
    }

    public double[] countCurrentDifferences() {
        double[] dif = newX.clone();
        for (int i = 0; i < dif.length; i++) {
            dif[i] = Math.abs(newX[i] - x[i]);
        }
        return dif;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
        numberOfX = matrix.getSize();
        x = matrix.getB().clone();
        newX = matrix.getB().clone();
    }

    public int getIterationCount() {
        return iterationCount;
    }

    public double getAccuracy() {
        return accuracy;
    }
}
