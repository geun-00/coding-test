import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        int num = 1;
        int x = n / 2;
        int y = n / 2;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int[][] ans = new int[n][n];

        ans[x][y] = num;

        int dir = 0;
        int cycle = 1;
        int count = 0;

        int tx = x + 1, ty = y + 1;

        while (x >= 0 && x < n && y >= 0 && y < n) {

            for (int i = 0; i < cycle; i++) {
                num++;
                x = x + dx[dir];
                y = y + dy[dir];

                if (x < 0 || x >= n || y < 0 || y >= n) {
                    break;
                }

                ans[x][y] = num;
                if (ans[x][y] == target) {
                    tx = x + 1;
                    ty = y + 1;
                }
            }

            dir = (dir + 1) % 4;

            count++;
            if (count == 2) {
                count = 0;
                cycle++;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }

        sb.append(tx).append(" ").append(ty);

        System.out.print(sb);
    }
}