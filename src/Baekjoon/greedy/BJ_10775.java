package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/10775">백준 10775번 - 그리디 : 공항</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10775%EB%B2%88-%EA%B3%B5%ED%95%AD">velog</a>
 * @since 2024-07-30
 */
public class BJ_10775 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int gate = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        parent = new int[gate + 1];
        for (int i = 1; i <= gate; i++) {
            parent[i] = i;
        }

        int count = 0;

        for (int i = 0; i < p; i++) {
            int g = Integer.parseInt(br.readLine());

            int temp = find(g); //도킹 가능한 게이트 찾기

            if (temp == 0) { //도킹 불가능
                break;
            }

            count++;

            union(temp - 1, temp); //이전 게이트로 업데이트
        }

        System.out.println(count);
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
}
