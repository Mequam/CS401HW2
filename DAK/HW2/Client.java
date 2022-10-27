package DAK.HW2;

import java.util.Scanner;
import java.util.regex.*;

public class Client {
    public static String demandPattern(Scanner cin,String pattern,String prompt,String warning) {
        System.out.print(prompt);
        String ret_val = cin.nextLine();

        while (!Pattern.matches(pattern, ret_val)) {
            System.out.print(warning);
            ret_val = cin.nextLine();
        }

        return ret_val;
    }
    public static String demandUsername(Scanner cin,String prompt) {
        return demandPattern(cin, "[A-Z]+", prompt, "invalid name, must only contain A-Z\n" + prompt);
    }
    public static void main(String [] args) {
        Scanner cin = new Scanner(System.in);
        String firstName = demandUsername(cin, "First Name: ");
        System.out.println();
        String lastName = demandUsername(cin, "Last Name: ");


    }
}
