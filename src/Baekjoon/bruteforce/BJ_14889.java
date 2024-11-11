package Baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/14889">백준 14889번 - 브루트포스 : 스타트와 링크</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14889%EB%B2%88-%EC%8A%A4%ED%83%80%ED%8A%B8%EC%99%80-%EB%A7%81%ED%81%AC">velog</a>
 * @since 2024-11-09
 */
public class BJ_14889 {

    static int n;
    static int[][] arr;
    static int[] team;
    static int[] startTeam;
    static int[] linkTeam;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        team = new int[n];
        startTeam = new int[n / 2];
        linkTeam = new int[n / 2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        select(0, 0);

        System.out.println(min);
    }

    private static void select(int start, int depth) {
        if (depth >= n / 2) {

            min = Math.min(min, getDiff());

            if (min == 0) {
                System.out.println(0);
                System.exit(0);
            }

            return;
        }

        for (int i = start; i < n; i++) {
            team[i] = 1;
            select(i + 1, depth + 1);
            team[i] = 0;
        }
    }

    private static int getDiff() {

        int startIdx = 0;
        int linkIdx = 0;

        for (int i = 0; i < n; i++) {
            if (team[i] == 1)
                startTeam[startIdx++] = i;
            else
                linkTeam[linkIdx++] = i;
        }

        int start = 0;
        int link = 0;

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                if (i != j) {
                    start += arr[startTeam[i]][startTeam[j]];
                    link += arr[linkTeam[i]][linkTeam[j]];
                }
            }
        }

        return Math.abs(start - link);
    }
}