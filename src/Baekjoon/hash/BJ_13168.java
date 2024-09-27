package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/13168">백준 13168번 - 해시 : 내일로 여행</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-13168%EB%B2%88-%EB%82%B4%EC%9D%BC%EB%A1%9C-%EC%97%AC%ED%96%89">velog</a>
 * @since 2024-09-26
 */
public class BJ_13168 {

    static final int INF = 100_000_000;

    static int[][] basicCost;
    static int[][] railroCost;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashMap<String, Integer> cityIdx = new HashMap<>();

        int n = Integer.parseInt(st.nextToken()); //도시의 수
        int r = Integer.parseInt(st.nextToken()); //내일로 티켓 가격

        String[] cities = br.readLine().split(" "); //n개의 도시
        for (int i = 0; i < n; i++) {
            cityIdx.put(cities[i], i);
        }

        int m = Integer.parseInt(br.readLine());
        String[] travels = br.readLine().split(" "); //여행할 m개의 도시
        int[] route = new int[m]; //여행할 m개의 도시 순서

        for (int i = 0; i < m; i++) {
            route[i] = cityIdx.get(travels[i]);
        }

        int k = Integer.parseInt(br.readLine()); //교통수단 수

        basicCost = new int[n][n]; //내일로 티켓을 사지 않는 경우
        railroCost = new int[n][n]; //내일로 티켓을 사는 경우

        for (int i = 0; i < n; i++) {
            Arrays.fill(basicCost[i], INF);
            Arrays.fill(railroCost[i], INF);
            basicCost[i][i] = 0;
            railroCost[i][i] = 0;
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            String type = st.nextToken();
            int s = cityIdx.get(st.nextToken());
            int e = cityIdx.get(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            int railro = cost;

            if (type.equals("Mugunghwa") || type.equals("ITX-Saemaeul") || type.equals("ITX-Cheongchun")) {
                railro = 0;
            }
            else if (type.equals("S-Train") || type.equals("V-Train")) {
//                railro /= 2; //오답
                railro = (int) Math.round(cost / 2.0); //주의, 반올림 처리 필요
            }

            basicCost[s][e] = Math.min(basicCost[s][e], cost);
            basicCost[e][s] = Math.min(basicCost[e][s], cost);

            railroCost[s][e] = Math.min(railroCost[s][e], railro);
            railroCost[e][s] = Math.min(railroCost[e][s], railro);
        }

        floydWarshall(basicCost, n);
        floydWarshall(railroCost, n);

        int basicTotalCost = calcTotalCost(basicCost, route, m);
        int railroTotalCost = calcTotalCost(railroCost, route, m) + r; //내일로 티켓 가격 포함

        if (basicTotalCost > railroTotalCost) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static int calcTotalCost(int[][] cost, int[] route, int m) {

        int total = 0;

        for (int i = 0; i < m - 1; i++) {
            total += cost[route[i]][route[i + 1]];
        }

        return total;
    }

    private static void floydWarshall(int[][] cost, int n) {

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    if (cost[s][e] > cost[s][k] + cost[k][e]) {
                        cost[s][e] = cost[s][k] + cost[k][e];
                    }
                }
            }
        }
    }
}
