package Baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2268">백준 2268번 - 세그먼트 트리 : 수들의 합 7</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2268%EB%B2%88-%EC%88%98%EB%93%A4%EC%9D%98-%ED%95%A9-7">velog</a>
 * @since 2024-11-23
 */
public class BJ_2268 {

    static long[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int level = 0;
        while ((1 << level) < n) {
            level++;
        }

        int leafNodeIdx = 1 << level;
        tree = new long[leafNodeIdx * 2];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            b = b + leafNodeIdx - 1;

            if (a == 0) {
                c = c + leafNodeIdx - 1;

                if (b > c) {
                    int temp = b;
                    b = c;
                    c = temp;
                }

                sb.append(sum(b, c)).append("\n");
            }
            else {
                modify(b, c);
            }
        }

        System.out.print(sb);
    }

    private static long sum(int s, int e) {

        long sum = 0;

        while (s <= e) {

            if (s % 2 == 1) {
                sum += tree[s];
                s++;
            }

            if (e % 2 == 0) {
                sum += tree[e];
                e--;
            }

            s /= 2;
            e /= 2;
        }

        return sum;
    }

    private static void modify(int idx, int value) {

        tree[idx] = value;

        while (idx > 0) {
            idx /= 2;
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
        }
    }
}