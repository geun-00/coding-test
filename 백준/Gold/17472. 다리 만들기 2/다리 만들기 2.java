import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] parent;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;
    static ArrayList<ArrayList<Point>> lands = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    bfs(i, j, ++num);
                }
            }
        }

        parent = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> qu = new PriorityQueue<>();

        for (ArrayList<Point> land : lands) {
            for (Point p : land) {

                int nowLand = map[p.x][p.y];

                for (int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    int len = 0;

                    while (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                        if (map[nx][ny] == nowLand) {
                            break;
                        }
                        if (map[nx][ny] != 0) {
                            if (len >= 2) {
                                qu.offer(new Edge(nowLand, map[nx][ny], len));
                            }
                            break;
                        } else {
                            len++;
                        }
                        nx += dx[i];
                        ny += dy[i];
                    }
                }
            }
        }

        int used = 0;
        int result = 0;

        while (!qu.isEmpty()) {
            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                used++;
                result += e.weight;
            }
        }

        if (used != num - 1) {
            result = -1;
        }

        bw.write(String.valueOf(result));
        bw.flush();

        bw.close();
        br.close();
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void bfs(int x, int y, int num) {
        Queue<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(x, y));

        visit[x][y] = true;
        map[x][y] = num;

        ArrayList<Point> list = new ArrayList<>();
        list.add(new Point(x, y));

        while (!qu.isEmpty()) {
            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visit[nx][ny] || map[nx][ny] == 0) {
                    continue;
                }
                map[nx][ny] = num;
                visit[nx][ny] = true;
                qu.offer(new Point(nx, ny));
                list.add(new Point(nx, ny));
            }
        }

        lands.add(list);
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
