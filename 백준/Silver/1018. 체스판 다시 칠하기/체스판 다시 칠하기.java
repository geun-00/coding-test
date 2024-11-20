import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[][] arr;
    static int min = 64;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                solve(i, j);
            }
        }

        System.out.println(min);
    }

    private static void solve(int x, int y) {

        char prev = arr[x][y];

        int count = 0;

        for (int i = x; i < x + 8; i++) {
            for (int j = y; j < y + 8; j++) {

                if (arr[i][j] != prev) {
                    count++;
                }
                prev = (prev == 'W') ? 'B' : 'W';
            }
            prev = (prev == 'W') ? 'B' : 'W';
        }

        count = Math.min(count, 64 - count);
        min = Math.min(count, min);
    }
}