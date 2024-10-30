package Baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2042">백준 2042번 - 세그먼트 트리 : 구간 합 구하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2042%EB%B2%88-%EA%B5%AC%EA%B0%84-%ED%95%A9-%EA%B5%AC%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-
 */
public class BJ_2042 {

    static long[] tree;

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int size = 1;
        while ((1 << size) < n) {
            size++;
        }

        //리프 노드 시작 인덱스
        int leafNodeIdx = 1 << size;

        tree = new long[leafNodeIdx * 2];

        for (int i = leafNodeIdx; i < leafNodeIdx + n; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        //초기 구간 합 저장
        int idx = tree.length - 1;
        while (idx > 0) {
            tree[idx / 2] += tree[idx];
            idx--;
        }

        for (int i = 0; i < m + k; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //세그먼트 트리 인덱스로 변환
            int start = b + leafNodeIdx - 1;

            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                changeValue(start, c);
            } else {
                int c = Integer.parseInt(st.nextToken());

                //세그먼트 트리 인덱스로 변환
                int end = c + leafNodeIdx - 1;

                sb.append(getSum(start, end)).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void changeValue(int startIdx, long value) {

        long diff = value - tree[startIdx];

        //부모 노드로 이동하면서 차이만큼 구간 합 변경
        while (startIdx > 0) {
            tree[startIdx] += diff;
            startIdx /= 2;
        }
    }

    private static long getSum(int start, int end) {

        long sum = 0;

        while (start <= end) {

            if (start % 2 == 1) {
                sum += tree[start];
                start++;
            }

            if (end % 2 == 0) {
                sum += tree[end];
                end--;
            }

            //부모 노드로 이동
            start /= 2;
            end /= 2;
        }

        return sum;
    }
}
