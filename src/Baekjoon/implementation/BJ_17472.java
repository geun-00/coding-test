package Baekjoon.implementation;

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

/**
 * <a href = "https://www.acmicpc.net/problem/17472">백준 17472번 - 구현 : 다리 만들기 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/17472%EB%B2%88-%EB%8B%A4%EB%A6%AC-%EB%A7%8C%EB%93%A4%EA%B8%B0-2">velog</a>
 *
 * @since 2024-10-21
 */
public class BJ_17472 {

    static int n, m;
    static int[] parent; //MST용 배열
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;
    static ArrayList<ArrayList<Point>> lands = new ArrayList<>(); //각 나라의 모든 섬

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

        //BFS로 나라를 구분하면서 모든 섬의 위치 저장
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    bfs(i, j, ++num);
                }
            }
        }

        //MST용 배열 초기화
        parent = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> qu = new PriorityQueue<>();

        //섬에서 다른 나라의 섬으로 이동하는 다리 우선순위 큐에 저장
        for (ArrayList<Point> land : lands) {
            for (Point p : land) {

                //현재 나라의 번호
                int nowLand = map[p.x][p.y];

                for (int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    int len = 0;

                    while (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                        if (map[nx][ny] == nowLand) { //같은 나라의 섬을 만나면 break
                            break;
                        }
                        if (map[nx][ny] != 0) {
                            if (len >= 2) { //다른 나라의 섬을 만나고 연결된 다리의 길이가 2 이상이면 큐에 저장
                                qu.offer(new Edge(nowLand, map[nx][ny], len));
                            }
                            break;
                        }

                        len++;
                        nx += dx[i];
                        ny += dy[i];
                    }
                }
            }
        }

        int used = 0;   //설치한 다리 개수
        int result = 0; //설치된 다리의 총 길이

        //유니온 파인드로 다리 길이 최솟값 구하기
        while (!qu.isEmpty()) {
            Edge e = qu.poll();

            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                used++;
                result += e.weight;
            }
        }

        //설치된 다리 개수가 (나라 개수 - 1)보다 적으면 모든 나라를 연결하지 못한 것이다.
        if (used < num - 1) {
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
