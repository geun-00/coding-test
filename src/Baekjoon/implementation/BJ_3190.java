package Baekjoon.implementation;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/3190">백준 3190번 - 구현 : 뱀</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-3190%EB%B2%88-%EB%B1%80">velog</a>
 * @since 2024-06-30
 */
public class BJ_3190 {
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

        //0-동, 1-북, 2-서, 3-남

        //오른쪽 90도 회전 = (d + 3) % 4
        //왼쪽 90도 회전 = (d + 1) % 4
        int d = 0;

        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        Deque<Point> snake = new ArrayDeque<>();
        snake.offer(new Point(1, 1));

        //현재 머리의 위치
        int headX = 1;
        int headY = 1;

        int second = 0;

        while (true) {

            second++;

            int nx = headX + dx[d];
            int ny = headY + dy[d];

            //벽에 부딪히면 게임 종료
            if (nx <= 0 || ny <= 0 || nx > n || ny > n) {
                System.out.println(second);
                return;
            }

            //자기 자신의 몸과 부딪히면 게임 종료
            for (Point p : snake) {
                if (p.x == nx && p.y == ny) {
                    System.out.println(second);
                    return;
                }
            }

            //사과가 있는 칸이면 꼬리는 유지한 채 머리만 추가(=몸 길이 추가)
            //사과가 없는 칸이면 꼬리 자르고 머리 추가(=몸 길이 유지)
            if (apples[nx][ny]) {
                apples[nx][ny] = false;
            } else {
                snake.pollLast();
            }

            snake.offerFirst(new Point(nx, ny));

            headX = nx;
            headY = ny;

            //X초 후에 방향을 회전해야 한다면 방향 회전
            if (map.containsKey(second)) {
                char c = map.get(second);
                if (c == 'L') {
                    d = (d + 1) % 4;
                } else if (c == 'D') {
                    d = (d + 3) % 4;
                }
            }
        }
    }
}