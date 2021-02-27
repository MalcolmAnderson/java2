package utils;

import java.util.Scanner;

public class KeyboardInput {
    Scanner keyboard = new Scanner(System.in);

    public String getNextLineFromKeyboardWithPrompt(String prompt){
        System.out.print(prompt);
        String outString = keyboard.nextLine();
        return outString;
    }

}
