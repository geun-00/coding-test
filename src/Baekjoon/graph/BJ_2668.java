package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * <a href = "https://www.acmicpc.net/problem/2668">백준 2668번 - 그래프 탐색 : 숫자고르기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2668%EB%B2%88-%EC%88%AB%EC%9E%90%EA%B3%A0%EB%A5%B4%EA%B8%B0">velog</a>
 * @since 2024-11-26
 */
public class BJ_2668 {

    static int[] arr;
    static boolean[] visit;
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new int[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;
        }

        for (int i = 0; i < n; i++) {
            visit[i] = true;
            dfs(i, i);
            visit[i] = false;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(ans.size()).append("\n");

        for (int num : ans) {
            sb.append(num).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int now, int target) {

        if (!visit[arr[now]]) {
            visit[arr[now]] = true;
            dfs(arr[now], target);
        }

        //재방문한 수라면 처음으로 돌아와서 사이클이 발생한 것인지 확인
        if (arr[now] == target) {
            ans.add(target + 1);
        }

        visit[arr[now]] = false;
    }
}