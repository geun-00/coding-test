package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/5213">백준 5213번 - 과외맨</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-5213%EB%B2%88-%EA%B3%BC%EC%99%B8%EB%A7%A8">velog</a>
 * @since 2025-03-20
 */
public class BJ_5213 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 1
        int[][] tile = new int[n][n * 2];
        int[][] tileNum = new int[n][n * 2];
        int num = 1;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < n; j++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    tile[i][j * 2] = Integer.parseInt(st.nextToken());
                    tile[i][j * 2 + 1] = Integer.parseInt(st.nextToken());
                    tileNum[i][j * 2] = num;
                    tileNum[i][j * 2 + 1] = num;
                    num++;
                }
            } else {
                for (int j = 0; j < n - 1; j++) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    tile[i][j * 2 + 1] = Integer.parseInt(st.nextToken());
                    tile[i][j * 2 + 2] = Integer.parseInt(st.nextToken());
                    tileNum[i][j * 2 + 1] = num;
                    tileNum[i][j * 2 + 2] = num;
                    num++;
                }
            }
        }

        // 2
        List<Integer>[] graph = initGraph(n, tileNum, tile, num);

        // 3
        int[] prev = new int[num];
        int[] dist = new int[num];
        Arrays.fill(prev, -1);
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.dist));
        pq.offer(new Node(1, 1));
        dist[1] = 1;

        // 4
        int max = 1;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            max = Math.max(max, node.num);
            if (max == num - 1) break;

            for (int next : graph[node.num]) {
                if (dist[next] > node.dist + 1) {
                    dist[next] = node.dist + 1;
                    prev[next] = node.num;
                    pq.offer(new Node(next, dist[next]));
                }
            }
        }

        // 5
        Deque<Integer> stk = new ArrayDeque<>();
        int now = max;
        while (now != -1) {
            stk.push(now);
            now = prev[now];
        }

        StringBuilder sb = new StringBuilder();

        sb.append(stk.size()).append("\n");
        while (!stk.isEmpty()) {
            sb.append(stk.pop()).append(" ");
        }

        System.out.print(sb);
    }

    // 2
    private static List<Integer>[] initGraph(int n, int[][] tileNum, int[][] tile, int num) {
        List<Integer>[] graph = new ArrayList[num];
        for (int i = 0; i < num; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n * 2; y++) {
                int curTile = tileNum[x][y];
                if (curTile == 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= 2 * n) continue;
                    if (tileNum[nx][ny] == 0) continue;

                    if (tileNum[x][y] != tileNum[nx][ny] && tile[x][y] == tile[nx][ny]) {
                        graph[curTile].add(tileNum[nx][ny]);
                    }
                }
            }
        }

        return graph;
    }

    static class Node {
        int num, dist;

        public Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }
}
