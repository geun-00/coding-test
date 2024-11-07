package Baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/10868">백준 10868번 - 세그먼트 트리 : 최솟값</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-10868%EB%B2%88-%EC%B5%9C%EC%86%9F%EA%B0%92">velog</a>
 * @since 2024-11-05
 */
public class BJ_10868 {

    static int[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //세그먼트 트리의 최대 레벨
        int level = 1;
        while ((1 << level) < n) {
            level++;
        }

        //N개 정수가 저장될 리프노드 시작 인덱스
        int leafNodeIdx = 1 << level;

        tree = new int[leafNodeIdx * 2];

        Arrays.fill(tree, Integer.MAX_VALUE);

        for (int i = leafNodeIdx; i < leafNodeIdx + n; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }

        int idx = tree.length - 1;

        while (idx > 0) {
            tree[idx / 2] = Math.min(tree[idx / 2], tree[idx]);
            idx--;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //세그먼트 트리 인덱스로 변환
            a = a + leafNodeIdx - 1;
            b = b + leafNodeIdx - 1;

            sb.append(getMin(a, b)).append("\n");
        }

        System.out.print(sb);
    }

    private static int getMin(int s, int e) {

        int min = Integer.MAX_VALUE;

        while (s <= e) {
            if (s % 2 == 1) {
                min = Math.min(min, tree[s]);
                s++;
            }
            if (e % 2 == 0) {
                min = Math.min(min, tree[e]);
                e--;
            }
            s /= 2;
            e /= 2;
        }

        return min;
    }
}