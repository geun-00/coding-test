import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        iter:
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            Deque<Character> stk = new ArrayDeque<>();

            for (char c : s.toCharArray()) {
                if (c == '(') {
                    stk.push(c);
                } else {
                    if (stk.isEmpty()) {
                        sb.append("NO").append("\n");
                        continue iter;
                    }
                    stk.pop();
                }
            }

            sb.append(stk.isEmpty() ? "YES" : "NO").append("\n");
        }
        System.out.print(sb);
    }
}