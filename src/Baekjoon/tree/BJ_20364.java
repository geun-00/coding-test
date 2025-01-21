package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/20364">백준 20364번 - 트리 : 부동산 다툼</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-20364%EB%B2%88-%EB%B6%80%EB%8F%99%EC%82%B0-%EB%8B%A4%ED%88%BC">velog</a>
 * @since 2025-01-09
 */
public class BJ_20364 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        boolean[] tree = new boolean[n + 1];

        for (int i = 0; i < q; i++) {

            int x = Integer.parseInt(br.readLine());
            int temp = x;

            int num = 0;

            while (temp > 0) {
                if (tree[temp]) {
                    num = temp;
                }
                temp /= 2;
            }

            if (num == 0) {
                tree[x] = true;
                sb.append(0);
            } else {
                sb.append(num);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}