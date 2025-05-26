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

        if (n == 1 || m == 1) {
            ans = 1;
        } else if (n == 2) {
            ans = Math.min(4, (m + 1) / 2);
        } else {
            if (m < 7) {
                ans = Math.min(4, m);
            } else {
                ans = 5 + m - 7;
            }
        }

        System.out.println(ans);
    }
}