import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class Matrix {
    private double[][] a;
    private double[] b;
    private final int size;

    public Matrix(int n) {
        a = new double[n][n];
        b = new double[n];
        size = n;
    }
    public Matrix( int n, double[][]a,double[]b){
        size = n;
        this.a = a;
        this.b = b;
    }

    public double[][] getA() {
        return a;
    }

    public void setA(double[][] a) {
        this.a = a;
    }

    public double[] getB() {
        return b;
    }

    public void setB(double[] b) {
        this.b = b;
    }

    public int getSize() {
        return size;
    }

    public void addToA(double element, int i, int j) {
        a[i][j] = element;

    }

    public void addToB(double element, int i) {
        b[i] = element;
    }

    public double calculateDeterminant() {
        int n = a.length;
        double[][] arr = new double[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.copyOf(a[i], a[i].length);
        }

        BigInteger prime = BigInteger.probablePrime(n + 4, new Random());

        double det = 1;

        for (int row = 0; row < n; ++row) {
            int currentRow = row;
            while (currentRow < n && arr[currentRow][row] == 0) {
                ++currentRow;
            }
            if (currentRow == n) {
                return 0;
            }

            if (currentRow != row) {
                det = -1 * det;
                double[] tmp = arr[currentRow];
                arr[currentRow] = arr[row];
                arr[row] = tmp;
            }

            double inverse = (1/(arr[row][row])) % prime.intValue() ;

            for (currentRow = row + 1; currentRow < n; ++currentRow) {
                if (arr[currentRow][row] == 0) {
                    continue;
                }
                double coefficient = arr[currentRow][row] * inverse % prime.intValue();
                for (int column = row; column < n; ++column) {
                    arr[currentRow][column] = arr[currentRow][column] - (arr[row][column] * coefficient % prime.intValue()) % prime.intValue();
                }
            }

        }

        for (int i = 0; i < n; ++i) {
            det = det * (arr[i][i]) % prime.intValue();
        }
        det = det + prime.intValue();
        det = det % prime.intValue();
        if ((det * 2) > prime.intValue()) {
            det = (prime.intValue() - det)%prime.intValue();
        }
        return det;
    }

}

