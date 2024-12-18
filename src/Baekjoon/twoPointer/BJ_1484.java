package Baekjoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href = "https://www.acmicpc.net/problem/1484">백준 1484번 - 투 포인터 : 다이어트</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1484%EB%B2%88-%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8">velog</a>
 * @since 2024-12-17
 */
public class BJ_1484 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine());

        int s = 1, e = 1;
        List<Integer> ans = new ArrayList<>();

        while (e < g) {

            int diff = e * e - s * s;

            if (diff == g) {
                ans.add(e);
                e++;
            } else if (diff < g) {
                e++;
            } else {
                s++;
            }
        }

        if (ans.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int a : ans) {
                System.out.println(a);
            }
        }
    }
}