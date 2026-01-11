import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][n];
        int[][][] dist = new int[n][n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        dist[0][0][0] = 0;

        int[] initNode = {0, 0, 0, 0};
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node[3]));
        pq.offer(initNode);

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int x = node[0];
            int y = node[1];
            int moveCount = node[2];
            int time = node[3];

            if (time > dist[x][y][moveCount]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                int nMoveCount = (moveCount + 1) % 3;
                int nTime = time + t;

                if (nMoveCount == 0) {
                    nTime += arr[nx][ny];
                }

                if (nTime < dist[nx][ny][nMoveCount]) {
                    dist[nx][ny][nMoveCount] = nTime;
                    pq.offer(new int[]{nx, ny, nMoveCount, nTime});
                }
            }
        }

        System.out.println(Math.min(dist[n - 1][n - 1][0], Math.min(dist[n - 1][n - 1][1], dist[n - 1][n - 1][2])));
    }
}