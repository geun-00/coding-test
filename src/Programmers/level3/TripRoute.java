package Programmers.level3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/43164">프로그래머스 - Lv.3 : 여행경로</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%97%AC%ED%96%89%EA%B2%BD%EB%A1%9C">velog</a>
 * @since 2024-08-02
 */
public class TripRoute {

    static boolean[] visit;
    static ArrayList<String> routes;

    static HashMap<String, PriorityQueue<String>> graph;
    static Deque<String> stack;

    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution(new String[][]{
                {"ICN", "JFK"},
                {"HND", "IAD"},
                {"JFK", "HND"}
        })));
        System.out.println(Arrays.toString(solution(new String[][]{
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "ICN"},
                {"ATL", "SFO"}
        })));
    }

    private static String[] solution(String[][] tickets) {

        stack = new LinkedList<>();
        graph = new HashMap<>();

        for (String[] ticket : tickets) {
            graph.putIfAbsent(ticket[0], new PriorityQueue<>());
            graph.get(ticket[0]).offer(ticket[1]);
        }

        dfs("ICN");

        return stack.toArray(new String[0]);

/*
        visit = new boolean[tickets.length];
        routes = new ArrayList<>();

        dfs("ICN", "ICN", 0, tickets);

        routes.sort(null);

        return routes.get(0).split(" ");
*/
    }

    public static void dfs(String now) {
        while (graph.containsKey(now) && !graph.get(now).isEmpty()) {
            dfs(graph.get(now).poll());
        }
        stack.push(now);
    }

    private static void dfs(String now, String path, int depth, String[][] tickets) {
        if (depth == tickets.length) {
            routes.add(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visit[i] && tickets[i][0].equals(now)) {
                visit[i] = true;
                dfs(tickets[i][1], path + " " + tickets[i][1], depth + 1, tickets);
                visit[i] = false;
            }
        }
    }
}
