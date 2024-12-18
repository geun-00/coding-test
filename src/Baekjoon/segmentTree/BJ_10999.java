package Baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/10999">백준 10999번 - 세그먼트 트리 : 구간 합 구하기 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10999%EB%B2%88-%EA%B5%AC%EA%B0%84-%ED%95%A9-%EA%B5%AC%ED%95%98%EA%B8%B0-2">velog</a>
 * @since 2024-12-10
 */
public class BJ_10999 {

    static long[] tree;
    static long[] lazy;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int level = 0;
        while ((1 << level) < n) {
            level++;
        }

        int treeSize = 1 << (level + 1);

        tree = new long[treeSize];
        lazy = new long[treeSize];

        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(1, 0, n - 1, arr);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m + k; i++) {

            st = new StringTokenizer(br.readLine());

            int q = Integer.parseInt(st.nextToken());

            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;

            if (q == 1) {
                long num = Long.parseLong(st.nextToken());
                update(left, right, 1, 0, n - 1, num);
            } else {
                sb.append(query(left, right, 1, 0, n - 1)).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void init(int node, int start, int end, long[] arr) {

        //리프 노드 도착
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;

        //왼쪽 노드
        init(node * 2, start, mid, arr);

        //오른쪽 노드
        init(node * 2 + 1, mid + 1, end, arr);

        //부모 노드 구간합 저장
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static void update(int left, int right, int node, int start, int end, long num) {

        //갱신(전파)가 필요한 경우 먼저 진행
        propagation(node, start, end);

        //타겟 구간에 포함되지 않는 구간인 경우
        if (start > right || end < left) return;

        //타겟 구간에 포함되는 노드인 경우
        if (left <= start && end <= right) {

            //구간 길이만큼 구간 합 수정
            tree[node] += num * (end - start + 1);

            //리프 노드가 아닌 경우 자식 노드에게 전파
            if (start != end) {
                lazy[node * 2] += num;
                lazy[node * 2 + 1] += num;
            }

            //밑의 자식 노드들은 나중에 변경하고 현재 노드에서 바로 종료
            return;
        }

        int mid = (start + end) / 2;

        //왼쪽 노드
        update(left, right, node * 2, start, mid, num);

        //오른쪽 노드
        update(left, right, node * 2 + 1, mid + 1, end, num);

        //부모 노드 구간 합 수정
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static long query(int left, int right, int node, int start, int end) {

        propagation(node, start, end);

        //타겟 구간에 포함되지 않는 노드
        if (start > right || end < left) return 0;

        //타겟 구간에 포함되는 노드
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        return query(left, right, node * 2, start, mid)/*왼쪽 노드*/ +
                query(left, right, node * 2 + 1, mid + 1, end)/*오른쪽 노드*/;
    }

    private static void propagation(int node, int start, int end) {

        if (lazy[node] != 0) {

            //전파된 값과 구간의 길이만큼 노드 수정
            tree[node] += lazy[node] * (end - start + 1);

            //리프 노드가 아닌 경우 자식 노드한테 전파
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            //전파값 초기화
            lazy[node] = 0;
        }
    }
}