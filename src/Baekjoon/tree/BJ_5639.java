package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/5639">백준 5639번 - 트리 : 이진 검색 트리</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-5639%EB%B2%88-%EC%9D%B4%EC%A7%84-%EA%B2%80%EC%83%89-%ED%8A%B8%EB%A6%AC">velog</a>
 * @since 2024-07-06
 */
public class BJ_5639 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int r = Integer.parseInt(br.readLine());

        Node root = new Node(r);

        while (true) {
            String s = br.readLine();

            if (s == null) {
                break;
            }

            root.link(Integer.parseInt(s));
        }

        postOrder(root);
        System.out.println(sb);
    }

    private static void postOrder(Node node) { //후위순회
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.root).append('\n');
    }

    static class Node {
        int root;
        Node left; //왼쪽 자식
        Node right; //오른쪽 자식

        public Node(int root) {
            this.root = root;
        }

        public void link(int num) {

            if (num < root) { //루트보다 값이 작으면 왼쪽으로
                if (left == null) {
                    left = new Node(num);
                } else {
                    left.link(num);
                }
            }
            else {          //루트보다 값이 크면 오른쪽으로
                if (right == null) {
                    right = new Node(num);
                } else {
                    right.link(num);
                }
            }
        }
    }
}
