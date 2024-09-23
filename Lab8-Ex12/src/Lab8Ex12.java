import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Lab8Ex12 {
    public static void main(String[] args) {
        try {
            // Создаем и заполняем исходный файл
            String inputFileName = "text_input.txt";
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inputFileName), StandardCharsets.UTF_8))){
                writer.write("Меняются и время и мечты,");
                writer.newLine();
                writer.write("Меняются, как время представленья,");
                writer.newLine();
                writer.write("Изменчивы под солнцем все явленья,");
                writer.newLine();
                writer.write("И мир, всечасно, видишь новым ты.");
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

            // Обрабатываем файл и записываем результат
            String outputFileName = "text_output.txt";
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), StandardCharsets.UTF_8));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8))){

                String line;
                int lineNumber = 0;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    String[] words = line.split("\\s+|\\p{Punct}");
                    List<String> consonantWords = new ArrayList<>();
                    for (String word : words) {
                        if (!word.isEmpty() && startsWithConsonant(word)) {
                            consonantWords.add(word);
                        }
                    }
                    if (!consonantWords.isEmpty()) {
                        writer.write("Строка " + lineNumber + ": ");
                        for (String word : consonantWords) {
                            writer.write(word + " ");
                        }
                        writer.write("(Количество слов: " + consonantWords.size() + ")");
                        writer.newLine();
                    }
                }
            }

            // Выводим содержимое результирующего файла
            System.out.println("Содержимое результирующего файла:");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(outputFileName), StandardCharsets.UTF_8))){
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line.trim());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для проверки, начинается ли слово с согласной буквы
    private static boolean startsWithConsonant(String word) {
        char firstChar = Character.toLowerCase(word.charAt(0));
        String vowels = "аеёиоуыэюя";
        return vowels.indexOf(firstChar) == -1 && Character.isLetter(firstChar);
    }
}
