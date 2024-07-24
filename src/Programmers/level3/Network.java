package Programmers.level3;

import java.util.ArrayList;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/43162">프로그래머스 - Lv.3 : 네트워크</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC">velog</a>
 * @since 2024-07-23
 */
public class Network {

    static ArrayList<Integer>[] graph;
    static boolean[] visit;

    public static void main(String[] args) {
        System.out.println(solution(3, new int[][]
                {
                        {1, 1, 0},
                        {1, 1, 0},
                        {0, 0, 1}
                }
        ));

        System.out.println(solution(3, new int[][]
                {
                        {1, 1, 0},
                        {1, 1, 1},
                        {0, 1, 1}
                }
        ));
    }

    private static int solution(int n, int[][] computers) {

        graph = new ArrayList[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (computers[i][j] == 1) {
                        graph[i].add(j);
                        graph[j].add(i);
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                count++;
                dfs(i);
            }
        }

        return count;
    }

    private static void dfs(int node) {
        visit[node] = true;

        for (int next : graph[node]) {
            if (!visit[next]) {
                dfs(next);
            }
        }
    }
}
