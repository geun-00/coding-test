import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static char[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        solve(0, 0, n);

        System.out.print(sb);
    }

    private static void solve(int x, int y, int n) {

        if (n == 1) {
            sb.append(arr[x][y]);
            return;
        }

        int zero = 0, one = 0;
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (arr[i][j] == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
        }

        if (zero == 0) {
            sb.append("1");
            return;
        }
        if (one == 0) {
            sb.append("0");
            return;
        }

        sb.append("(");

        int size = n / 2;
        for (int i = x; i < x + n; i += size) {
            for (int j = y; j < y + n; j += size) {
                solve(i, j, size);
            }
        }
        sb.append(")");
    }
}