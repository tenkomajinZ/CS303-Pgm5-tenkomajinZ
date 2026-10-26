
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintWriter;

public class Functions {

    public static boolean isDigits(String inString){
        if (inString == null || inString.isEmpty()) 
            return false;
        for (char c : inString.toCharArray()) {
            if (!Character.isDigit(c)) 
               return false;
        }
        return true;
    }

    // DONE BY CHRIS HERNANDEZ
    public static void testPalindrome(PrintWriter out){
        System.out.println("\nSTACK 1: PALINDROME");
        out.println("\nSTACK 1: PALINDROME");
        
        MyStack<Character> stack = new MyStack<>();
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList("tacocat", "bubba","a", "mom","abba", "abccba", "abcba" ));

        // PRE: wordList contains the words to evaluate
        // POST: Prints whether each word is a palindrome
        for (String word : wordList) {

            // Empty the stack before checking another word
            stack.clear();

            // Push all characters onto the stack
            for (int i = 0; i < word.length(); i++) {
                stack.push(word.charAt(i));
            }

            // Compare the original word to the reversed order
            boolean isPalindrome = true;

            for (int i = 0; i < word.length(); i++) {
                char reversedChar = stack.pop();

                if (word.charAt(i) != reversedChar) {
                    isPalindrome = false;
                    break;
                }
            }

            // Print results to console and report file
            String result = word + " is a palindrome: " + isPalindrome;

            System.out.println(result);
            out.println(result);
        }
    }

    // DONE BY CHRIS HERNANDEZ
    public static void testPostFixExpression(PrintWriter out) {
        System.out.println("\nSTACK 2: POSTFIX EVALUATOR");
        out.println("\nSTACK 2: POSTFIX EVALUATOR");       

        ArrayList<String> expList = new ArrayList<>(Arrays.asList(
            "5 7 * 15 + 25 *", 
            "5 0 / 4 +", 
            "15 2 * 3 / 15 +", 
            "3 5 7 + *", 
            "3 5 6"));

        // PRE: expList contains postfix expressions to evaluate
        // POST: Evaluates each expression using a stack and prints the result or an invalid expression message
        for (String expression : expList) {

            MyStack<Integer> stack = new MyStack<>();
            boolean valid = true;

            String[] tokens = expression.trim().split("\\s+");

            // Process each token in the expression
            for (String token : tokens) {

                // If token is a number, push it onto the stack
                if (isDigits(token)) {
                    stack.push(Integer.parseInt(token));
                }

                // AI helped me build the logic to use operators 
                // On the two popped operands and push the result back onto the stack

                // If token is an operator, calculate using 2 operands
                else if (token.equals("+") || token.equals("-") ||
                        token.equals("*") || token.equals("/")) {

                    // Ensure there are enough numbers for an operation
                    if (stack.size() < 2) {
                        valid = false;
                        break;
                    }

                    int right = stack.pop();
                    int left = stack.pop();
                    int result = 0;

                    switch (token) {
                        case "+":
                            result = left + right;
                            break;

                        case "-":
                            result = left - right;
                            break;

                        case "*":
                            result = left * right;
                            break;

                        case "/":
                            if (right == 0) {
                                valid = false;
                            } else {
                                result = left / right;
                            }
                            break;
                    }

                    if (!valid) {
                        break;
                    }

                    // Push calculation result onto stack
                    stack.push(result);
                }

                // Unexpected token
                else {
                    valid = false;
                    break;
                }
            }

            String output;

            // A valid expression must leave exactly one result
            if (!valid || stack.size() != 1) {
                output = "The expression: " +
                        expression + " is invalid.";
            } else {
                int answer = stack.pop();

                output = "The result of the postfix expression: " +
                        expression + " = " + answer;
            }

            System.out.println(output);
            out.println(output);
        }
    }

    // PRE: out is an open PrintWriter.
    // POST: writes each supplied prefix result or invalid message to console and report.
    public static void testPreFixExpression(PrintWriter out) {
        System.out.println("\nSTACK 3: PREFIX EVALUATOR");
        out.println("\nSTACK 3: PREFIX EVALUATOR");
      
        ArrayList<String> expList = new ArrayList<>(Arrays.asList(
            "+ * 7 5 + 6 7",
            "- + 7 * 4 5 + 2 0",
            "- / 18 / 6 3 2",
            "* 25 + 15 * 7 5", 
            "+ 4 / 0 5", 
            "+ 15 / 3 * 2 15", 
            "* + 7 5 3", 
            "6 5 3"));

        for (String expression : expList) {
            String result;
            try {
                result = "The result of the prefix expression: " + expression
                        + " = " + preFixExpression(expression);
            }
            catch (IllegalArgumentException e) {
                result = "The expression: " + expression + " is invalid.";
            }
            System.out.println(result);
            out.println(result);
        }

	}

    // PRE: out is an open PrintWriter.
    // POST: writes the supplied decimal conversions to console and report.
    public static void testDecToBinary(PrintWriter out) {
        System.out.println("\nSTACK 5: DECIMAL TO BINARY EVALUATOR");
        out.println("\nSTACK 5: DECIMAL TO BINARY EVALUATOR");

        ArrayList<Integer> binary = new ArrayList<>(Arrays.asList(15, 81, 105, 255, 256, 0, 1 ));
        for (int number : binary) {
            String result = "The decimal value " + number + " is "
                    + decToBinary(number) + " in binary";
            System.out.println(result);
            out.println(result);
        }
 
    }

  
    // PRE: out is an open PrintWriter.
    // POST: writes whether each supplied expression is balanced to console and report.
    public static void testIsBalanced(PrintWriter out) {
        System.out.println("\nSTACK 4: PARENTHESIS IS BALANCED EVALUATOR");
        out.println("\nSTACK 4: PARENTHESIS IS BALANCED EVALUATOR");

        ArrayList<String> expList = new ArrayList<>(Arrays.asList(
            "[7 + (4 - 3)] - (3 * 4)",
            "[7 + (4 - 3] - (3 * 4)",
            "4 + 6 - 7", 
            "([x + y] + {z * w})",
            "{(1 + [2 * 3]) - 4}",
            "([x + y] + z}"));
        for (String expression : expList) {
            String result = "The expression " + expression;
            if (isBalanced(expression)) {
                result += " is balanced";
            }
            else {
                result += " is not balanced";
            }
            System.out.println(result);
            out.println(result);
        }
    }

    // Sources for c and d: CS303 Program 5 requirements and supplied test cases;
    // CS303Lecture9 MyDblLinkedList.java and CS303Lecture10 class examples.
    // PRE: expression contains whitespace-separated integer operands and +, -, *, /.
    //      Operands and intermediate results fit in int.
    // POST: returns the prefix value using integer division; throws
    //       IllegalArgumentException for invalid tokens, structure, or division by zero.
    public static int preFixExpression(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression is empty");
        }

        MyStack<Integer> numbers = new MyStack<>();
        String[] tokens = expression.trim().split("\\s+");

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (token.equals("+") || token.equals("-")
                    || token.equals("*") || token.equals("/")) {
                if (numbers.size() < 2) {
                    throw new IllegalArgumentException("Missing operand");
                }

                int left = numbers.pop();
                int right = numbers.pop();
                int result;
                if (token.equals("+")) {
                    result = left + right;
                }
                else if (token.equals("-")) {
                    result = left - right;
                }
                else if (token.equals("*")) {
                    result = left * right;
                }
                else {
                    if (right == 0) {
                        throw new IllegalArgumentException("Cannot divide by zero");
                    }
                    result = left / right;
                }
                numbers.push(result);
            }
            else {
                numbers.push(Integer.parseInt(token));
            }
        }

        if (numbers.size() != 1) {
            throw new IllegalArgumentException("Extra operands");
        }
        return numbers.pop();
    }

    // PRE: expression is non-null.
    // POST: returns true when (), [], and {} are correctly paired and nested;
    //       ignores other characters and treats an empty expression as balanced.
    public static boolean isBalanced(String expression) {
        MyStack<Character> brackets = new MyStack<>();

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (current == '(' || current == '[' || current == '{') {
                brackets.push(current);
            }
            else if (current == ')' || current == ']' || current == '}') {
                if (brackets.isEmpty()) {
                    return false;
                }
                char opening = brackets.pop();
                if ((current == ')' && opening != '(')
                        || (current == ']' && opening != '[')
                        || (current == '}' && opening != '{')) {
                    return false;
                }
            }
        }

        return brackets.isEmpty();
    }

    // PRE: out is an open PrintWriter.
    // POST: writes the supplied undo/redo results to console and report.
    public static void testUndoRedo(PrintWriter out) {
        System.out.println("\nSTACK 6: UNDO REDO FUNCTION EVALUATOR");
        out.println("\nSTACK 6: UNDO REDO FUNCTION EVALUATOR");

        ArrayList<String> expList = new ArrayList<>(Arrays.asList(
            "D add a", "D add b", "D add c", "R", "U","U","R","U","D add d", "U", "U", "U", "R", "F", "Q"));
        MyStack<String> undoStack = new MyStack<>();
        MyStack<String> redoStack = new MyStack<>();

        for (String command : expList) {
            String result = undoRedo(command, undoStack, redoStack);
            System.out.println(result);
            out.println(result);
            if (command.trim().equals("Q")) {
                break;
            }
        }

    }

    // PRE: number is a nonnegative integer.
    // POST: returns its binary digits; rejects negative input.
    public static String decToBinary(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Decimal value must be nonnegative");
        }
        if (number == 0) {
            return "0";
        }

        MyStack<Integer> remainders = new MyStack<>();
        while (number > 0) {
            remainders.push(number % 2);
            number = number / 2;
        }

        // The stack reverses the remainders into the correct digit order.
        String result = "";
        while (!remainders.isEmpty()) {
            result += remainders.pop();
        }
        return result;
    }

    // PRE: input is non-null; undoStack and redoStack are separate stacks.
    // POST: updates command history and returns the matching report message.
    //       D clears redo history; U and R move the most recent action.
    //       Q returns the ending message so the caller can stop processing.
    public static String undoRedo(String input, MyStack<String> undoStack,
                                  MyStack<String> redoStack) {
        String command = input.trim();
        if (command.startsWith("D ") && !command.substring(2).trim().isEmpty()) {
            String action = command.substring(2).trim();
            undoStack.push(action);
            redoStack.clear();
            return "performing: " + action;
        }
        else if (command.equals("U")) {
            if (undoStack.isEmpty()) {
                return "undo: stack is empty";
            }
            String action = undoStack.pop();
            redoStack.push(action);
            return "undoing: command: " + action;
        }
        else if (command.equals("R")) {
            if (redoStack.isEmpty()) {
                return "redo: stack is empty";
            }
            String action = redoStack.pop();
            undoStack.push(action);
            return "redoing: command: " + action;
        }
        else if (command.equals("Q")) {
            return "program ending";
        }
        else {
            return "invalid command entered: " + input;
        }
    }
}
