import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        boolean[][] canMove = new boolean[n][n];
        canMove[0][0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!canMove[i][j] || (i == n - 1 && j == n - 1)) {
                    continue;
                }

                int right = j + arr[i][j];
                if (right < n && !canMove[i][right]) {
                    canMove[i][right] = true;
                }

                int down = i + arr[i][j];
                if (down < n && !canMove[down][j]) {
                    canMove[down][j] = true;
                }
            }
        }

        System.out.println(canMove[n - 1][n - 1] ? "HaruHaru" : "Hing");
    }
}