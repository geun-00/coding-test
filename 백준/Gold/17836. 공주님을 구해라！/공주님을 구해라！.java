import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][][] visit = new boolean[n][m][2];
        visit[0][0][0] = true;

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{0, 0, 0, 0});

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();
            int x = cur[0];
            int y = cur[1];
            int time = cur[2];
            int hasGram = cur[3];

            if (time > t) continue;
            if (x == n - 1 && y == m - 1) {
                System.out.println(time);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (arr[nx][ny] == 0) {
                    if (!visit[nx][ny][hasGram]) {
                        visit[nx][ny][hasGram] = true;
                        qu.offer(new int[]{nx, ny, time + 1, hasGram});
                    }
                } else if (arr[nx][ny] == 1) {
                    if (hasGram == 1 && !visit[nx][ny][hasGram]) {
                        visit[nx][ny][hasGram] = true;
                        qu.offer(new int[]{nx, ny, time + 1, hasGram});
                    }
                } else {
                    if (!visit[nx][ny][hasGram]) {
                        visit[nx][ny][hasGram] = true;
                        qu.offer(new int[]{nx, ny, time + 1, 1 - hasGram});
                    }
                }
            }
        }

        System.out.println("Fail");
    }
}
