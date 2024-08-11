import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static String[][] ladder;
    static int n, m, h;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladder = new String[h + 1][n + 1];
        for (int i = 1; i <= h; i++) {
            Arrays.fill(ladder[i], "|");
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladder[a][b] = "|-";
        }

        //0~3개 가로선 추가
        for (int i = 0; i <= 3; i++) {
            solve(i, 0, 1);
        }

        System.out.println(-1);
    }

    private static void solve(int target, int depth, int row) {
        if (depth == target) {
            if (check()) {
                System.out.println(target);
                System.exit(0);
            }
            return;
        }

        for (int i = row; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (ladder[i][j].equals("|") &&
                    ladder[i][j - 1].equals("|") &&
                    ladder[i][j + 1].equals("|")) {

                    ladder[i][j] = "|-";
                    solve(target, depth + 1, i);
                    ladder[i][j] = "|";
                }
            }
        }
    }

    private static boolean check() {

        for (int i = 1; i <= n; i++) {
            int now = i;
            for (int j = 1; j <= h; j++) {
                if (ladder[j][now].equals("|-")) {
                    now++;
                } else if (ladder[j][now - 1].equals("|-")) {
                    now--;
                }
            }
            if (now != i) {
                return false;
            }
        }

        return true;
    }
}
