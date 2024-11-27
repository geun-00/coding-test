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

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = -1;
        int e = 0;

        for (int i : arr) {
            s = Math.max(s, i);
            e += i;
        }

        while (s <= e) {

            int mid = (s + e) / 2;

            int sum = 0;
            int count = 0;

            for (int i = 0; i < n; i++) {
                if (sum + arr[i] > mid) {
                    count++;
                    sum = 0;
                }
                sum += arr[i];
            }

            if (sum > 0) {
                count++;
            }

            if (count > m) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        System.out.println(s);
    }
}