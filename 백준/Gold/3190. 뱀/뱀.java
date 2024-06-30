import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        boolean[][] apples = new boolean[n + 1][n + 1];

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            apples[x][y] = true;
        }

        HashMap<Integer, Character> map = new HashMap<>();
        int l = Integer.parseInt(br.readLine());

        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            char rotate = st.nextToken().charAt(0);

            map.put(x, rotate);
        }
        
        int d = 0;

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        Deque<Point> snake = new ArrayDeque<>();
        snake.offer(new Point(1, 1));

        int headX = 1;
        int headY = 1;

        int second = 0;

        while (true) {

            second++;

            int nx = headX + dx[d];
            int ny = headY + dy[d];

            if (nx <= 0 || ny <= 0 || nx > n || ny > n) {
                break;
            }

            boolean flag = false;
            for (Point p : snake) {
                if (p.x == nx && p.y == ny) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                break;
            }

            if (apples[nx][ny]) {
                apples[nx][ny] = false;
            } else {
                snake.pollLast();
            }

            snake.offerFirst(new Point(nx, ny));
            headX = nx;
            headY = ny;

            if (map.containsKey(second)) {
                char c = map.get(second);
                if (c == 'L') {
                    d = (d + 3) % 4;
                } else if (c == 'D') {
                    d = (d + 1) % 4;
                }
            }
        }

        System.out.println(second);
    }
}