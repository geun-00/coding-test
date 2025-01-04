import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(n, 0, 0, arr));
    }

    private static int solve(int n, int x, int y, int[][] arr) {

        if (n == 1) {
            return arr[x][y];
        }

        int[] temp = new int[4];

        int half = n / 2;

        temp[0] = solve(half, x, y, arr);
        temp[1] = solve(half, x, y + half, arr);
        temp[2] = solve(half, x + half, y, arr);
        temp[3] = solve(half, x + half, y + half, arr);

        Arrays.sort(temp);

        return temp[1];
    }
}