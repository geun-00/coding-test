import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static int[][] objectsIndex;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        objectsIndex = new int[n][m];
        map = new char[n][m];

        int sx = 0, sy = 0;
        int object = 0;

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (map[i][j] == 'X') {
                    objectsIndex[i][j] = object++;
                }
            }
        }

        System.out.println(bfs(sx, sy, object));
    }

    private static int bfs(int sx, int sy, int object) {
        boolean[][][] visit = new boolean[n][m][1 << object];
        visit[sx][sy][0] = true;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{sx, sy, 0, 0});

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int x = now[0];
            int y = now[1];
            int gotObjects = now[2];
            int time = now[3];

            if (map[x][y] == 'E') {
                if (gotObjects == (1 << object) - 1) {
                    return time;
                }
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (map[nx][ny] == '#') continue;

                int next = gotObjects;

                if (map[nx][ny] == 'X') {
                    int objIndex = objectsIndex[nx][ny];
                    next |= (1 << objIndex);
                }

                if (visit[nx][ny][next]) continue;

                visit[nx][ny][next] = true;
                qu.offer(new int[]{nx, ny, next, time + 1});
            }
        }

        return 0;
    }
}