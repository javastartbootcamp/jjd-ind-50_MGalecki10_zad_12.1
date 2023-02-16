import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "operations.txt";
        int lines = countLines(fileName);
        String[] numbersAndSings = readLine(fileName, lines);
        double result = createArray(numbersAndSings);
        try (
                var fileWriter = new FileWriter(fileName, true);
                var writer = new BufferedWriter(fileWriter);
        ) {
            writer.write(" = " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double createArray(String[] numbersAndSings) {
        for (String numbersAndSing : numbersAndSings) {
            String[] splitArray = numbersAndSing.split(" ");
            int firstNumber = Integer.parseInt(splitArray[0]);
            String sign = splitArray[1];
            int secondNumber = Integer.parseInt(splitArray[2]);
            return choiceSign(firstNumber, sign, secondNumber);
        }
        return 0;
    }

    private static double choiceSign(int firstNumber, String sign, int secondNumber) {
        return switch (sign) {
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> firstNumber / secondNumber;
            default -> 0;
        };
    }

    private static String[] readLine(String fileName, int lines) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        String[] data = new String[lines];
        for (int i = 0; i < data.length; i++) {
            data[i] = scan.nextLine();
        }
        return data;
    }

    private static int countLines(String fileName) throws FileNotFoundException {
        Scanner scannerFile = new Scanner(new File(fileName));
        int lines = 0;
        while (scannerFile.hasNextLine()) {
            scannerFile.nextLine();
            lines++;
        }
        return lines;
    }
}