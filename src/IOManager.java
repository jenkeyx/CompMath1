import java.io.File;
import java.util.Scanner;

public class IOManager {
    public Matrix loadMatrix(String source){
        Scanner scanner = new Scanner(System.in);
        switch (source){
            case "file":
                   return loadFromFile(scanner);
            case "cl":
                return loadFromCl(scanner);
            default:
                //todo create matrix from random
                return null;
        }
    }
    public Matrix loadFromFile(Scanner in){
        System.out.println("Введите название файла/путь");
        String fileName = in.next();
        if (isFileValid(fileName)) {
            StringBuilder builder = new StringBuilder();
            Scanner scanner = null;
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine()).append(" ");
            }
            return createMatrixFromString(builder.toString());
        }else {
            return null;
        }
    }

    public Matrix loadFromCl(Scanner in){
        StringBuilder srtMatrix = new StringBuilder();
        System.out.println("Введите размер матрицы");
        int n = readInt(in);
        System.out.println("Введите данны матрицы");
        while (in.hasNextLine()){
            srtMatrix.append(in.nextLine());
        }
        return createMatrixFromString(srtMatrix.toString());
    }


    private Matrix createMatrixFromString(String strMatrix){
        Scanner scanner = new Scanner(strMatrix);
        scanner.next();
        int n = scanner.nextInt();
        double[][] a = new double[n][n];
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <=  n; j++) {
                if (j==n){
                    b[i] = readDouble(scanner);
                }else {
                    a[i][j] = readDouble(scanner);
                }
            }
        }
        return new Matrix(n,a,b);
    }


    private boolean isFileValid(String fileName) {
        File matrixFile = new File(fileName);
        if (matrixFile.exists()){
            if (matrixFile.canWrite() && matrixFile.canRead()){
                System.out.println("Файл найден");
                return true;
            }else{
                System.out.println("У данного файла проблемы с доступом");
                return false;
            }
        }else{
            System.out.println("Не найдено файла по указанному пути");
            return false;
        }
    }

    int readInt(Scanner in) {
            return Integer.parseInt(in.next().split(" ")[0].replace(",", "."));
    }

    double readDouble(Scanner in) {
            return Double.parseDouble(in.next().split(" ")[0].replace(",", "."));
    }
}
