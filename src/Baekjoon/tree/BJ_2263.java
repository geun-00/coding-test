package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2263">백준 2263번 - 트리 : 트리의 순회</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2263%EB%B2%88-%ED%8A%B8%EB%A6%AC%EC%9D%98-%EC%88%9C%ED%9A%8C">velog</a>
 *
 * @since 2024-07-19
 */
public class BJ_2263 {

    static int[] inOrder;
    static int[] postOrder;
    static int[] preOrder;
    static int index = 0;
    static HashMap<Integer, Integer> inOrderMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        inOrder = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            inOrderMap.put(inOrder[i], i);
        }

        postOrder = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        preOrder = new int[n];
        solve(0, n - 1, 0, n - 1);

        StringBuilder sb = new StringBuilder();

        for (int i : preOrder) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }

    /**
     * @param ins   inOrder 범위 시작 인덱스
     * @param ine   inOrder 범위 마지막 인덱스
     * @param posts postOrder 범위 시작 인덱스
     * @param poste postOrder 범위 마지막 인덱스
     */
    private static void solve(int ins, int ine, int posts, int poste) {

        if (ins > ine || posts > poste) {
            return;
        }

        //포스트오더의 마지막은 현재 서브트리의 루트
        //프리오더는 루트부터 탐색한다.
        int rootValue = postOrder[poste]; //현재 서브트리의 루트 노드
        preOrder[index++] = rootValue;

        //인오더에서 루트 노드의 위치 구하기
        int rootIdx = inOrderMap.get(rootValue);

        //왼쪽 서브트리의 크기
        int leftSize = rootIdx - ins;

        //왼쪽 서브트리
        solve(ins, rootIdx - 1, posts, posts + leftSize - 1);

        //오른쪽 서브트리
        solve(rootIdx + 1, ine, posts + leftSize, poste - 1);
    }
}
