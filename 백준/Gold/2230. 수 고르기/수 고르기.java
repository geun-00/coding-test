import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);

        int s = 0, e = 0;
        long ans = Long.MAX_VALUE;

        while (e < n) {
            
            long diff = arr[e] - arr[s];

            if (diff >= m) {
                ans = Math.min(ans, diff);
                s++;
            } else {
                e++;
            }
            if (s >= n || e >= n) break;
        }

        System.out.println(ans);
    }
}