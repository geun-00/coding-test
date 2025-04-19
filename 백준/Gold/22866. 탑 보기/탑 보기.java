import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] top = new int[n + 1];
        int[] ans = new int[n + 1];
        int[] counting = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            top[i] = Integer.parseInt(st.nextToken());
            ans[i] = -1;
        }

        Deque<Integer> right = new ArrayDeque<>();
        for (int i = n; i > 0; i--) {
            while (!right.isEmpty() && top[right.peek()] <= top[i]) {
                right.pop();
            }
            counting[i] += right.size();
            if (!right.isEmpty()) {
                ans[i] = right.peek();
            }

            right.push(i);
        }

        Deque<Integer> left = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            while (!left.isEmpty() && top[left.peek()] <= top[i]) {
                left.pop();
            }
            counting[i] += left.size();
            if (!left.isEmpty()) {
                if (ans[i] == -1 || Math.abs(i - left.peek()) < Math.abs(i - ans[i])) {
                    ans[i] = left.peek();
                } else if (Math.abs(i - left.peek()) == Math.abs(i - ans[i])) {
                    ans[i] = Math.min(ans[i], left.peek());
                }
            }

            left.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int count = counting[i];

            if (count == 0) sb.append(0);
            else sb.append(count).append(" ").append(ans[i]);

            sb.append("\n");
        }

        System.out.print(sb);
    }
}