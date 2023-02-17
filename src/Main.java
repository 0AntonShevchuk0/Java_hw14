import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // Початок програми
    public static void main(String[] args) {
        // Константні шляхи до файлів
        final String fileToReplacePath = "files/toReplace.txt";
        final String fileReplacedPath = "files/replaced.txt";

        final String txtFilePath = "files/file.txt";
        final String bakFileUnfinishedPath = "files/";

        final String splitFile1Path = "files/splitFile1.txt";
        final String splitFile2Path = "files/splitFile2.txt";

        final String encryptedFilePath = "files/fileEncrypted.txt";
        final String decryptedFilePath = "files/fileDecrypted.txt";

        // Заміна рядків у файлі
        if (FileProcessor.replaceInFile("hi", "hello", fileToReplacePath, fileReplacedPath)) {
            System.out.println("The contents of the file have been successfully replaced");
        }

        // Зчитування назви файлу
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter .bak file name: ");
        String fileName = scanner.next();

        // Копіювання файлу
        if (FileProcessor.copy(txtFilePath, bakFileUnfinishedPath + fileName + ".bak")) {
            System.out.println("File successfully copied");
        }

        // Розділення файлу на 2 половини
        if (FileProcessor.split(txtFilePath, splitFile1Path, splitFile2Path)) {
            System.out.println("File successfully split");
        }

        // Блок, де може виникнути помилка
        try {
            // Введення ключа шифрування
            System.out.print("Enter the key: ");
            int key = Integer.parseInt(scanner.next());

            // Шифрування файлу
            if (FileProcessor.encrypt(fileToReplacePath, encryptedFilePath, key)) {
                System.out.println("File successfully encrypted");
            }

            // Дешифрування
            if (FileProcessor.decrypt(encryptedFilePath, decryptedFilePath, key)) {
                System.out.println("File successfully decrypted");
            }
        }
        // Обробка помилок
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
