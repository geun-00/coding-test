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

        char[][] arr = new char[n][2 * n - 1];

        for (char[] row : arr) {
            Arrays.fill(row, ' ');
        }

        solve(arr, n, 0, n - 1);

        StringBuilder sb = new StringBuilder();
        for (char[] row : arr) {
            sb.append(row).append("\n");
        }

        System.out.print(sb);
    }

    private static void solve(char[][] arr, int size, int x, int y) {
        if (size == 3) {

            arr[x][y] = '*';
            arr[x + 1][y - 1] = '*';
            arr[x + 1][y + 1] = '*';
            arr[x + 2][y - 2] = '*';
            arr[x + 2][y - 1] = '*';
            arr[x + 2][y] = '*';
            arr[x + 2][y + 1] = '*';
            arr[x + 2][y + 2] = '*';

            return;
        }

        int nextSize = size / 2;

        solve(arr, nextSize, x, y);
        solve(arr, nextSize, x + nextSize, y - nextSize);
        solve(arr, nextSize, x + nextSize, y + nextSize);
    }
}