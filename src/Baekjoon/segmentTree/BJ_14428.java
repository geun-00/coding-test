package Baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14428">백준 14428번 - 세그먼트 트리 : 수열과 쿼리 16</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14428%EB%B2%88-%EC%88%98%EC%97%B4%EA%B3%BC-%EC%BF%BC%EB%A6%AC-16">velog</a>
 *
 * @since 2024-11-27
 */
public class BJ_14428 {

    static int[] tree;
    static int[] arr;
    static int leafIdx;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //세그먼트 트리 리프노드 인덱스, 크기 구하기
        int level = 1;
        while ((1 << level) < n) {
            level++;
        }

        leafIdx = 1 << level;
        tree = new int[leafIdx * 2];

        arr = new int[n + 1];
        arr[0] = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            tree[leafIdx + i] = i + 1;
        }

        for (int i = leafIdx - 1; i > 0; i--) {
            tree[i] = getMinIndex(tree[i * 2], tree[i * 2 + 1]);
        }

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            int q = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            a = a + leafIdx - 1;

            if (q == 1) {
                update(a, b);
            } else {

                b = b + leafIdx - 1;

                sb.append(getMin(a, b)).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static int getMinIndex(int left, int right) {

        if (arr[left] == arr[right]) {
            return Math.min(left, right);
        }

        if (arr[left] < arr[right]) {
            return left;
        }

        return right;
    }

    private static int getMin(int s, int e) {

        int minIdx = 0;

        while (s <= e) {

            if (s % 2 == 1) {
                minIdx = getMinIndex(minIdx, tree[s]);
                s++;
            }

            if (e % 2 == 0) {
                minIdx = getMinIndex(minIdx, tree[e]);
                e--;
            }

            s /= 2;
            e /= 2;
        }

        return minIdx;
    }

    private static void update(int idx, int value) {

        arr[idx - leafIdx + 1] = value;

        while (idx > 1) {
            idx /= 2;
            tree[idx] = getMinIndex(tree[idx * 2], tree[idx * 2 + 1]);
        }
    }
}