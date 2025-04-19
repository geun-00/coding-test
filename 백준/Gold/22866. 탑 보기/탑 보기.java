import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
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
            ans[i] = Integer.MAX_VALUE;
        }

        Deque<Integer> right = new ArrayDeque<>();
        for (int i = n; i > 0; i--) {
            solve(top, ans, counting, right, i);
        }

        Deque<Integer> left = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            solve(top, ans, counting, left, i);
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

    private static void solve(int[] top, int[] ans, int[] counting, Deque<Integer> stk, int i) {
        while (!stk.isEmpty() && top[stk.peek()] <= top[i]) {
            stk.pop();
        }
        counting[i] += stk.size();
        if (!stk.isEmpty()) {
            int dist = Math.abs(i - stk.peek());
            int origin = Math.abs(i - ans[i]);

            if (dist < origin || dist == origin && stk.peek() < ans[i]) {
                ans[i] = stk.peek();
            }
        }

        stk.push(i);
    }
}