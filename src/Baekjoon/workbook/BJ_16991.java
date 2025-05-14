package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16991 {

    static double[][] dp;
    static int[][] cities;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new double[n][1 << n];
        cities = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cities[i][0] = Integer.parseInt(st.nextToken());
            cities[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(0, 1));
    }

    private static double solve(int city, int visit) {
        if (visit == (1 << n) - 1) {
            return getCost(cities[city][0], cities[city][1], cities[0][0], cities[0][1]);
        }

        if (dp[city][visit] != 0) {
            return dp[city][visit];
        }

        double minCost = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if ((visit & (1 << i)) == 0) {
                double cost = getCost(cities[city][0], cities[city][1], cities[i][0], cities[i][1]);
                minCost = Math.min(minCost, solve(i, (visit | (1 << i))) + cost);
            }
        }

        return dp[city][visit] = minCost;
    }

    private static double getCost(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }
}
