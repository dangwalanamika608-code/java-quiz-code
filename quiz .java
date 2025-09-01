import java.util.Scanner;

public class quiz {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        // Questions array
        String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "What is 2 + 2?",
            "Which animal lays eggs?",
            "What is the largest ocean on Earth?"
        };

        
        String[][] options = {
            {"A. Berlin", "B. Paris", "C. Rome", "D. Madrid"},
            {"A. Earth", "B. Mars", "C. Jupiter", "D. Venus"},
            {"A. 3", "B. 4", "C. 5", "D. 6"},
            {"A. Dog", "B. Chicken", "C. Cow", "D. Cat"},
            {"A. Atlantic", "B. Indian", "C. Arctic", "D. Pacific"}
        };

        // Correct answers array
        char[] correctAnswers = {'B', 'B', 'B', 'B', 'D'};

        int score = 0;

        System.out.println("Welcome to the Java Quiz!");


        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);
