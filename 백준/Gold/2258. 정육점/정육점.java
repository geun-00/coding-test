import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); //무게
            arr[i][1] = Integer.parseInt(st.nextToken()); //가격
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });

        int totalWeight = 0;
        int prevPrice = -1;
        int cost = 0;
        int ans = -1;

        for (int[] a : arr) {
            int w = a[0];
            int p = a[1];

            if (prevPrice == p) {
                cost += p;
            } else {
                cost = p;
            }
            prevPrice = p;
            totalWeight += w;

            if (totalWeight >= m) {
                if (ans == -1 || cost < ans) {
                    ans = cost;
                }
            }
        }

        System.out.println(ans);
    }
}