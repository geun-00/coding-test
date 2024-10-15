import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp1 = new int[n + 1];
        int[] dp2 = new int[n + 1];

        dp1[1] = arr[1];

        int result = arr[1];

        for (int i = 2; i <= n; i++) {
            dp1[i] = Math.max(dp1[i - 1] + arr[i], arr[i]);
            dp2[i] = Math.max(dp2[i - 1] + arr[i], dp1[i - 1]);
            result = Math.max(result, Math.max(dp1[i], dp2[i]));
        }

        System.out.println(result);
    }
}
