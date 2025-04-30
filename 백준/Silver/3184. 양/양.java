import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int r, c;
    static int o, v;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine()
                       .toCharArray();

            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'o') o++;
                else if (map[i][j] == 'v') v++;
            }
        }

        visit = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visit[i][j] && (map[i][j] == 'o' || map[i][j] == 'v')) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(o + " " + v);
    }

    private static void bfs(int i, int j) {
        visit[i][j] = true;
        int sheep = 0, wolf = 0;

        if (map[i][j] == 'o') sheep++;
        else wolf++;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{i, j});

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if (visit[nx][ny] || map[nx][ny] == '#') continue;

                qu.offer(new int[]{nx, ny});
                visit[nx][ny] = true;

                if (map[nx][ny] == 'o') sheep++;
                else if (map[nx][ny] == 'v') wolf++;
            }
        }

        if (sheep > wolf) v -= wolf;
        else o -= sheep;
    }
}