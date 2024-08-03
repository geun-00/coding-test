package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1041">백준 1041번 - 그리디 : 주사위</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1041%EB%B2%88-%EC%A3%BC%EC%82%AC%EC%9C%84">velog</a>
 * @since 2024-08-02
 */
public class BJ_1041 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dice = new int[6];
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sum = 0;
        int max = 0;
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
            sum += dice[i];
            max = Math.max(max, dice[i]);
        }

        //주사위가 1개인 경우
        if (n == 1) {
            System.out.println(sum - max);
            return;
        }

        int[] min = new int[3];
        min[0] = Math.min(dice[0], dice[5]); //A와 F
        min[1] = Math.min(dice[1], dice[4]); //B와 E
        min[2] = Math.min(dice[2], dice[3]); //C와 D

        Arrays.sort(min);

        System.out.println(
                ((min[0] + min[1] + min[2]) * 4L) + //3면이 보이는 주사위
                ((min[0] + min[1]) * (8L * n - 12)) + //2면이 보이는 주사위
                (min[0] * (5L * n * n - 16L * n + 12)) //1면이 보이는 주사위
        );
    }
}
