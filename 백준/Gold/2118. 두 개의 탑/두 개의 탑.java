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
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int clock = prefix[j - 1] - prefix[i - 1];
                ans = Math.max(ans, Math.min(clock, total - clock));
            }
        }

        System.out.println(ans);
    }
}