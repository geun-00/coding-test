import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int r, c;
    static char[][] map;
    static boolean[][] visit;
    static int sheep, wolves;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'v') {
                    wolves++;
                } else if (map[i][j] == 'k') {
                    sheep++;
                }
            }
        }

        visit = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visit[i][j] && (map[i][j] == 'v' || map[i][j] == 'k')) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(sheep + " " + wolves);
    }

    private static void bfs(int i, int j) {
        visit[i][j] = true;
        int s = (map[i][j] == 'k') ? 1 : 0;
        int w = (map[i][j] == 'v') ? 1 : 0;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{i, j});

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if (visit[nx][ny] || map[nx][ny] == '#') continue;

                visit[nx][ny] = true;
                qu.offer(new int[]{nx, ny});

                if (map[nx][ny] == 'v') w++;
                else if (map[nx][ny] == 'k') s++;
            }
        }

        if (s > w) wolves -= w;
        else sheep -= s;
    }
}