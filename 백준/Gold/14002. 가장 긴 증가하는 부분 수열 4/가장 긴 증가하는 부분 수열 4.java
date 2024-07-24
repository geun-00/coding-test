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

        int[] arr = new int[n];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        int max = 1;
        int maxIdx = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] + 1 == dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            if (max < dp[i]) {
                max = dp[i];
                maxIdx = i;
            }
        }

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(arr[maxIdx]);

        int idx = max - 1;
        for (int i = maxIdx - 1; i >= 0; i--) {
            if (dp[i] == idx) {
                idx--;
                stack.push(arr[i]);
            }
        }

        StringBuilder result = new StringBuilder();

        result.append(max).append("\n");

        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }

        System.out.print(result);
    }
}
