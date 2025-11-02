import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = 1;
        StringBuilder sb = new StringBuilder();

        while (true) {
            String s = br.readLine();

            if (s.contains("-")) break;

            sb.append(num).append(". ").append(solve(s)).append("\n");
            num++;
        }

        System.out.print(sb);
    }

    private static int solve(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '{') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        int open = 0, close = 0;
        for (char c : stack) {
            if (c == '{') {
                open++;
            } else {
                close++;
            }
        }

        int ans = 0;
        ans += (open / 2) + (open % 2);
        ans += (close / 2) + (close % 2);

        return ans;
    }
}