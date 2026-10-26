import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    // Contributor: Chris Hernandez, Halil Keles, Jason Nguyen.
    // Shared support:
    // - Chris Hernandez:
    //   Applications a and b: palindrome checker and postfix evaluator.
    // - Halil Keles:  MyStack method used by c and d
    //   Applications c and d: prefix evaluator and balanced parentheses.
    // - Jason Nguyen: MyStack methods used by e and f.
    //   Applications e and f: decimal to binary converter and undo/redo.

    // AI Assistance:
    // ChatGPT (OpenAI) was used to help understand stack logic,
    // develop portions of the postfix evaluation logic,
    // and assist with debugging and testing.
    // All implementation and testing responsibilities
    // are documented with their respective contributors.

    // PRE: report.txt can be created in the current folder.
    // POST: runs the starter test methods and writes their output to report.txt.
    public static void main(String[] args) {

        try{
            PrintWriter out = new PrintWriter("report.txt");
            Functions.testPalindrome(out);
            Functions.testPostFixExpression(out);
            Functions.testPreFixExpression(out);
            Functions.testIsBalanced(out);
            Functions.testDecToBinary(out);
            Functions.testUndoRedo(out);
            out.close();
        }
        catch(IOException e){
            System.out.println("Error opening the file: " + e.getMessage());
        }
    }
}
