import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-2, -2, 0, 0, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -1, 1};
    static int n;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        visit = new boolean[n][n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        int ans = bfs(r1, c1, r2, c2);
        System.out.println(ans);
    }

    private static int bfs(int r1, int c1, int r2, int c2) {
        visit[r1][c1] = true;
        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{r1, c1, 0});

        while (!qu.isEmpty()) {
            int[] now = qu.poll();
            int r = now[0];
            int c = now[1];
            int dist = now[2];

            if (r == r2 && c == c2) {
                return dist;
            }

            for (int i = 0; i < dx.length; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];

                if (nr < 0 || nc < 0 || nr >= n || nc >= n || visit[nr][nc]) {
                    continue;
                }

                visit[nr][nc] = true;
                qu.offer(new int[]{nr, nc, dist + 1});
            }
        }

        return -1;
    }
}