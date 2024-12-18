import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    map[i][j] = -1;
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        //운전 시작 위치
        int taxiX = Integer.parseInt(st.nextToken()) - 1;
        int taxiY = Integer.parseInt(st.nextToken()) - 1;

        Guest[] guests = new Guest[m + 1];

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken()) - 1;
            int startY = Integer.parseInt(st.nextToken()) - 1;

            int endX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;

            map[startX][startY] = i;
            guests[i] = new Guest(startX, startY, endX, endY);
        }

        for (int i = 0; i < m; i++) {

            int[] result = guestBfs(taxiX, taxiY);
            int guestIdx = result[0];
            int guestDist = result[1];

            if (guestIdx == -1 || guestDist > fuel) {
                System.out.println(-1);
                return;
            }

            fuel -= guestDist;
            Guest g = guests[guestIdx];
            map[g.startX][g.startY] = 0;

            int dist = destinationBfs(g.startX, g.startY, g.endX, g.endY);

            if (dist == -1 || dist > fuel) {
                System.out.println(-1);
                return;
            }

            fuel -= dist;
            fuel += dist * 2;

            taxiX = g.endX;
            taxiY = g.endY;
        }

        System.out.println(fuel);
    }

    private static int destinationBfs(int startX, int startY, int endX, int endY) {

        Queue<Node> qu = new ArrayDeque<>();

        qu.offer(new Node(startX, startY, 0));

        boolean[][] visit = new boolean[n][n];
        visit[startX][startY] = true;

        while (!qu.isEmpty()) {

            Node now = qu.poll();
            int x = now.x;
            int y = now.y;
            int dist = now.dist;

            if (x == endX && y == endY) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visit[nx][ny] || map[nx][ny] == -1) {
                    continue;
                }

                visit[nx][ny] = true;
                qu.offer(new Node(nx, ny, dist + 1));
            }
        }

        return -1;
    }

    private static int[] guestBfs(int taxiX, int taxiY) {

        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(taxiX, taxiY, 0));

        boolean[][] visit = new boolean[n][n];
        visit[taxiX][taxiY] = true;

        while (!qu.isEmpty()) {

            Node now = qu.poll();
            int x = now.x;
            int y = now.y;
            int dist = now.dist;

            if (map[x][y] > 0) {
                return new int[]{map[x][y], dist};
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visit[nx][ny] || map[nx][ny] == -1) {
                    continue;
                }

                visit[nx][ny] = true;
                qu.offer(new Node(nx, ny, dist + 1));
            }
        }

        return new int[]{-1, -1};
    }

    static class Node implements Comparable<Node> {

        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {

            if (this.dist == o.dist) {
                if (this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x - o.x;
            }

            return this.dist - o.dist;
        }
    }

    static class Guest {

        int startX, startY;
        int endX, endY;

        public Guest(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }
}