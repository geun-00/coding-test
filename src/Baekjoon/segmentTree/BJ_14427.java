package Baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14427">백준 14427번 - 세그먼트 트리 : 수열과 쿼리 15</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14427%EB%B2%88-%EC%88%98%EC%97%B4%EA%B3%BC-%EC%BF%BC%EB%A6%AC-15">velog</a>
 * @since 2025-01-10
 */
public class BJ_14427 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] tree = new int[4 * n];
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(1, 0, n - 1, tree, arr);

        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int query = Integer.parseInt(st.nextToken());

            if (query == 1) {

                int index = Integer.parseInt(st.nextToken()) - 1;
                int value = Integer.parseInt(st.nextToken());
                arr[index] = value;

                update(1, index, 0, n - 1, tree, arr);
            }
            else {
                sb.append(tree[1] + 1).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void update(int node, int index, int start, int end, int[] tree, int[] arr) {
        //인덱스 범위를 벗어나는 노드
        if (index < start || index > end) {
            return;
        }

        //리프노드
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;

        update(node * 2, index, start, mid, tree, arr);
        update(node * 2 + 1, index, mid + 1, end, tree, arr);

        tree[node] = (arr[tree[node * 2]] <= arr[tree[node * 2 + 1]])
            ? tree[node * 2]
            : tree[node * 2 + 1];
    }

    private static void init(int node, int start, int end, int[] tree, int[] arr) {
        //리프 노드
        if (start == end) {
            tree[node] = start;
            return;
        }

        int mid = (start + end) / 2;

        init(node * 2, start, mid, tree, arr);
        init(node * 2 + 1, mid + 1, end, tree, arr);

        tree[node] = (arr[tree[node * 2]] <= arr[tree[node * 2 + 1]])
            ? tree[node * 2]
            : tree[node * 2 + 1];
    }
}