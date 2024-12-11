import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        char[][] grid = new char[r][c];
        int[][] timer = new int[r][c];

        for (int i = 0; i < r; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        int curTime = 1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (curTime < n) {
            curTime++;

            if (curTime % 2 == 0) {
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (grid[i][j] == '.') {
                            grid[i][j] = 'O';
                            timer[i][j] = curTime;
                        }
                    }
                }
            } else {
                boolean[][] mark = new boolean[r][c];

                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (grid[i][j] == 'O' && curTime - timer[i][j] == 3) {

                            mark[i][j] = true;

                            for (int d = 0; d < 4; d++) {
                                int nr = i + dx[d];
                                int nc = j + dy[d];

                                if (nr < 0 || nc < 0 || nr >= r || nc >= c) continue;

                                mark[nr][nc] = true;
                            }
                        }
                    }
                }

                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (mark[i][j]) {
                            grid[i][j] = '.';
                            timer[i][j] = 0;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            sb.append(row).append("\n");
        }

        System.out.print(sb);
    }
}