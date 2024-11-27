import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int z = (int) ((y * 100.0) / x);

        int s = 0, e = 1_000_000_000;
        int ans = -1;

        while (s <= e) {

            int mid = (s + e) / 2;

            int tx = x + mid;
            int ty = y + mid;

            int tz = (int) ((ty * 100.0) / tx);

            if (tz != z) {
                ans = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        System.out.println(ans);
    }
}