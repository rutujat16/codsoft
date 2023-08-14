package task1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Task3 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int wordCount = 0;
        System.out.println("Enter a text or provide a file path:");
        String userInput = scanner.nextLine();
        File file = new File(userInput);     // Check if the user input is a file path
        if (file.exists()) 
        {
            try 
            {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) 
                {
                    input += fileScanner.nextLine() + " ";
                }
                fileScanner.close();
            } 
            catch (FileNotFoundException e) 
            {
                System.out.println("File not found!");
                return;
            }
        } 
        else 
        {
            input = userInput;
        }   
        String[] words = input.split("[\\p{Punct}\\s]+"); //Split the input into words using space or punctuation as delimiters
        for (String word : words) 
        {
            if (!word.isEmpty()) 
            {
                wordCount++;
            }
        }
        System.out.println("Total words: " + wordCount);
    }
}