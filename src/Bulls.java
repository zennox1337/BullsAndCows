import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    private static String generatedNum = "";
    private static PrintStream pw;

    static {
        try {
            pw = new PrintStream(new FileOutputStream("log.txt", true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Main() {
    }

    private static void startGameMessage() {
        System.out.println("Начало игры!");
    }

    private static String genRandomKey() {
        int randomNumber = 1000 + ((int) (Math.random() * 10000) % 9000);
        generatedNum = Integer.toString(randomNumber);
        while (isUniqueNumbers(generatedNum)) {
            genRandomKey();
        }
        return generatedNum;
    }

    private static int gameFinder() throws IOException {
        int gamesCount = 1;
        List<String> list = Files.readAllLines(Path.of("log.txt"));
        Pattern pattern = Pattern.compile("(№)(\\d)");
        Matcher match = pattern.matcher(list.toString());
        while (match.find()) {
            gamesCount = Integer.parseInt(match.group(2)) + 1;
        }
        return gamesCount;
    }

    private static void logger() throws IOException {
        String gameMessage = "Game №" + gameFinder() + " " + LocalDate.now() + " Загаданная строка " + generatedNum + "\n";
        pw.append(gameMessage);
    }
    private static int tries = 0;
    private static void logger(int bulls, int cows) {
        String gameMessage = "";
        if (bulls == 1 && cows == 1) {
            tries++;
            gameMessage = "У вас " + bulls + " бык и " + cows + " корова";
        } else if (bulls == 1 && cows > 1) {
            tries++;
            gameMessage = "У вас " + bulls + " бык и " + cows + " коровы";
        } else if (bulls > 1 && cows == 1) {
            tries++;
            gameMessage = "У вас " + bulls + " быков и " + cows + " корова";
        } else if (bulls > 1 && cows > 1) {
            tries++;
            gameMessage = "У вас " + bulls + " быка и " + cows + " коровы";
        } else if (bulls == 0 && cows > 1) {
            tries++;
            gameMessage = "У вас " + bulls + " быков и " + cows + " коровы";
        } else if (bulls == 0 && cows == 1) {
            tries++;
            gameMessage = "У вас " + bulls + " быков и " + cows + " корова";
        } else if (bulls > 1 && cows == 0 && bulls != 4) {
            tries++;
            gameMessage = "У вас " + bulls + " быка и " + cows + " коров";
        } else if (bulls == 1 && cows == 0) {
            tries++;
            gameMessage = "У вас " + bulls + " бык и " + cows + " коров";
        } else if (bulls == 0 & cows == 0) {
            tries++;
            gameMessage = "У вас " + bulls + " быков и " + cows + " коров";
        } else if (bulls==4){
            tries++;
            gameMessage = "Вы победили, количество попыток " + tries++;;
        }
        System.out.println(gameMessage);
        pw.append(gameMessage + "\n");
    }

    private static boolean isUniqueNumbers(String num) {
        for (int i = 0; i < num.length() - 1; i++) {
            for (int j = i + 1; j < num.length(); j++) {
                if (num.charAt(i) == num.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int checkingForBulls(String num1, String num2) {
        int bullsCount = 0;

        for (int i = 0; i < num1.length(); i++) {
            if (num1.charAt(i) == num2.charAt(i)) {
                bullsCount++;
            }
        }
        return bullsCount;
    }

    private static int checkingForCows(String num1, String num2) {
        int cowsCount = 0;
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                if (i != j) {
                    if (num1.charAt(i) == num2.charAt(j)) {
                        cowsCount++;
                    }
                }
            }
        }
        return cowsCount;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        startGameMessage();
        genRandomKey();

        int bulls = 0;
        int cows = 0;
        int playerAttempts = 1;

        boolean flag = false;
        while (true) {
            System.out.print("Запрос " + playerAttempts + ": ");
            String playerKey = sc.nextLine();
            if (!flag) {
                logger();
                flag = true;
            }
            bulls = checkingForBulls(playerKey, generatedNum);
            cows = checkingForCows(playerKey, generatedNum);
            logger(bulls, cows);
             if (bulls == 4) break;
             playerAttempts++;
        }
        sc.close();
    }
}
