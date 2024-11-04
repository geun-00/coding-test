import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int INF = Integer.MAX_VALUE;

    static ArrayList<Integer> virus = new ArrayList<>();

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] selected;
    static int[][] map;

    static int n, m;
    static int min = INF;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        selected = new int[m];

        int empty = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    virus.add(i * n + j);
                }
                if (map[i][j] == 0) {
                    empty++;
                }
            }
        }

        if (empty == 0) {
            System.out.println(0);
            return;
        }

        int size = virus.size();

        bitCount(size, empty);
//        backTrack(0, 0, size, empty);

        System.out.println(min == INF ? -1 : min);
    }

    private static void backTrack(int start, int idx, int size, int empty) {

        if (idx == m) {
            bfs(empty);
            return;
        }

        for (int i = start; i < size; i++) {
            selected[idx] = i;
            backTrack(i + 1, idx + 1, size, empty);
        }
    }

    private static void bitCount(int size, int empty) {

        for (int i = 0; i < (1 << size); i++) {

            if (Integer.bitCount(i) == m) {

                int idx = 0;

                for (int j = 0; j < size; j++) {
                    if ((i & (1 << j)) != 0) {
                        selected[idx++] = j;
                    }
                }

                bfs(empty);
            }
        }
    }

    private static void bfs(int empty) {

        boolean[][] visit = new boolean[n][n];

        Queue<Integer> qu = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            int idx = virus.get(selected[i]);
            qu.offer(idx);
        }

        int count = 0;

        while (!qu.isEmpty()) {

            if (count >= min) {
                return;
            }

            int size = qu.size();
            for (int i = 0; i < size; i++) {

                int now = qu.poll();
                int x = now / n;
                int y = now % n;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || visit[nx][ny] || map[nx][ny] == 1) {
                        continue;
                    }

                    visit[nx][ny] = true;
                    qu.offer(nx * n + ny);

                    if (map[nx][ny] == 0) empty--;
                }
            }
            
            count++;
            
            if (empty == 0) {
                min = Math.min(min, count);
                return;
            }

            
        }
    }
}