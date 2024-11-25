import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        long sum = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            sum += a;

            arr[i][0] = x;
            arr[i][1] = a;
        }

        long mid = (sum + 1) / 2;
        long count = 0;

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < n; i++) {
            count += arr[i][1];

            if (count >= mid) {
                System.out.println(arr[i][0]);
                return;
            }
        }
    }
}