import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Deque<Integer> stack = new ArrayDeque<>();

        int[] heights = new int[n];

        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        long ans = 0;

        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                int last = stack.pop();
                ans += i - last - 1;
            }

            stack.push(i);
        }

        if (!stack.isEmpty()) {
            int last = stack.pop();

            while (!stack.isEmpty()) {
                ans += last - stack.pop();
            }
        }
        System.out.println(ans);
    }
}