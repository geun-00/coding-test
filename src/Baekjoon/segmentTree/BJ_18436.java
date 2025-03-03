package Baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/18436">백준 18436번 - 세그먼트 트리 : 수열과 쿼리 37</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-18436%EB%B2%88-%EC%88%98%EC%97%B4%EA%B3%BC-%EC%BF%BC%EB%A6%AC-37">velog</a>
 * @since 2025-02-20
 */
public class BJ_18436 {

    static int[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        tree = new int[4 * n];

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(arr, 0, n - 1, 1);

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int q = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if (q == 1) {
                l--;
                update(0, n - 1, 1, l, r);
            } else if (q == 2) {
                l--;
                r--;
                sb.append(query(l, r, 0, n - 1, 1))
                  .append("\n");
            } else if (q == 3) {
                l--;
                r--;
                sb.append((r - l + 1) - query(l, r, 0, n - 1, 1))
                  .append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void init(int[] arr, int start, int end, int node) {
        if (start == end) {
            tree[node] = (arr[start] % 2 == 0) ? 1 : 0;
            return;
        }

        int mid = (start + end) / 2;
        init(arr, start, mid, node * 2);
        init(arr, mid + 1, end, node * 2 + 1);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static void update(int left, int right, int node, int index, int value) {
        if (left > index || index > right) {
            return;
        }

        if (left == right) {
            tree[node] = (value % 2 == 0) ? 1 : 0;
            return;
        }

        int mid = (left + right) / 2;
        update(left, mid, node * 2, index, value);
        update(mid + 1, right, node * 2 + 1, index, value);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static int query(int left, int right, int start, int end, int node) {
        if (left > end || right < start) {
            return 0;
        }

        if (start >= left && right >= end) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return query(left, right, start, mid, node * 2) +
            query(left, right, mid + 1, end, node * 2 + 1);
    }
}
