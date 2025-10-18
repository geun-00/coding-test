import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            long bit = 0;

            for (int j = 0; j < k; j++) {
                int t = Integer.parseInt(st.nextToken());

                bit |= (1L << t);
            }

            arr[i] = bit;
        }

        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());

            long bit = 0;
            for (int j = 0; j < p; j++) {
                int q = Integer.parseInt(st.nextToken());

                bit |= (1L << q);
            }

            int ans = 0;
            for (long o : arr) {
                if ((bit & o) == o) {
                    ans++;
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}