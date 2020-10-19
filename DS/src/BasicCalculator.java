import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class BasicCalculator {

  private static final Set<String> OPERATORS = new HashSet<>(Arrays.asList("-", "+", "*", "/"));
  private static final Map<String, Integer> PRECEDENCE = new HashMap<String, Integer>() {
    {
      put("/", 3);
      put("*", 3);
      put("+", 2);
      put("-", 2);
      put("(", 1);
    }
  };
  private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS = new HashMap<String, BiFunction<Integer, Integer, Integer>>() {
    {
      put("/", (x, y) -> x / y);
      put("*", (x, y) -> x * y);
      put("+", Integer::sum);
      put("-", (x, y) -> x - y);
    }
  };

  private static int calculator(String exp) {
    String postfix = infixToPostfix(exp);
    return rpn(Arrays.stream(postfix.split(" ")).collect(Collectors.toList()));
  }


  private static int rpn(List<String> tokens) {
    Stack<Integer> running = new Stack<>();
    for (String t : tokens) {
      if (OPERATORS.contains(t)) {
        int two = running.pop();
        int one = running.pop();
        running.add(OPERATIONS.get(t).apply(one, two));
      } else {
        running.add(Integer.valueOf(t));
      }
    }
    return running.get(0);
  }

  private static String infixToPostfix(String exp) {
    List<String> output = new ArrayList<>();
    Stack<String> opStack = new Stack<>();
    List<String> tokens = tokenize(exp);
    for (String token : tokens) {
      if (token.equals("(")) {
        opStack.add(token);
      } else if (token.equals(")")) {
        while (!opStack.peek().equals("(")) {
          output.add(opStack.pop());
        }
        opStack.pop();
      } else if (OPERATORS.contains(token)) {
        while (!opStack.isEmpty() && PRECEDENCE.get(token) <= PRECEDENCE.get(opStack.peek())) {
          output.add(opStack.pop());
        }
        opStack.push(token);
      } else {
        output.add(token);
      }
    }
    while (!opStack.isEmpty()) {
      output.add(opStack.pop());
    }
    return String.join(" ", output);
  }

  private static List<String> tokenize(String input) {
    List<String> tokens = new ArrayList<>();
    StringBuilder number = new StringBuilder();
    int i = 0;
    while (i < input.length()) {
      char c = input.charAt(i++);
      if (OPERATORS.contains(String.valueOf(c)) || c == '(' || c == ')') {
        if (number.length() > 0) {
          tokens.add(number.toString());
          number.setLength(0);
        }
        tokens.add(String.valueOf(c));
      } else if (Character.isDigit(c)) {
        number.append(c);
      }
    }
    if (number.length() > 0) {
      tokens.add(number.toString());
    }
    return tokens;
  }

  public static void main(String[] args) {
    System.out.println(calculator("2-1+2"));
  }
}
