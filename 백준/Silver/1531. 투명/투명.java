import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[101][101];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    arr[x][y]++;
                }
            }
        }

        int ans = 0;
        for (int x = 1; x <= 100; x++) {
            for (int y = 1; y <= 100; y++) {
                if (arr[x][y] > m) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}