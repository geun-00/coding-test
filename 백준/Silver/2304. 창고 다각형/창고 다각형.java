import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[1001];

        int n = Integer.parseInt(br.readLine());

        int max = 0;
        int maxIdx = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            arr[L] = H;

            if (max < arr[L]) {
                max = arr[L];
                maxIdx = L;
            }
        }

        int ans = 0;
        max = 0;

        for (int i = 0; i <= maxIdx; i++) {
            max = Math.max(max, arr[i]);

            ans += max;
        }

        max = 0;

        for (int i = 1000; i > maxIdx; i--) {
            max = Math.max(max, arr[i]);
            ans += max;
        }

        System.out.println(ans);
    }
}