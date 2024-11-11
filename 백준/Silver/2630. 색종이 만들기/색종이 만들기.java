import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int white = 0, blue = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, n);

        System.out.println(white);
        System.out.println(blue);
    }

    private static void solve(int x, int y, int n) {

        if (n == 1) {
            int c = arr[x][y];

            if (c == 0) white++;
            if (c == 1) blue++;

            return;
        }

        int w = 0, b = 0;

        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (arr[i][j] == 0) {
                    w++;
                } else {
                    b++;
                }
            }
        }

        if (w == 0 || b == 0) {
            if (w == 0) blue++;
            if (b == 0) white++;
            return;
        }

        int size = n / 2;

        for (int i = x; i < x + n; i += size) {
            for (int j = y; j < y + n; j += size) {
                solve(i, j, size);
            }
        }
    }
}