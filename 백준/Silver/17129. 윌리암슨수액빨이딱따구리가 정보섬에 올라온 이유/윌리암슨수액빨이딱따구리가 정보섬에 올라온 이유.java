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

        char[][] arr = new char[n][m];
        Queue<int[]> qu = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();

            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '2') {
                    qu.offer(new int[]{i, j, 0});
                    visit[i][j] = true;
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {
            int[] node = qu.poll();

            int x = node[0];
            int y = node[1];
            int count = node[2];

            if (arr[x][y] == '3' || arr[x][y] == '4' || arr[x][y] == '5') {
                System.out.println("TAK");
                System.out.println(count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (visit[nx][ny] || arr[nx][ny] == '1') {
                    continue;
                }

                visit[nx][ny] = true;
                qu.offer(new int[]{nx, ny, count + 1});
            }
        }

        System.out.println("NIE");
    }
}