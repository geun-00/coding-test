import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp1[i] = dp2[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            if (arr[i] >= arr[i - 1]) {
                dp1[i] = dp1[i - 1] + 1;
            }

            if (arr[i] <= arr[i - 1]) {
                dp2[i] = dp2[i - 1] + 1;
            }
        }

        int max = 1;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, Math.max(dp1[i], dp2[i]));
        }

        System.out.println(max);
    }
}