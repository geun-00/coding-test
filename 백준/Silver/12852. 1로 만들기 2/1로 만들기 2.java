import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        int[] parent = new int[n + 1];

        for (int i = 2; i <= n; i++) {

            dp[i] = dp[i - 1] + 1;
            parent[i] = i - 1;

            if (i % 2 == 0) {
                if (dp[i] > dp[i / 2] + 1) {
                    dp[i] = dp[i / 2] + 1;
                    parent[i] = i / 2;
                }
            }

            if (i % 3 == 0) {
                if (dp[i] > dp[i / 3] + 1) {
                    dp[i] = dp[i / 3] + 1;
                    parent[i] = i / 3;
                }
            }
        }

        System.out.println(dp[n]);

        ArrayList<Integer> list = new ArrayList<>();
        int now = n;

        while (now != 1) {
            list.add(now);
            now = parent[now];
        }
        list.add(1);

        StringBuilder sb = new StringBuilder();

        for (int num : list) {
            sb.append(num).append(" ");
        }

        System.out.print(sb);
    }
}