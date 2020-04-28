import java.util.Scanner;

public class FileParser {

    private final String strMatrix;
    Scanner scanner;

    public FileParser(String matrix) {
        strMatrix = matrix;
    }


    public Matrix readMatrix(){
        scanner = new Scanner(strMatrix);
        scanner.next();
        int n = scanner.nextInt();
        double[][]a = new double[n][n];
        double[]b = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <=  n; j++) {
                if (j==n){
                    b[i] = Double.parseDouble(scanner.next().split(" ")[0].replace(",", "."));
                }else {
                    a[i][j] = scanner.nextDouble();
                }
            }
        }
        Matrix matrix = new Matrix(n);
        matrix.setA(a);
        matrix.setB(b);
        scanner.close();
        return matrix;
    }

    public double readAccuracy() {
        scanner = new Scanner(strMatrix);
        double accuracy = Double.parseDouble(scanner.next().split(" ")[0].replace(",", "."));
        scanner.close();
        return accuracy;
    }
}
