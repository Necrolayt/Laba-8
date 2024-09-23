import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Lab8Ex11 {
    public static void main(String[] args) {

        try {
            // Создаем и заполняем исходный файл
            String inputFileName = "input.txt";
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(inputFileName), StandardCharsets.UTF_8))){
                writer.write("Первая строка");
                writer.newLine();
                writer.write("Вторая строка");
                writer.newLine();
                writer.write("3.14 -2.71 0.0 42.0 -1.0");
            }

            // Выводим содержимое исходного файла
            System.out.println("Содержимое исходного файла:");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), StandardCharsets.UTF_8))){
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            System.out.println();

            // Считываем данные из исходного файла
            List<String> lines = new ArrayList<>();
            List<Double> numbers = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), StandardCharsets.UTF_8))){
                String line;
                int lineCount = 0;
                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    if (lineCount <= 2) {
                        lines.add(line);
                    } else {
                        String[] numStrings = line.split("\\s+");
                        for (String numStr : numStrings) {
                            numbers.add(Double.parseDouble(numStr));
                        }
                    }
                }
            }

            // Отбираем вторую строку и положительные числа
            String secondLine = lines.size() >= 2 ? lines.get(1) : "";
            List<Double> positiveNumbers = new ArrayList<>();
            for (Double num : numbers) {
                if (num > 0) {
                    positiveNumbers.add(num);
                }
            }

            // Записываем выбранные данные в результирующий файл
            String outputFileName = "output.txt";
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8))){
                writer.write(secondLine);
                writer.newLine();
                for (Double num : positiveNumbers) {
                    writer.write(num + " ");
                }
            }

            // Выводим содержимое результирующего файла
            System.out.println("Содержимое результирующего файла:");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(outputFileName), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line.trim());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
