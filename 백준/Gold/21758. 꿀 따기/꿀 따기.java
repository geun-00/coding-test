import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];
        long[] sum = new long[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            sum[i] = arr[i] + sum[i - 1];
        }

        long max = Long.MIN_VALUE;

        //벌통이 가운데에 있고, 벌이 양 끝에 있는 경우
        for (int i = 2; i < n; i++) {
            long temp = (sum[i] - sum[1]) + (sum[n - 1] - sum[i - 1]);
            max = Math.max(max, temp);
        }

        //벌통이 첫번째에 있는 경우(왼쪽 끝)
        for (int i = 2; i < n; i++) {
            long temp = sum[i - 1] + (sum[n - 1] - arr[i]);
            max = Math.max(max, temp);
        }

        //벌통이 마지막에 있는 경우(오른쪽 끝)
        for (int i = 2; i < n; i++) {
            long temp = (sum[n] - sum[i]) + (sum[n] - sum[1] - arr[i]);
            max = Math.max(max, temp);
        }

        System.out.println(max);
    }
}
