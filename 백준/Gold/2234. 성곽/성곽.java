import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    //서, 북, 동, 남
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] map;
    static int[][] roomNum;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        roomNum = new int[m][n];
        visit = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int rooms = 0;
        int max = 0;

        ArrayList<Integer> roomsSize = new ArrayList<>();
        roomsSize.add(0);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    rooms++;
                    int size = bfs(i, j, rooms);
                    max = Math.max(max, size);
                    roomsSize.add(size);
                }
            }
        }

        System.out.println(rooms);
        System.out.println(max);

        max = 0;
        HashSet<String> set = new HashSet<>();

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {

                for (int d = 0; d < 4; d++) {

                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                        continue;
                    }

                    int nowRoom = roomNum[x][y];
                    int nextRoom = roomNum[nx][ny];

                    if (nowRoom != nextRoom) {

                        String s = Math.min(nowRoom, nextRoom) + " " + Math.max(nowRoom, nextRoom);

                        if (set.add(s)) {

                            max = Math.max(max, roomsSize.get(nowRoom) + roomsSize.get(nextRoom));
                        }
                    }
                }
            }
        }

        System.out.println(max);
    }

    private static int bfs(int x, int y, int rooms) {

        visit[x][y] = true;

        roomNum[x][y] = rooms;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(x * n + y);

        int size = 0;

        while (!qu.isEmpty()) {

            int now = qu.poll();

            int now_x = now / n;
            int now_y = now % n;
            int wall = map[now_x][now_y];

            size++;

            for (int i = 0; i < 4; i++) {

                int nx = now_x + dx[i];
                int ny = now_y + dy[i];

                if ((wall & (1 << i)) == 0 && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    roomNum[nx][ny] = rooms;
                    qu.offer(nx * n + ny);
                }
            }
        }

        return size;
    }
}