package task1;

import java.util.Scanner;
import java.util.Random;
public class Taskone 
{
    public static void main(String args[])
    {
        int min_no = 1, max_no = 100;
        int maxAttempts = 5;
        int score = 0;
        boolean playAgain = true;
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        while (playAgain) 
        {
            int generatedNumber = random.nextInt(max_no - min_no + 1) + min_no;
            System.out.println("************************************************");
            System.out.println("                   WEL-COME");
            System.out.println(" \n		  Number Game");
            System.out.println("************************************************");
           
            System.out.println("Generate a number between " + min_no + " and " + max_no + "!");
            System.out.println("You have " + maxAttempts + " attempts to guess the number!");
            int attempt = 1;
            while (attempt <= maxAttempts) 
            {
                System.out.print( "\nEnter your guess: ");
                int userGuess = sc.nextInt();
                if (userGuess == generatedNumber) 
                {
                    System.out.println("Congratulations! You guessed the correct number!!!");
                    score++;
                    break;
                } 
                else if (userGuess < generatedNumber) 
                {
                    System.out.println("Too low! Try Again!");
                } 
                else 
                {
                    System.out.println("Too high! Try Again!");
                }
                attempt++;
            }
            System.out.println("\nAttempts taken: " + attempt);
            if (attempt > maxAttempts) 
            {
                System.out.println("You ran out of attempts, Better Luck next time!! \n The correct number was: " + generatedNumber);
            }
            System.out.println("\nYour final score is: " +score);
            System.out.println("************************************************");
            System.out.print("\nDo you want to play again? (yes/no): ");
            String x = sc.next();
            playAgain = x.equalsIgnoreCase("yes");
        }
        
    }
}