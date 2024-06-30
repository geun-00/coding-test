package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1068">백준 1068번 - 트리 : 트리</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1068%EB%B2%88-%ED%8A%B8%EB%A6%AC">velog</a>
 * @since 2024-06-29
 */
public class BJ_1068 {

    static ArrayList<Integer>[] tree;
    static boolean[] visit;
    static int delNode;
    static int leaf = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int root = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == -1) {
                root = i;
            } else {
                tree[num].add(i);
                tree[i].add(num);
            }
        }

        delNode = Integer.parseInt(br.readLine()); //지울 노드의 번호

        if (root == delNode) { //루트 노드를 제거 한다면, 남는 노드는 0개다.
            System.out.println(0);
        } else {
            dfs(root);
            System.out.println(leaf);
        }
    }

    private static void dfs(int parent) {
        visit[parent] = true;

        int child = 0;

        for (int next : tree[parent]) { //현재 노드의 자식 노드 탐색
            if (!visit[next] && next != delNode) {
                child++;
                dfs(next);
            }
        }

        if (child == 0) { //자식 노드가 0개라면 자신은 리프 노드다.
            leaf++;
        }
    }
}
