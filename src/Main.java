import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            switch (scanner.nextLine().trim()) {
                case "read":
                    readFromConsole();
                    break;
                case "file":
                    readFromFile();
                    break;
                case "random":
                    createRandomMatrix();
                    break;
                case "help":
                    printHelpMessage();
                    break;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("Неизвестная команда, введите \"help\" для получения списка команд");
            }
        }
    }

    static void readFromConsole() {
        try {
            MatrixService matrixService = new MatrixService();
            matrixService.createFromCl();
        } catch (Exception e) {
            System.out.println("Введенные данные некорректны");
        }
    }

    static void createRandomMatrix() {
        try {

            System.out.println("Укажите размер матрицы");
        } catch (Exception e) {
            System.out.println("Введен некорректный размер матрицы");
        }
    }

    static void readFromFile() {
        MatrixService matrixService = new MatrixService();
        try {
            matrixService.createFromFile();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Указанный файл не найден");
        }
    }

    static void printHelpMessage() {
        System.out.println("there will be a help message");
    }
}
