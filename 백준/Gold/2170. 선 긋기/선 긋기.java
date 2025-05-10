import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[][] arr = new long[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Long.parseLong(st.nextToken());
            arr[i][1] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr, Comparator.comparingLong(a -> a[0]));

        long ans = Math.abs(arr[0][1] - arr[0][0]);
        long prev = arr[0][1];

        for (int i = 1; i < n; i++) {
            if (prev < arr[i][1]) {
                ans += Math.abs(arr[i][1] - arr[i][0]);

                if (arr[i][0] < prev) {
                    ans += arr[i][0] - prev;
                }

                prev = arr[i][1];
            }
        }

        System.out.println(ans);
    }
}