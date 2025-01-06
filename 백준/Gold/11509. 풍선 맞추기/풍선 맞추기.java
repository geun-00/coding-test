import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] arrows = new int[1_000_001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (arrows[arr[i]] > 0) {
                arrows[arr[i]]--;
            } else {
                ans++;
            }

            while (i < n - 1 && arr[i] - 1 == arr[i + 1]) {
                i++;
            }

            arrows[arr[i] - 1]++;
        }

        System.out.println(ans);
    }
}
