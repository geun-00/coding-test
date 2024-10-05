package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/6087">백준 6087번 - 다익스트라 : 레이저 통신</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-6087%EB%B2%88-%EB%A0%88%EC%9D%B4%EC%A0%80-%ED%86%B5%EC%8B%A0">velog</a>
 *
 * @since 2024-10-05
 */
public class BJ_6087 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        char[][] map = new char[h][w];

        boolean first = true;

        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;

        for (int i = 0; i < h; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                map[i][j] = arr[j];

                if (map[i][j] == 'C') {
                    if (first) {
                        first = false;
                        startX = i;
                        startY = j;
                    } else {
                        endX = i;
                        endY = j;
                    }
                }
            }
        }

        int[][][] dist = new int[h][w][4];

        for (int[][] d : dist) {
            for (int[] row : d) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }

        PriorityQueue<Node> qu = new PriorityQueue<>();
        for (int i = 0; i < 4; i++) {
            qu.offer(new Node(startX, startY, i, 0));
            dist[startX][startY][i] = 0;
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!qu.isEmpty()) {

            Node now = qu.poll();

            //우선순위큐 이기 때문에 최소 개수가 보장된다.
            if (now.x == endX && now.y == endY) {
                System.out.println(now.count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < h && ny < w && map[nx][ny] != '*') {

                    int newCount = now.count;
                    if (now.dir != i) { //방향이 다르면 거울 1개 추가
                        newCount += 1;
                    }

                    //더 적은 거울을 설치할 수 있을 때만 탐색하도록 한다.(다익스트라)
                    if (dist[nx][ny][i] > newCount) {
                        dist[nx][ny][i] = newCount;
                        qu.offer(new Node(nx, ny, i, newCount));
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node> {

        int x, y, dir, count;

        public Node(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }
}
