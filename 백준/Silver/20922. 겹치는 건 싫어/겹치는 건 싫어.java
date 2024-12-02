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
        int[] num_arr = new int[100_001];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        int ans = 1;

        while (right < n) {

            num_arr[arr[right]]++;

            while (num_arr[arr[right]] > k) {
                num_arr[arr[left]]--;
                left++;
            }

            ans = Math.max(ans, right - left + 1);
            right++;
        }

        System.out.println(ans);
    }
}