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

        int[] score = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        int low = 0;
        int high = 2_000_000;
        int ans = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            int group = 0;
            int sum = 0;

            for (int s : score) {
                sum += s;
                if (sum >= mid) {
                    sum = 0;
                    group++;
                }
            }

            if (group >= k) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(ans);
    }
}