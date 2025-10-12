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

        int ans = 0;
        int sum = 0;

        if (n > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int w = Integer.parseInt(st.nextToken());

                sum += w;
                if (sum > m) {
                    sum = w;
                    ans++;
                }
            }

            if (sum > 0) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}