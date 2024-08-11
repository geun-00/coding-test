import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int s = 1;
        int e = k;
        int result = 0;

        while (s <= e) {

            int mid = (s + e) / 2;

            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += Math.min(mid / i, n);
            }

            if (sum < k) {
                s = mid + 1;
            } else {
                result = mid;
                e = mid - 1;
            }
        }

        System.out.println(result);
    }
}
