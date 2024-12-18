package Baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/19238">백준 16918번 - 시뮬레이션 : 스타트 택시</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-19238%EB%B2%88-%EC%8A%A4%ED%83%80%ED%8A%B8-%ED%83%9D%EC%8B%9C">velog</a>
 * @since 2024-12-11
 */
public class BJ_16918 {

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

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken()) - 1;
            int startY = Integer.parseInt(st.nextToken()) - 1;

            int endX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;

            map[startX][startY] = i + 1;
            guests[i + 1] = new Guest(startX, startY, endX, endY);
        }

        for (int i = 0; i < m; i++) {

            //현재 택시 위치에서 가장 가까운 손님 찾기
            GuestInfo result = guestBfs(taxiX, taxiY);

            int guestIdx = result.idx;
            int guestDist = result.dist;

            //도달 가능한 손님이 없거나 연료가 부족한 경우
            if (guestIdx == -1 || guestDist > fuel) {
                System.out.println(-1);
                return;
            }

            //손님 위치까지 이동
            fuel -= guestDist;

            //손님의 출발지, 도착지 정보
            Guest g = guests[guestIdx];
            //손님 위치 빈 칸으로 초기화
            map[g.startX][g.startY] = 0;

            //손님 위치에서 도착지까지 최단 거리
            int dist = destinationBfs(g.startX, g.startY, g.endX, g.endY);

            //도착지까지 갈 수 없거나 연료가 부족한 경우
            if (dist == -1 || dist > fuel) {
                System.out.println(-1);
                return;
            }

            //도착지까지 연료를 사용하고 (이동한 거리 * 2) 연료 충전
            fuel -= dist;
            fuel += dist * 2;

            //택시 위치 손님의 도착지로 이동
            taxiX = g.endX;
            taxiY = g.endY;
        }

        System.out.println(fuel);
    }

    private static GuestInfo guestBfs(int taxiX, int taxiY) {

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
                return new GuestInfo(map[x][y], dist);
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

        return new GuestInfo(-1, -1);
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

    static class GuestInfo {

        int idx, dist;

        public GuestInfo(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }
}