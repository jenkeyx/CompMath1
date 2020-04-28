import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatrixService {
    MatrixSolver matrixSolver = new MatrixSolver();
    Scanner scanner = new Scanner(System.in);

    public void createFromCl() {
        System.out.println("Введите точность");
        matrixSolver.setAccuracy(readDouble());

        System.out.println("Введите размер матрицы");
        Matrix matrix = new Matrix(readInt());

        System.out.println("Введите данные матрицы");
        int n = matrix.getSize();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == n) {
                    matrix.addToB(readDouble(), i);
                } else {
                    matrix.addToA(readDouble(), i, j);
                }
            }
        }
        matrixSolver.setMatrix(matrix);
        printMatrix();
    }

    public void createFromFile() throws FileNotFoundException {
        System.out.println("Введите название файла");
        String fileName = scanner.next();
        FileService fileService = new FileService(fileName);
        FileParser parser = new FileParser(fileService.readFile());
        matrixSolver.setAccuracy(parser.readAccuracy());
        matrixSolver.setMatrix(parser.readMatrix());
        printMatrix();
    }

    private void printMatrix() {
        MatrixOutput output = new MatrixOutput(System.out);
        System.out.println("Точность: " +  matrixSolver.getAccuracy());
        output.writeTableHeader();
        double[] x = matrixSolver.solve();
        double[] diff = matrixSolver.countCurrentDifferences();
        MatrixOutput matrixOutput = new MatrixOutput(System.out);
        for (int i = 0; i < x.length; i++) {
            matrixOutput.writeTableLine(x[i], diff[i]);
        }
        System.out.println("Колличество итераций " + matrixSolver.getIterationCount());
    }

    int readInt() {
        try {
            return Integer.parseInt(scanner.next().split(" ")[0].replace(",", "."));
        } catch (Exception e) {
            System.out.println("Введенные данные некорректны. Попробуйте еще раз");
            return readInt();
        }
    }

    double readDouble() {
        try {
            return Double.parseDouble(scanner.next().split(" ")[0].replace(",", "."));
        } catch (Exception e) {
            System.out.println("Введенные данные некорректны. Попробуйте еще раз");
            return readDouble();
        }
    }

}
