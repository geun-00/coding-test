package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/4256">백준 4256번 - 트리 : 트리</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-4256%EB%B2%88-%ED%8A%B8%EB%A6%AC">velog</a>
 * @since 2024-09-15
 */
public class BJ_4256 {

    static int[] preOrder;
    static int[] inOrder;

    static StringBuilder sb = new StringBuilder();
    static HashMap<Integer, Integer> inOrderMap;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            preOrder = new int[n];
            inOrder = new int[n];

            inOrderMap = new HashMap<>();

            //전위 순회
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preOrder[i] = Integer.parseInt(st.nextToken());
            }

            //중위 순회
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inOrder[i] = Integer.parseInt(st.nextToken());
                inOrderMap.put(inOrder[i], i);
            }

            solve(0, n - 1, 0, n - 1);

            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void solve(int pre_s, int pre_e, int in_s, int in_e) {

        if (pre_s > pre_e || in_s > in_e) {
            return;
        }

        //전위 순회의 첫번째 노드 = 루트 노드
        int root = preOrder[pre_s];

        //중위 순회에서 루트 노드 위치
        int rootIndex = inOrderMap.get(root);

        //왼쪽 서브트리의 크기
        int leftSize = rootIndex - in_s;

        //왼쪽 서브트리
        solve(pre_s + 1, pre_s + leftSize, in_s, rootIndex - 1);

        //오른쪽 서브트리
        solve(pre_s + leftSize + 1, pre_e, rootIndex + 1, in_e);

        /**
         * 위치에 따라 순회 방식이 달라진다.
         * 1. 왼쪽 서브트리 재귀호출 위에 있을 경우 : 전위 순회
         * 2. 왼쪽과 오른쪽 서브트리 재귀호출 사이에 있을 경우 : 중위 순회
         * 3. 가장 마지막에 있을 경우 : 후위 순회
         */
        sb.append(root).append(" ");
    }
}
