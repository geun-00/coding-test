import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;

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

        System.out.println(solve(0, 0, n));
    }

    private static int solve(int x, int y, int size) {
        if (size == 2) {
            int[] temp = new int[4];
            temp[0] = arr[x][y];
            temp[1] = arr[x][y + 1];
            temp[2] = arr[x + 1][y];
            temp[3] = arr[x + 1][y + 1];

            Arrays.sort(temp);
            return temp[2];
        }

        int[] temp = new int[4];
        int half = size / 2;
        temp[0] = solve(x, y, half);
        temp[1] = solve(x, y + half, half);
        temp[2] = solve(x + half, y, half);
        temp[3] = solve(x + half, y + half, half);

        Arrays.sort(temp);

        return temp[2];
    }
}
