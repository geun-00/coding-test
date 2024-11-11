package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1991">백준 1991번 - 트리 : 트리 순회</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1991%EB%B2%88-%ED%8A%B8%EB%A6%AC-%EC%88%9C%ED%9A%8C">velog</a>
 * @since 2024-11-09
 */
public class BJ_1991 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Node[] tree = new Node[26];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char node = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if (tree[node - 'A'] == null) {
                tree[node - 'A'] = new Node(node);
            }

            Node now = tree[node - 'A'];

            if (left != '.') {
                now.left = new Node(left);
                tree[left - 'A'] = now.left;
            }
            if (right != '.') {
                now.right = new Node(right);
                tree[right - 'A'] = now.right;
            }
        }

        preOrder(tree[0]);
        sb.append("\n");

        inOrder(tree[0]);
        sb.append("\n");

        postOrder(tree[0]);

        System.out.println(sb);
    }

    //전위 순회, root -> left -> right
    private static void preOrder(Node node) {
        if (node == null) {
            return;
        }
        sb.append(node.name);   //root
        preOrder(node.left);    //left
        preOrder(node.right);   //right
    }

    //중위 순회, left -> root -> right
    private static void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);     //left
        sb.append(node.name);   //root
        inOrder(node.right);    //right
    }

    //후위 순회, left -> right -> root
    private static void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);   //left
        postOrder(node.right);  //right
        sb.append(node.name);   //root
    }

    static class Node {
        char name;
        Node left, right;

        public Node(char name) {
            this.name = name;
        }
    }
}
