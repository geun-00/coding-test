package Programmers.level3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/49189">프로그래머스 - Lv.3 : 가장 먼 노드</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B0%80%EC%9E%A5-%EB%A8%BC-%EB%85%B8%EB%93%9C">velog</a>
 * @since 2024-08-02
 */
public class FarthestNode {
    static int[] level;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;

    public static void main(String[] args) {

        System.out.println(solution(6, new int[][]
                {
                        {3, 6},
                        {4, 3},
                        {3, 2},
                        {1, 3},
                        {1, 2},
                        {2, 4},
                        {5, 2},
                }));
    }

    private static int solution(int n, int[][] edge) {

        graph = new ArrayList[n + 1];
        visit = new boolean[n + 1];
        level = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int n1 = edge[i][0];
            int n2 = edge[i][1];

            graph[n1].add(n2);
            graph[n2].add(n1);
        }

        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(1, 0));
        visit[1] = true;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            level[now.num] = now.depth;

            for (int next : graph[now.num]) {
                if (!visit[next]) {
                    visit[next] = true;
                    qu.offer(new Node(next, now.depth + 1));
                }
            }
        }

        int maxLevel = Arrays.stream(level).max().getAsInt();

        return (int) Arrays.stream(level).filter(d -> d == maxLevel).count();
    }

    static class Node {
        int num, depth;

        public Node(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
}
