import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[1_000_001];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            arr[x] = g;
        }

        int sum = 0;
        for (int i = 0; i < Math.min(arr.length, 2 * k + 1); i++) {
            sum += arr[i];
        }

        int ans = sum;
        for (int left = 0, right = 2 * k + 1; right < arr.length; left++, right++) {
            sum -= arr[left];
            sum += arr[right];

            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }
}
