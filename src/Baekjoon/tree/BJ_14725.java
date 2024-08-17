package Baekjoon.tree;

/**
 * <a href = "https://www.acmicpc.net/problem/14725">백준 14725번 - 트리 : 개미굴</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14725%EB%B2%88-%EA%B0%9C%EB%AF%B8%EA%B5%B4">velog</a>
 * @since 2024-08-14
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BJ_14725 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Node root = new Node(""); //루트 노드(공백)

        for (int i = 0; i < n; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());

            Node cur = root; //루트에서 시작하여 점차 밑으로 이동

            for (int j = 0; j < k; j++) {

                String food = st.nextToken();

                //자식으로 추가
                if (!cur.children.containsKey(food)) { //log n
                    cur.children.put(food, new Node(food)); //log n
                }

                //자식으로 이동
                cur = cur.children.get(food); //log n
            }
        }

        printResult(root, 0);
        System.out.println(sb);
    }

    private static void printResult(Node node, int depth) {

        for (Node child : node.children.values()) {

            for (int i = 0; i < depth; i++) {
                sb.append("--");
            }

            sb.append(child.name).append("\n");

            printResult(child, depth + 1);
        }
    }

    static class Node {
        String name;
        TreeMap<String, Node> children; //key=자식의 이름, value=자식의 자식 노드 정보

        public Node(String name) {
            this.name = name;
            this.children = new TreeMap<>();
        }
    }
}