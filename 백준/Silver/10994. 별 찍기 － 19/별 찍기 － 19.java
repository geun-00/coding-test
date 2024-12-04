import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static char[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int size = 4 * n - 3;

        arr = new char[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(arr[i], ' ');
        }

        solve(0, 0, n);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            sb.append(arr[i]).append("\n");
        }

        System.out.print(sb);
    }

    private static void solve(int x, int y, int n) {
        if (n == 1) {
            arr[x][y] = '*';
            return;
        }

        int size = 4 * n - 3;

        for (int i = 0; i < size; i++) {
            arr[x][y + i] = '*';
            arr[x + i][y] = '*';
            arr[x + size - 1][y + i] = '*';
            arr[x + i][y + size - 1] = '*';
        }

        solve(x + 2, y + 2, n - 1);
    }
}