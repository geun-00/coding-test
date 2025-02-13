package Baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/12837">백준 12837번 - 세그먼트 트리 : 가계부 (Hard)</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-12837%EB%B2%88-%EA%B0%80%EA%B3%84%EB%B6%80-Hard">velog</a>
 * @since 2025-02-01
 */
public class BJ_12837 {

    static long[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        tree = new long[4 * n];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int query = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            if (query == 1) {
                update(p, x, 1, 1, n);
            } else {
                sb.append(getResult(p, x, 1, 1, n)).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void update(int p, int x, int node, int start, int end) {

        //범위를 벗어난 노드
        if (start > p || end < p) {
            return;
        }

        //리프 노드
        if (start == end) {
            tree[node] += x;
            return;
        }

        int mid = (start + end) / 2;

        update(p, x, node * 2, start, mid);
        update(p, x, node * 2 + 1, mid + 1, end);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static long getResult(int left, int right, int node, int start, int end) {

        //범위를 벗어난 노드
        if (right < start || left > end) {
            return 0;
        }

        //범위 내의 노드, 더 내려갈 필요 없음
        if (start >= left && right >= end) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return getResult(left, right, node * 2, start, mid) +
               getResult(left, right, node * 2 + 1, mid + 1, end);
    }
}
