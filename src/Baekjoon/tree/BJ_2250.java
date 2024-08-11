package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2250">백준 2250번 - 트리 : 트리의 높이와 너비</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2250%EB%B2%88-%ED%8A%B8%EB%A6%AC%EC%9D%98-%EB%86%92%EC%9D%B4%EC%99%80-%EB%84%88%EB%B9%84">velog</a>
 *
 * @since 2024-08-11
 */
public class BJ_2250 {

    static Node[] tree;
    static Level[] level;
    static int order = 1; //중위순회 순서

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new Node[n + 1];

        //최대 레벨을 알면 그 레벨까지만 하면 되는데 일단 알 수 없으므로 노드 개수만큼 한다.(노드 개수는 넘어갈 수 없음)
        //이진 트리라 노드 개수로 구하는 계산식이 있을 것 같은데 모르겠다.
        level = new Level[n + 1];

        //각 레벨의 양쪽 끝에 위치한 열 번호 초기화
        for (int i = 1; i <= n; i++) {
            level[i] = new Level(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        boolean[] child = new boolean[n + 1];

        for (int i = 0; i < n; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            tree[node] = new Node(left, right);

            if (left != -1) {
                child[left] = true;
            }
            if (right != -1) {
                child[right] = true;
            }
        }

        //아무도 자식으로 두고있지 않은 노드가 루트노드
        int root = 0;
        for (int i = 1; i <= n; i++) {
            if (!child[i]) {
                root = i;
                break;
            }
        }

        inOrder(root, 1);

        int width = Integer.MIN_VALUE; //가장 넓은 너비
        int lv = 0; //가장 넓은 너비를 갖는 레벨

        for (int i = 1; i <= n; i++) {

            //레벨 끝까지 탐색한 경우 불필요한 탐색 방지
            if (level[i].min == Integer.MAX_VALUE) {
                break;
            }

            int temp = level[i].max - level[i].min + 1;

            //가장 넓은 너비가 두 개 이상인 경우 번호가 작은 레벨을 출력해야 하므로 '>' 조건
            if (temp > width) {
                width = temp;
                lv = i;
            }
        }

        System.out.println(lv + " " + width);
    }

    private static void inOrder(int node, int depth) {

        if (node == -1) { //리프 노드
            return;
        }

        //왼쪽 노드부터 탐색
        inOrder(tree[node].left, depth + 1);

        //해당 레벨의 가장 왼쪽 노드와 가장 오른쪽 노드 구하기
        level[depth].min = Math.min(level[depth].min, order);
        level[depth].max = Math.max(level[depth].max, order);
        order++; //후위 순회 탐색 순서 증가

        //오른쪽 노드 탐색
        inOrder(tree[node].right, depth + 1);
    }

    static class Node {
        int left, right; //왼쪽 자식 노드, 오른쪽 자식 노드

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static class Level {
        int min, max; //해당 레벨의 가장 왼쪽과 가장 오른쪽에 위치한 노드의 열 번호

        public Level(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
