package Baekjoon.dac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/1802">백준 1802번 - 분할 정복 : 종이 접기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1802%EB%B2%88-%EC%A2%85%EC%9D%B4-%EC%A0%91%EA%B8%B0">velog</a>
 * @since 2025-02-28
 */
public class BJ_1802 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            char[] paper = br.readLine().toCharArray();
            System.out.println(solve(0, paper.length - 1, paper) ? "YES" : "NO");
        }
    }

    public static boolean solve(int start, int end, char[] paper) {
        if (start >= end) {
            return true;
        }

        //좌우가 대칭이 되는지 확인
        int mid = (start + end) / 2;
        for (int i = start, j = end; i < mid; i++, j--) {
            if (paper[i] == paper[j]) {
                return false;
            }
        }

        //중간을 제외하고 왼쪽과 오른쪽 부분도 모두 대칭이 되는지 확인
        return solve(start, mid - 1, paper) && solve(mid + 1, end, paper);
    }
}
