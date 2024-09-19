package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/20055">백준 20055번 - 구현 : 컨베이어 벨트 위의 로봇</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-20055%EB%B2%88-%EC%BB%A8%EB%B2%A0%EC%9D%B4%EC%96%B4-%EB%B2%A8%ED%8A%B8-%EC%9C%84%EC%9D%98-%EB%A1%9C%EB%B4%87">velog</a>
 * @since 2024-09-11
 */
public class BJ_20055 {

    static int[] durability;
    static boolean[] robots;
    static int zero = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        robots = new boolean[n];
        durability = new int[2 * n];

        for (int i = 0; i < durability.length; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;

        while (zero < k) {
            count++;

            //벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
            rotate();

            //가장 먼저 벨트에 올라간 로봇부터 한 칸 이동
            move();

            //올리는 위치에 로봇 올림
            loadRobot();
        }

        System.out.println(count);
    }

    private static void rotate() {
        //벨트 회전
        int temp = durability[durability.length - 1];
        for (int i = durability.length - 1; i > 0; i--) {
            durability[i] = durability[i - 1];
        }
        durability[0] = temp;

        //로봇 회전
        for (int i = robots.length - 1; i > 0; i--) {
            robots[i] = robots[i - 1];
        }

        //올리는 위치와 내리는 위치는 비워준다.
        robots[0] = robots[robots.length - 1] = false;
    }

    private static void move() {
        for (int i = robots.length - 1; i > 0; i--) {

            //현재 칸에 로봇이 있고, 이동하려는 칸에 로봇이 없고, 내구도가 1 이상 이어야 한다.
            if (robots[i - 1] && !robots[i] && durability[i] > 0) {

                robots[i] = true;
                robots[i - 1] = false;

                durability[i]--;
                if (durability[i] == 0) {
                    zero++;
                }
            }
        }

        //내리는 위치에 있는 로봇 내리기
        robots[robots.length - 1] = false;
    }

    private static void loadRobot() {
        if (durability[0] > 0) {
            robots[0] = true;

            durability[0]--;
            if (durability[0] == 0) {
                zero++;
            }
        }
    }
}