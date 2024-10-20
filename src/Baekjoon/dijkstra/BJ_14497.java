package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14497">백준 14497번 - 다익스트라 : 주난의 난</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14497%EB%B2%88-%EC%A3%BC%EB%82%9C%EC%9D%98-%EB%82%9C">velog</a>
 *
 * @since 2024-10-20
 */
public class BJ_14497 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        //주난의 위치
        int x1 = Integer.parseInt(st.nextToken()) - 1;
        int y1 = Integer.parseInt(st.nextToken()) - 1;

        //범인의 위치
        int x2 = Integer.parseInt(st.nextToken()) - 1;
        int y2 = Integer.parseInt(st.nextToken()) - 1;

        char[][] map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Deque<Point> qu = new ArrayDeque<>();
        qu.offer(new Point(x1, y1, 0));

        boolean[][] visit = new boolean[n][m];
        visit[x1][y1] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {

            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                //다음 위치가 "#"이면 결과 출력
                if (nx == x2 && ny == y2) {
                    bw.write(String.valueOf(now.count + 1));
                    bw.flush();
                    return;
                }

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visit[nx][ny]) {

                    visit[nx][ny] = true;

                    //1을 만나면 나중에 탐색될 수 있도록 마지막에 저장
                    if (map[nx][ny] == '1') {
                        qu.offerLast(new Point(nx, ny, now.count + 1));
                    }
                    //0을 만나면 가장 먼저 탐색될 수 있도록 앞에 저장
                    else {
                        qu.offerFirst(new Point(nx, ny, now.count));
                    }
                }
            }
        }

        bw.close();
        br.close();
    }

    static class Point {
        int x, y, count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
