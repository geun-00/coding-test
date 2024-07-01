package Baekjoon.implementation;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16236">백준 16236번 - 구현 : 아기 상어</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16236%EB%B2%88-%EC%95%84%EA%B8%B0-%EC%83%81%EC%96%B4">velog</a>
 * @since 2024-07-01
 */
public class BJ_16236 {

    static class Node implements Comparable<Node> {
        int x, y, move;

        public Node(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }

        @Override
        public int compareTo(Node o) {
            if (this.move != o.move) { //거리가 다르면 거리 순 정렬
                return this.move - o.move;
            }
            else if (this.x != o.x) { //거리가 같으면 x 좌표 순 정렬(가장 위에 있는 물고기)
                return this.x - o.x;
            }

            return this.y - o.y; //거리와 x 좌표가 같으면 y 좌표 순 정렬(가장 왼쪽에 있는 물고기)
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

                if (map[i][j] == 9) { //처음 아기 상어의 위치
                    map[i][j] = 0;
                    shark = new Point(i, j);
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int size = 2; //아기 상어의 크기
        int eat = 0;  //아기 상어가 먹은 물고기 개수
        int time = 0; //모든 물고기를 잡아 먹는데 걸린 시간

        while (true) {

            Queue<Node> qu = new PriorityQueue<>();
            boolean[][] visit = new boolean[n][n];

            qu.offer(new Node(shark.x, shark.y, 0));
            visit[shark.x][shark.y] = true;

            boolean check = false;

            while (!qu.isEmpty()) {

                Node now = qu.poll();

                //잡아먹을 수 있는 물고기 발견
                //우선순위 큐로 인해 조건에 맞는 가장 적절한 위치에 있는 물고기가 선택이 된다.
                if (map[now.x][now.y] != 0 && map[now.x][now.y] < size) {

                    map[now.x][now.y] = 0;

                    time += now.move; //이동한 거리가 걸린 시간이다.
                    eat++; //먹은 물고기 수 증가
                    shark = new Point(now.x, now.y); //먹은 물고기 위치에서 다시 BFS 수행을 위해 아기 상어 위치 업데이트

                    check = true;
                    break;
                }

                //이동할 수 있는 위치 탐색
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

            if (!check) { //모든 칸을 탐색할 동안 잡아먹을 물고기가 없다 == 반복 종료
                break;
            }

            if (size == eat) { //자신의 크기와 같은 수의 물고기를 먹을 때마다 크기 1 증가
                size++;
                eat = 0;
            }
        }

        System.out.println(time);
    }
}
