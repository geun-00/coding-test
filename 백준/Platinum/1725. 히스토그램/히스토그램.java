import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] heights = new long[n];

        for (int i = 0; i < n; i++) {
            heights[i] = Long.parseLong(br.readLine());
        }

        Deque<long[]> stack = new ArrayDeque<>();

        long max = Long.MIN_VALUE;

        //{value, index}
        stack.push(new long[]{-1, -1});

        for (int i = 0; i < n; i++) {

            if (stack.peek()[0] <= heights[i]) {
                stack.push(new long[]{heights[i], i});
            } else {

                long temp = i;

                while (stack.peek()[0] > heights[i]) {

                    long h = stack.peek()[0];
                    long w = i - stack.peek()[1];

                    max = Math.max(max, h * w);
                    temp = stack.pop()[1];
                }

                stack.push(new long[]{heights[i], temp});
            }
        }

        while (!stack.isEmpty()) {

            long h = stack.peek()[0];
            long w = n - stack.peek()[1];

            max = Math.max(max, h * w);
            stack.pop();
        }

        System.out.println(max);
    }
}