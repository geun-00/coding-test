import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] prefix = new int[n + 1];
        int total = 0;
        for (int i = 0; i < n; i++) {
            int dist = Integer.parseInt(br.readLine());
            prefix[i + 1] = prefix[i] + dist;
            total += dist;
        }

        int ans = 0;
        int left = 1, right = 1;

        while (right <= n) {
            int clock = prefix[right] - prefix[left - 1];
            ans = Math.max(ans, Math.min(clock, total - clock));

            if (clock > total / 2) {
                left++;
            } else {
                right++;
            }
        }

        System.out.println(ans);
    }
}