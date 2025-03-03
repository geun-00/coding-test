import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long sum = 0;
        for (int i = 0; i < m; i++) {
            sum += arr[i];
        }

        long ans = sum;
        for (int i = m; i < n; i++) {
            sum += arr[i];
            sum -= arr[i - m];
            ans = Math.max(ans, sum);
        }
        System.out.println(ans);
    }
}