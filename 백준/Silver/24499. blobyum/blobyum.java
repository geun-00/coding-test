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

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int window = 0;
        int ans = 0;

        for (int i = 0; i < k; i++) {
            window += arr[i];
        }

        ans = window;
        for (int i = 0; i < n; i++) {
            window -= arr[i];
            window += arr[(i + k) % n];

            ans = Math.max(ans, window);
        }

        System.out.println(ans);
    }
}