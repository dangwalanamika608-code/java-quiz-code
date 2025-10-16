import java.util.Random;
import java.util.Scanner;


public class NumberGuessGame {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain = true;
        while (playAgain) {
            playRound();
            System.out.print("Play again? (y/n): ");
            String again = SCANNER.nextLine().trim().toLowerCase();
            playAgain = again.equals("y") || again.equals("yes");
            System.out.println();
        }

        System.out.println("Thanks for playing! Goodbye ");
       
    }

    private static void playRound() {
        Difficulty diff = chooseDifficulty();
        int secret = RANDOM.nextInt(diff.range) + 1; // 1..range
        int attemptsLeft = diff.attempts;
        int maxAttempts = diff.attempts;

        int pointsPerAttempt = (int) Math.ceil((double) diff.range / (double) maxAttempts);

        System.out.printf("\nI have picked a number between 1 and %d. You have %d attempts. Good luck!\n",
                diff.range, diff.attempts);

        boolean won = false;
        while (attemptsLeft > 0) {
            System.out.printf("Attempts left: %d. Enter your guess: ", attemptsLeft);
            String input = SCANNER.nextLine().trim();

            int guess;
            try {
                guess = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
                continue;
            }

            if (guess < 1 || guess > diff.range) {
                System.out.printf("Please guess a number inside the range 1 to %d.%n", diff.range);
                continue;
            }

            if (guess == secret) {
                won = true;
                int score = attemptsLeft * pointsPerAttempt;
                System.out.printf("Correct! You guessed the number %d.\n", secret);
                System.out.printf("Remaining attempts: %d -> Score: %d points.%n", attemptsLeft, score);
                break;
            } else if (guess < secret) {
                attemptsLeft--;
                System.out.println("Higher  (try a larger number)");
            } else { // guess > secret
                attemptsLeft--;
                System.out.println("Lower  (try a smaller number)");
            }
        }

        if (!won) {
            System.out.printf("Out of attempts! The number was %d. Better luck next time.\n", secret);
            System.out.printf("Score: 0 points.%n");
        }

        // Quick note on scoring details
        System.out.printf("(Scoring rule: %d points per remaining attempt for this difficulty.)\n\n", pointsPerAttempt);
    }

    private static Difficulty chooseDifficulty() {
        while (true) {
            System.out.println("Choose difficulty:");
            System.out.println("  1) Easy   - 1 to 20   (7 attempts)");
            System.out.println("  2) Medium - 1 to 50   (5 attempts)");
            System.out.println("  3) Hard   - 1 to 100  (3 attempts)");
            System.out.print("Enter 1, 2 or 3: ");

            String choice = SCANNER.nextLine().trim();
            switch (choice) {
                case "1":
                    return new Difficulty("Easy", 20, 7);
                case "2":
                    return new Difficulty("Medium", 50, 5);
                case "3":
                    return new Difficulty("Hard", 100, 3);
                default:
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.\n");
            }
        }
    }

    // small helper class for difficulty settings
    private static class Difficulty {
        final String name;
        final int range;
        final int attempts;

        Difficulty(String name, int range, int attempts) {
            this.name = name;
            this.range = range;
            this.attempts = attempts;
        }
    }
}
