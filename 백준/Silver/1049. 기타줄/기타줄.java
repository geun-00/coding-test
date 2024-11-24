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

        int min1 = 1001, min2 = 1001;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            min1 = Math.min(min1, Integer.parseInt(st.nextToken()));
            min2 = Math.min(min2, Integer.parseInt(st.nextToken()));
        }

        int i = min1 * (((n - 1) / 6) + 1);
        int j = min2 * n;
        int k = min1 * (n / 6) + min2 * (n % 6);

        System.out.println(Math.min(i, Math.min(j, k)));
    }
}