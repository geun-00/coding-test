import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] sum = new int[n + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            sum[i + 1] = sum[i] + arr[i];
        }

        int max = sum[x];
        int now = max;

        for (int i = x; i < n; i++) {

            now -= arr[i - x];
            now += arr[i];

            max = Math.max(max, now);
        }

        if (max == 0) {
            System.out.println("SAD");
            return;
        }

        int count = 0;
        for (int i = x; i <= n; i++) {
            if (sum[i] - sum[i - x] == max) {
                count++;
            }
        }

        System.out.println(max);
        System.out.println(count);
    }
}