package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/12767">백준 12767번 - Ceiling Function</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-12767%EB%B2%88-Ceiling-Function">velog</a>
 *
 * @since 2024-11-01
 */
public class BJ_12767 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int root = Integer.parseInt(st.nextToken());
            Tree tree = new Tree(root, k); //가장 먼저 나오는 번호를 루트 노드로 하는 트리 생성

            for (int j = 0; j < k - 1; j++) {
                int child = Integer.parseInt(st.nextToken());
                tree.insert(child);
            }

            Arrays.sort(tree.nodeIndex);
            set.add(Arrays.toString(tree.nodeIndex));
        }

        System.out.println(set.size());
    }

    static class Node {

        int num;
        Node left, right;

        public Node(int num) {
            this.num = num;
        }
    }

    static class Tree {

        Node root;
        int[] nodeIndex;
        int idx;

        public Tree(int num, int size){
            this.root = new Node(num);
            this.nodeIndex = new int[size];
            this.nodeIndex[0] = 1;
            this.idx = 0;
        }

        public void insert(int num) {

            Node node = new Node(num); //삽입될 노드

            this.idx++;           //트리 인덱스 증가
            int childNum = 1;     //삽입될 노드의 인덱스
            Node parent = root;   //부모 노드

            while (true) {

                //부모 노드보다 값이 작아 왼쪽으로 갈 때
                if (parent.num > num) {

                    //왼쪽 자식 노드 인덱스 변환
                    childNum *= 2;

                    //왼쪽에 추가될 수 있을 때
                    if (parent.left == null) {
                        parent.left = node;
                        this.nodeIndex[this.idx] = childNum;
                        return;
                    }

                    //왼쪽 노드로 이동
                    parent = parent.left;
                }

                //부모 노드보다 값이 커 오른쪽으로 갈 때
                else if (parent.num < num) {

                    //오른쪽 자식 노드 인덱스 변환
                    childNum = childNum * 2 + 1;

                    //오른쪽에 추가될 수 있을 떄
                    if (parent.right == null) {
                        parent.right = node;
                        this.nodeIndex[this.idx] = childNum;
                        return;
                    }

                    //오른쪽 노드로 이동
                    parent = parent.right;
                }
            }
        }
    }
}
