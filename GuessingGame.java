import java.util.*;

public class GuessingGame {

    static Scanner input = new Scanner(System.in);
    static String playerName;
    static int min = 1;
    static int max = 100;
    static int maxAttempts = 10;

    public static void main(String[] args) {
        startGame();
    }

    // Start the game
    static void startGame() {
        System.out.println("========================================");
        System.out.println("    WELCOME TO THE GUESSING GAME!");
        System.out.println("========================================");

        System.out.print("Enter your name: ");
        playerName = input.nextLine();

        boolean playAgain;
        do {
            displayWelcome();
            int secretNumber = generateSecretNumber();
            int attemptsUsed = playGame(secretNumber);
            displayStats(secretNumber, attemptsUsed);
            playAgain = askPlayAgain();
        } while (playAgain);

        System.out.println("========================================");
        System.out.println("Thanks for playing, " + playerName + "!");
        System.out.println("See you next time!");
        System.out.println("========================================");
    }

    // Welcome message
    static void displayWelcome() {
        System.out.println("\n========================================");
        System.out.println("    WELCOME TO THE GUESSING GAME!");
        System.out.println("========================================");
        System.out.println("Hello, " + playerName + "!");
        System.out.println("\nI'm thinking of a number between " + min + " and " + max + ".");
        System.out.println("You have " + maxAttempts + " attempts to guess it.");
        System.out.println("I'll give you a hint after each guess.");
        System.out.println("\nLet's begin!");
        System.out.println("========================================");
    }

    // Generate random number
    static int generateSecretNumber() {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    // Get user guess with validation
    static int getUserGuess(int attempt) {
        int guess;

        while (true) {
            System.out.print("\n--- Attempt #" + attempt + " ---\n");
            System.out.print("Enter your guess (" + min + "-" + max + "): ");

            guess = input.nextInt();

            if (guess >= min && guess <= max) {
                return guess;
            } else {
                System.out.println("Invalid! Please enter a number between " + min + " and " + max + ".");
            }
        }
    }

    // Give hint
    static void giveHint(int guess, int secretNumber) {
        if (guess < secretNumber) {
            System.out.println("Too low! Try a higher number.");
        } else if (guess > secretNumber) {
            System.out.println("Too high! Try a lower number.");
        }
    }

    // Main gameplay
    static int playGame(int secretNumber) {
        int attempts = 0;

        while (attempts < maxAttempts) {
            attempts++;
            int guess = getUserGuess(attempts);

            if (guess == secretNumber) {
                System.out.println("\nCongratulations " + playerName + "!");
                System.out.println("You guessed the number " + secretNumber + " in " + attempts + " attempts!");
                return attempts;
            } else {
                giveHint(guess, secretNumber);
            }
        }

        // If failed
        System.out.println("\nGAME OVER!");
        System.out.println("You've used all " + maxAttempts + " attempts.");
        System.out.println("The secret number was " + secretNumber + ".");
        System.out.println("Better luck next time, " + playerName + "!");

        return attempts;
    }

    // Display stats
    static void displayStats(int secretNumber, int attempts) {
        String rating;

        if (attempts == 1) {
            rating = "Perfect!";
        } else if (attempts <= 3) {
            rating = "Excellent!";
        } else if (attempts <= 6) {
            rating = "Good job!";
        } else if (attempts <= 10) {
            rating = "Nice try!";
        } else {
            rating = "Better luck next time!";
        }

        System.out.println("\n========================================");
        System.out.println("            GAME STATISTICS");
        System.out.println("========================================");
        System.out.println("Player: " + playerName);
        System.out.println("Secret Number: " + secretNumber);
        System.out.println("Attempts Used: " + attempts);
        System.out.println("Rating: " + rating);
        System.out.println("========================================");
    }

    // Ask to play again
    static boolean askPlayAgain() {
        System.out.print("\nWould you like to play again, " + playerName + "? (Y/N): ");
        char choice = input.next().toUpperCase().charAt(0);
        input.nextLine(); // clear buffer
        return choice == 'Y';
    }
}