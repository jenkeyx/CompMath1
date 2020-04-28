import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileService {
    File matrix;

    public FileService(String fileName) {
        while (!isFileValid(fileName)) {
            System.out.println("Указанный файл не может быть использован. Попробуйте еще раз");
            fileName = new Scanner(System.in).next();
        }
        matrix = new File(fileName);
    }

    public String readFile() throws FileNotFoundException {
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(matrix);
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine()).append(" ");
        }
        scanner.close();
        return builder.toString();
    }


    private boolean isFileValid(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            if (file.isFile()) {
                if (file.canRead()) {
                    if (file.canWrite()) {
                        System.out.println("Файл найден.");
                        return true;
                    } else {
                        System.out.println("Файл не может быть изменен.");
                        return false;
                    }
                } else {
                    System.out.println("Файл не может быть прочитан.");
                    return false;
                }
            } else {
                if (file.isDirectory()) {
                    System.out.println("Это директория.");
                } else {
                    System.out.println("Неизвестный вид файла.");
                }
                return false;
            }
        } else {
            System.out.println("Файл не найден.");
            return false;
        }
    }
}
