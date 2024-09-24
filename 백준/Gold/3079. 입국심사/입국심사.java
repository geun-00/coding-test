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

        int[] times = new int[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(br.readLine());
            times[i] = t;
            max = Math.max(max, t);
        }

        long s = 1;
        long e = (long) max * m;

        while (s <= e) {
            long mid = (s + e) / 2;

            long sum = 0;
            for (int time : times) {
                sum += mid / time;
                if (sum >= m) {
                    break;
                }
            }

            if (sum >= m) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        System.out.println(s);
    }
}
