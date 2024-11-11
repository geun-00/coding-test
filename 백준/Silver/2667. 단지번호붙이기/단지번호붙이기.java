import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {

    static char[][] map;
    static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        visit = new boolean[n][n];
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '1' && !visit[i][j]) {
                    list.add(bfs(i, j));
                }
            }
        }

        list.sort(null);

        System.out.println(list.size());
        for (int i : list) {
            System.out.println(i);
        }
    }

    private static Integer bfs(int x, int y) {

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(x * n + y);

        visit[x][y] = true;

        int count = 1;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now / n + dx[i];
                int ny = now % n + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visit[nx][ny] || map[nx][ny] == '0') {
                    continue;
                }

                visit[nx][ny] = true;
                qu.offer(nx * n + ny);
                count++;
            }
        }

        return count;
    }
}