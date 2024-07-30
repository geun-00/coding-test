import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        Deque<Character> stack = new ArrayDeque<>();

        int result = 0;
        int temp = 1;

        for (int i = 0; i < s.length(); i++) {

            switch (s.charAt(i)) {
                case '(':
                    stack.push('(');
                    temp *= 2;
                    break;
                case '[':
                    stack.push('[');
                    temp *= 3;
                    break;
                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') {
                        System.out.println(0);
                        return;
                    }
                    if (s.charAt(i - 1) == '(') {
                        result += temp;
                    }
                    stack.pop();
                    temp /= 2;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') {
                        System.out.println(0);
                        return;
                    }
                    if (s.charAt(i - 1) == '[') {
                        result += temp;
                    }
                    stack.pop();
                    temp /= 3;
                    break;
            }
        }

        System.out.println(!stack.isEmpty() ? 0 : result);
    }
}
