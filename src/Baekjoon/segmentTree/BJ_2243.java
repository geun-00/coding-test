package Baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2243">백준 2243번 - 세그먼트 트리 : 사탕상자</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2243%EB%B2%88-%EC%82%AC%ED%83%95%EC%83%81%EC%9E%90">velog</a>
 *
 * @since 2025-05-28
 */
public class BJ_2243 {

    // 1
    static final int MAX_TASTE = 1_000_000;
    static int[] tree = new int[4 * MAX_TASTE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            // 2
            if (a == 1) {
                int b = Integer.parseInt(st.nextToken());
                int taste = query(1, 1, MAX_TASTE, b);
                sb.append(taste).append("\n");
            }
            else {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                update(1, 1, MAX_TASTE, b, c);
            }
        }

        System.out.print(sb);
    }

    /**
     * 3 - 사탕을 꺼내는 메서드
     * @param node 세그먼트 트리의 노드 번호
     * @param left 현재 노드 범위의 가장 작은 값
     * @param right 현재 노드 범위의 가장 큰 값
     * @param b 꺼낼 사탕의 순위
     * @return 사탕의 순위
     */
    private static int query(int node, int left, int right, int b) {
        //리프 노드 도착
        if (left == right) {
            update(1, 1, MAX_TASTE, left, -1);
            return left;
        }

        int mid = (left + right) / 2;

        //왼쪽 자식 범위 안에 순위가 있는 경우
        if (tree[node * 2] >= b) {
            return query(node * 2, left, mid, b);
        }

        //오른쪽 자식 범위 안에 순위가 있는 경우
        return query(node * 2 + 1, mid + 1, right, b - tree[node * 2]);
    }

    /**
     * 4 - 사탕을 넣거나 빼는 메서드
     * @param node 세그먼트 트리의 노드 번호
     * @param left 현재 노드 범위의 가장 작은 값
     * @param right 현재 노드 범위의 가장 큰 값
     * @param b 넣을 사탕의 맛
     * @param c 사탕을 넣거가 빼는 개수
     */
    private static void update(int node, int left, int right, int b, int c) {
        //범위를 벗어난 노드
        if (b < left || right < b) return;

        //리프 노드 도착
        if (left == right) {
            tree[node] += c;
            return;
        }

        //자식 노드 호출
        int mid = (left + right) / 2;
        update(node * 2, left, mid, b, c);
        update(node * 2 + 1, mid + 1, right, b, c);

        //부모 노드 계산
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}
