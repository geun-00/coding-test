import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(input[0]);
            arr[i][1] = Integer.parseInt(input[1]);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });

        int[] calendar = new int[365 + 1];
        for (int[] row : arr) {
            for (int i = row[0]; i <= row[1]; i++) {
                calendar[i]++;
            }
        }

        int ans = 0;
        int width = 0;
        int height = 0;

        for (int i = 1; i <= 365; i++) {
            if (calendar[i] > 0) {
                height = Math.max(height, calendar[i]);
                width++;
            } else {
                ans += (width * height);
                width = height = 0;
            }
        }
        
        ans += (width * height);

        System.out.println(ans);
    }
}