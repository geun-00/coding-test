import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while (!(input = br.readLine()).equals(".")) {
            sb.append(solve(input)).append("\n");
        }
        System.out.print(sb);
    }

    private static String solve(String input) {
        Deque<Character> stk = new ArrayDeque<>();

        for (char c : input.toCharArray()) {
            if (c == '(' || c == '[') {
                stk.push(c);
            } else if (c == ')') {
                if (stk.isEmpty() || stk.peek() != '(') return "no";
                stk.pop();
            } else if (c == ']') {
                if (stk.isEmpty() || stk.peek() != '[') return "no";
                stk.pop();
            }
        }

        return stk.isEmpty() ? "yes" : "no";
    }
}
