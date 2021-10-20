import java.util.Scanner;

class Main {


    private static String generatedNum = "";

    private static void startGameMessage() {
        System.out.println("Начало игры!");
    }

    private static String genRandomKey() {
        int randomNumber = 1000 + ((int) (Math.random() * 10000) % 9000);
        generatedNum = Integer.toString(randomNumber);
        while (isUniqueNumbers(generatedNum)) {
            genRandomKey();
        }
        System.out.println(generatedNum);
        return generatedNum;
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        startGameMessage();
        genRandomKey();

        int bulls = 0;
        int cows = 0;
        int playerTries = 1;

        while (bulls != 4) {
            System.out.print("Запрос " + playerTries + ": ");
            String playerKey = sc.nextLine();
            bulls = checkingForBulls(playerKey, generatedNum);
            cows = checkingForCows(playerKey, generatedNum);


            if (isUniqueNumbers(playerKey)) {
                System.out.println("Вы должны ввести число без повторений");
            } else if (bulls == 1 && cows == 1) {
                System.out.printf("У вас %d бык и %d корова\n", bulls, cows);
                playerTries++;
            } else if (bulls == 1 && cows > 1) {
                System.out.printf("У вас %d бык и %d коровы\n", bulls, cows);
                playerTries++;
            } else if (bulls > 1 && cows == 1) {
                System.out.printf("У вас %d быков и %d корова\n", bulls, cows);
                playerTries++;
            } else if (bulls > 1 && cows > 1) {
                System.out.printf("У вас %d быка и %d коровы\n", bulls, cows);
                playerTries++;
            } else if (bulls == 0 && cows > 1) {
                System.out.printf("У вас %d быков и %d коровы\n", bulls, cows);
                playerTries++;
            } else if (bulls == 0 && cows == 1) {
                System.out.printf("У вас %d быков и %d корова\n", bulls, cows);
                playerTries++;
            } else if (bulls > 1 && cows == 0 && bulls != 4) {
                System.out.printf("У вас %d быка и %d коров\n", bulls, cows);
                playerTries++;
            } else if (bulls == 1 && cows == 0) {
                System.out.printf("У вас %d бык и %d коров\n", bulls, cows);
                playerTries++;
            } else if (bulls == 0 & cows == 0) {
                System.out.printf("\nУ вас %d быков и %d коров\n", bulls, cows);
                playerTries++;
            } else if (bulls == 4) {
                if (playerTries == 2) {
                    System.out.println("Строка была угадана за 1" + " попытку");
                } else if (playerTries >= 2 || playerTries <= 4) {
                    System.out.println("Строка была угадана за " + playerTries + " попытки");
                } else if (playerTries >= 5) {
                    System.out.println("Строка была угадана за " + playerTries + " попыток");
                }
            }
        }
        sc.close();
    }
}
