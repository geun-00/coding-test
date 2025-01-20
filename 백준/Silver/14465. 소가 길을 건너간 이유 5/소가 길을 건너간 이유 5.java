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
        int b = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < b; i++) {
            int num = Integer.parseInt(br.readLine()) - 1;
            arr[num] = 1;
        }

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        int ans = sum;

        for (int i = k; i < n; i++) {
            sum -= arr[i - k];
            sum += arr[i];

            ans = Math.min(ans, sum);
        }

        System.out.println(ans);
    }
}