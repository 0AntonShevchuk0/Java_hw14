import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

// Всі функції статичні, бо в об'єктах немає необхідності
public class FileProcessor {
    // Зам
    public static boolean replaceInFile(String toBeReplaced, String replaceTo, String inputPath, String outputPath) {
        try {
            // Створення потоків введення
            FileReader reader = new FileReader(inputPath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            //Створення потоків виведення
            FileWriter writer = new FileWriter(outputPath);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Читання файлу по рядках
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Заповнення файлу
                bufferedWriter.write(line.replaceAll(toBeReplaced, replaceTo));
                bufferedWriter.newLine();
            }

            // Закриття потоків
            bufferedReader.close();
            bufferedWriter.close();
            reader.close();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }

        return true;
    }

    // Копіювання файлу
    public static boolean copy(String inputPath, String outputPath) {
        try {
            // Використання вже існуючої функції для копіювання
            Files.copy(Paths.get(inputPath), Paths.get(outputPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
        return true;
    }

    // Розділення фалйу на 2 разних
    public static boolean split(String inputPath, String outputPath1, String outputPath2) {
        try {
            FileReader reader = new FileReader(inputPath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            FileWriter writer1 = new FileWriter(outputPath1);
            BufferedWriter bufferedWriter1 = new BufferedWriter(writer1);

            FileWriter writer2 = new FileWriter(outputPath2);
            BufferedWriter bufferedWriter2 = new BufferedWriter(writer2);

            String line;
            for (int i = 0; (line = bufferedReader.readLine()) != null; i++) {
                // Пишемо по черзі то в один потік, то в інший
                if (i % 2 == 0) {
                    bufferedWriter1.write(line);
                    bufferedWriter1.newLine();
                } else {
                    bufferedWriter2.write(line);
                    bufferedWriter2.newLine();
                }
            }

            bufferedReader.close();
            bufferedWriter1.close();
            bufferedWriter2.close();
            reader.close();
            writer1.close();
            writer2.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }

        return true;
    }

    // Шифрування файлу
    public static boolean encrypt(String inputPath, String outputPath, int key) {
        try {
            FileReader reader = new FileReader(inputPath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            FileWriter writer = new FileWriter(outputPath);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Посимвольна робота з рядком
                char[] symbols = line.toCharArray();
                for (int i = 0; i < symbols.length; i++) {
                    symbols[i] += key;
                }

                bufferedWriter.write(String.valueOf(symbols));
                bufferedWriter.newLine();
            }

            bufferedReader.close();
            bufferedWriter.close();
            reader.close();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }

        return true;
    }

    // Дешифрування файлу
    public static boolean decrypt(String inputPath, String outputPath, int key) {
        try {
            FileReader reader = new FileReader(inputPath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            FileWriter writer = new FileWriter(outputPath);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                char[] symbols = line.toCharArray();
                for (int i = 0; i < symbols.length; i++) {
                    symbols[i] -= key;
                }

                bufferedWriter.write(String.valueOf(symbols));
                bufferedWriter.newLine();
            }

            bufferedReader.close();
            bufferedWriter.close();
            reader.close();
            writer.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }

        return true;
    }
}


