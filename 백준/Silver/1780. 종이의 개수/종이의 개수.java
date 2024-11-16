import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int mone = 0;
    static int zero = 0;
    static int one = 0;

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
        System.out.println(mone);
        System.out.println(zero);
        System.out.println(one);

    }

    private static void solve(int x, int y, int n) {

        if (n == 1) {
            if (arr[x][y] == -1) mone++;
            else if (arr[x][y] == 0) zero++;
            else one++;
            return;
        }

        int t1 = 0, t2 = 0, t3 = 0;

        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (arr[i][j] == -1) {
                    t1++;
                } else if (arr[i][j] == 0) {
                    t2++;
                } else {
                    t3++;
                }
            }
        }

        if (t1 == n * n) {
            mone++;
            return;
        }
        if (t2 == n * n) {
            zero++;
            return;
        }
        if (t3 == n * n) {
            one++;
            return;
        }

        int size = n / 3;

        for (int i = x; i < x + n; i += size) {
            for (int j = y; j < y + n; j += size) {
                solve(i, j, size);
            }
        }
    }
}