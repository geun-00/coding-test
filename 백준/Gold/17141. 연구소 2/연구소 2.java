import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static List<int[]> virus = new ArrayList<>();
    static int[] selected;
    static int ans = -1;
    static int[][] lab;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        lab = new int[n][n];
        selected = new int[m];
        int empty = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());

                if (lab[i][j] == 2) {
                    virus.add(new int[]{i, j});
                    lab[i][j] = 0;
                }
                else if (lab[i][j] == 0) empty++;
            }
        }

        solve(0, empty, m, 0);
        System.out.println(ans);
    }

    private static void solve(int index, int empty, int m, int start) {
        if (index == m) {
            go(empty, copyLab(), m);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            selected[index] = i;
            solve(index + 1, empty, m, i + 1);
        }
    }

    private static void go(int empty, int[][] map, int m) {
        Queue<int[]> qu = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][n];

        for (int i : selected) {
            int[] pos = virus.get(i);
            int x = pos[0];
            int y = pos[1];
            qu.offer(new int[]{x, y});
            visit[x][y] = true;
            map[x][y] = 2;
        }

        int time = -1;
        empty += virus.size() - m;

        while (!qu.isEmpty()) {
            int size = qu.size();
            time++;

            for (int i = 0; i < size; i++) {
                int[] now = qu.poll();
                int x = now[0];
                int y = now[1];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if (visit[nx][ny] || map[nx][ny] != 0) continue;

                    map[nx][ny] = 2;
                    visit[nx][ny] = true;
                    empty--;
                    qu.offer(new int[]{nx, ny});
                }
            }
        }

        if (empty == 0) {
            if (ans == -1 || time < ans) {
                ans = time;
            }
        }
    }

    public static int[][] copyLab() {
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = lab[i][j];
            }
        }

        return map;
    }
}