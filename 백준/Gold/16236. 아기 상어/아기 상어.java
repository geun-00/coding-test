import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int x, y, move;

        public Node(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }

        @Override
        public int compareTo(Node o) {
            if (this.move != o.move) {
                return this.move - o.move;
            }
            else if (this.x != o.x) {
                return this.x - o.x;
            }

            return this.y - o.y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        Point shark = null;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    shark = new Point(i, j);
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int size = 2;
        int eat = 0;
        int time = 0;

        while (true) {
            Queue<Node> qu = new PriorityQueue<>();
            boolean[][] visit = new boolean[n][n];

            qu.offer(new Node(shark.x, shark.y, 0));
            visit[shark.x][shark.y] = true;

            boolean check = false;

            while (!qu.isEmpty()) {
                Node now = qu.poll();

                if (map[now.x][now.y] != 0 && map[now.x][now.y] < size) {
                    map[now.x][now.y] = 0;
                    time += now.move;
                    shark = new Point(now.x, now.y);
                    eat++;
                    check = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                        if (!visit[nx][ny] && map[nx][ny] <= size) {
                            visit[nx][ny] = true;
                            qu.offer(new Node(nx, ny, now.move + 1));
                        }
                    }
                }
            }

            if (!check) {
                break;
            }

            if (size == eat) {
                size++;
                eat = 0;
            }
        }

        System.out.println(time);
    }
}
