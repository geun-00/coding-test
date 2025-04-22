import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n * m][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                arr[i * m + j][0] = i;
                arr[i * m + j][1] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));

        int[] count = new int[n];
        int classes = 0;
        int ans = Integer.MAX_VALUE;

        for (int left = 0, right = 0; right < n * m; right++) {
            int index = arr[right][0];
            count[index]++;

            if (count[index] == 1) classes++;

            while (classes == n) {
                int diff = arr[right][1] - arr[left][1];
                ans = Math.min(ans, diff);

                count[arr[left][0]]--;
                if (count[arr[left][0]] == 0) classes--;

                left++;
            }
        }

        System.out.println(ans);
    }
}