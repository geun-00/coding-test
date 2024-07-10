package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14499">백준 14499번 - 구현 : 주사위 굴리기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14499%EB%B2%88-%EC%A3%BC%EC%82%AC%EC%9C%84-%EA%B5%B4%EB%A6%AC%EA%B8%B0">velog</a>
 * @since 2024-07-10
 */
public class BJ_14499 {

    static final int TOP = 0;
    static final int BACK = 1;
    static final int RIGHT = 2;
    static final int LEFT = 3;
    static final int FRONT = 4;
    static final int BOTTOM = 5;

    static int[] dice = new int[6];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < k; i++) {

            int d = Integer.parseInt(st.nextToken()) - 1;

            int nx = x + dx[d];
            int ny = y + dy[d];

            //바깥으로 이동하는 경우
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }

            rollDice(d); //주사위 굴리기

            if (map[nx][ny] != 0) { //0이 아닌 칸인 경우
                //주사위 바닥면에 칸에 쓰여 있는 수 복사
                dice[BOTTOM] = map[nx][ny];
                //칸에 쓰여 있는 수는 0이 된다.
                map[nx][ny] = 0;
            } else { //0인 칸인 경우
                //칸에 주사위 바닥면에 쓰여 있는 수 복사
                map[nx][ny] = dice[BOTTOM];
            }

            //주사위 이동 후 상단에 쓰여 있는 값 출력
            System.out.println(dice[TOP]);

            x = nx;
            y = ny;
        }
    }

    private static void rollDice(int d) {

        int[] temp = new int[6];
        for (int i = 0; i < 6; i++) {
            temp[i] = dice[i];
        }

        switch (d) {
            case 0: //동
                dice[TOP] = temp[LEFT];
                dice[RIGHT] = temp[TOP];
                dice[LEFT] = temp[BOTTOM];
                dice[BOTTOM] = temp[RIGHT];
                break;
            case 1: //서
                dice[TOP] = temp[RIGHT];
                dice[RIGHT] = temp[BOTTOM];
                dice[LEFT] = temp[TOP];
                dice[BOTTOM] = temp[LEFT];
                break;
            case 2: //북
                dice[TOP] = temp[FRONT];
                dice[FRONT] = temp[BOTTOM];
                dice[BACK] = temp[TOP];
                dice[BOTTOM] = temp[BACK];
                break;
            case 3: //남
                dice[TOP] = temp[BACK];
                dice[FRONT] = temp[TOP];
                dice[BACK] = temp[BOTTOM];
                dice[BOTTOM] = temp[FRONT];
                break;
        }
    }
}