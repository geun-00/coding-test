import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        Deque<Integer> stk = new ArrayDeque<>();
        boolean first = true;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stk.push(-1);
            } else if (Character.isDigit(c)) {
                stk.push(c - '0');
            } else {
                int len = 0;
                while (!stk.isEmpty() && stk.peek() != -1) {
                    if (stk.peek() == -2) {
                        stk.pop();
                        len += stk.pop();
                    } else {
                        stk.pop();
                        len++;
                    }
                }
                stk.pop();
                stk.push(len * stk.pop());
                stk.push(-2);
            }
        }

        int ans = 0;
        while (!stk.isEmpty() && stk.peek() != -1) {
            if (stk.peek() == -2) {
                stk.pop();
                ans += stk.pop();
            } else {
                stk.pop();
                ans++;
            }
        }
        System.out.println(ans);
    }
}