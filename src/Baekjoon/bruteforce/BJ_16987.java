package Baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16987">백준 16987번 - 브루트포스 : 계란으로 계란치기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16987%EB%B2%88-%EA%B3%84%EB%9E%80%EC%9C%BC%EB%A1%9C-%EA%B3%84%EB%9E%80%EC%B9%98%EA%B8%B0">velog</a>
 * @since 2025-01-08
 */
public class BJ_16987 {

    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] eggs = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        solve(0, eggs);

        System.out.println(ans);
    }

    private static void solve(int index, int[][] eggs) {

        if (index == eggs.length) {
            int count = 0;
            for (int[] egg : eggs) {
                if(egg[0] <= 0) count++;
            }
            ans = Math.max(count, ans);
            return;
        }

        //손에 든 계란이 깨진 경우
        if (eggs[index][0] <= 0) {
            solve(index + 1, eggs);
            return;
        }

        boolean flag = false;

        for (int i = 0; i < eggs.length; i++) {

            if (i == index || eggs[i][0] <= 0) continue;

            flag = true;

            //두 계란의 내구도 감소
            eggs[i][0] -= eggs[index][1];
            eggs[index][0] -= eggs[i][1];

            solve(index + 1, eggs);

            //계란 내구도 복구
            eggs[i][0] += eggs[index][1];
            eggs[index][0] += eggs[i][1];
        }

        //깨지지 않은 다른 계란이 없는 경우
        if (!flag) {
            solve(index + 1, eggs);
        }
    }
}