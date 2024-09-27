package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1135">백준 1135번 - 트리 : 뉴스 전하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1135%EB%B2%88-%EB%89%B4%EC%8A%A4-%EC%A0%84%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-09-24
 */
public class BJ_1135 {

    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int boss = Integer.parseInt(st.nextToken());
            if (boss != -1) {
                tree[boss].add(i);
            }
        }

        System.out.println(solve(0));
    }

    private static int solve(int node) {

        //부하가 없으면, 0분 걸림
        if (tree[node].isEmpty()) {
            return 0;
        }

        //각 부하의 뉴스를 전달하는 데 걸리는 시간을 저장할 리스트
        ArrayList<Integer> times = new ArrayList<>();
        for (int child : tree[node]) {
            times.add(solve(child));
        }

        //내림차순으로 정렬 (오래 걸리는 부하부터 먼저 전화해야 전체 시간을 줄일 수 있음)
        times.sort(Collections.reverseOrder());

        //각 부하에게 뉴스를 전달하는데 걸리는 시간을 계산
        int maxTime = 0;
        for (int i = 0; i < times.size(); i++) {
            //전화 거는 1분 + 순서에 따라 i분 증가
            maxTime = Math.max(maxTime, times.get(i) + i + 1);
        }

        return maxTime;
    }
}
