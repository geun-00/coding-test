package Baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/5676">백준 5676번 - 세그먼트 트리 : 음주 코딩</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-5676%EB%B2%88-%EC%9D%8C%EC%A3%BC-%EC%BD%94%EB%94%A9">velog</a>
 * @since 2024-12-24
 */
public class BJ_5676 {

    static final char ZERO = '0';
    static final char POSITIVE = '+';
    static final char NEGATIVE = '-';

    static int[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;

        while ((input = br.readLine()) != null && !input.isEmpty()) {

            StringTokenizer st = new StringTokenizer(input);

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            tree = new int[4 * n];

            int[] arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            init(1, 0, n - 1, arr);

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());

                String command = st.nextToken();

                if ("C".equals(command)) {
                    int index = Integer.parseInt(st.nextToken()) - 1;
                    int value = Integer.parseInt(st.nextToken());

                    update(1, 0, n - 1, index, value);
                }
                else {
                    int start = Integer.parseInt(st.nextToken()) - 1;
                    int end = Integer.parseInt(st.nextToken()) - 1;

                    int result = query(1, 0, n - 1, start, end);
                    sb.append(getAns(result));
                }
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    /**
     * start ~ end 범위의 곱 연산 결과
     * @param node 세그먼트 트리의 현재 노드 번호
     * @param start 타겟 시작 범위
     * @param end 타겟 끝 범위
     * @param left 현재 노드의 시작 범위
     * @param right 현재 노드의 끝 범위
     * @return -1, 0, 1 중 하나
     */
    private static int query(int node, int start, int end, int left, int right) {

        if (left > end || right < start) {
            return 1;
        }

        if (left <= start && right >= end) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return query(node * 2, start, mid, left, right) *
                query(node * 2 + 1, mid + 1, end, left, right);
    }

    /**
     * index 값 변경
     * @param node 세그먼트 트리의 현재 노드 번호
     * @param left 현재 노드의 시작 범위
     * @param right 현재 노드의 끝 범위
     * @param index 타겟 인덱스
     * @param value 타겟 값
     */
    private static void update(int node, int left, int right, int index, int value) {

        if (index < left || index > right) {
            return;
        }

        if (left == right) {
            tree[node] = getSign(value);
            return;
        }

        int mid = (left + right) / 2;

        update(node * 2, left, mid, index, value);
        update(node * 2 + 1, mid + 1, right, index, value);

        tree[node] = tree[node * 2] * tree[node * 2 + 1];
    }

    private static void init(int node, int start, int end, int[] arr) {

        //리프 노드 도착
        if (start == end) {
            tree[node] = getSign(arr[start]);
            return;
        }

        int mid = (start + end) / 2;

        init(node * 2, start, mid, arr);            //왼쪽 자식 노드
        init(node * 2 + 1, mid + 1, end, arr); //오른쪽 자식 노드

        //부모 노드 계산
        tree[node] = tree[node * 2] * tree[node * 2 + 1];
    }

    private static int getSign(int value) {
        return Integer.compare(value, 0);
//        if (value > 0) return 1;
//        if (value < 0) return -1;
//        return 0;
    }

    private static char getAns(int result) {
        if (result > 0) return POSITIVE;
        if (result < 0) return NEGATIVE;
        return ZERO;
    }
}