import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] visit = new boolean[100_001];

        int left = 0, right = 0;
        long ans = 0;

        while (right < n) {

            if (!visit[arr[right]]) {
                visit[arr[right]] = true;
                ans += (right - left + 1);
                right++;
            } else {
                visit[arr[left]] = false;
                left++;
            }
        }

        System.out.println(ans);
    }
}