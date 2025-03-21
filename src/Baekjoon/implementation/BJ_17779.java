package Baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/17779">백준 17779번 - 구현 : 게리맨더링 2</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-17779%EB%B2%88-%EA%B2%8C%EB%A6%AC%EB%A7%A8%EB%8D%94%EB%A7%812">velog</a>
 * @since 2025-03-05
 */
public class BJ_17779 {

    static int n;
    static int[][] arr;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int d1 = 1; y - d1 >= 0; d1++) {
                    for (int d2 = 1; x + d1 + d2 < n && y + d2 < n; d2++) {
                        solve(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static void solve(int x, int y, int d1, int d2) {
        boolean[][] mark = new boolean[n][n];
        int five = 0;

        for (int i = 0; i <= d1; i++) {
            mark[x + i][y - i] = true;          //1번 경계선
            mark[x + d2 + i][y + d2 - i] = true;//4번 경계선
        }

        for (int i = 0; i <= d2; i++) {
            mark[x + i][y + i] = true;          //2번 경계선
            mark[x + d1 + i][y - d1 + i] = true;//3번 경계선
        }

        //5번 선거구 내부
        for (int i = x + 1; i < x + d1 + d2; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if (mark[i][j]) flag = !flag;
                if (flag) mark[i][j] = true;
            }
        }

        int one = 0, two = 0, three = 0, four = 0;

        //1번 선거구
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (!mark[i][j]) one += arr[i][j];
            }
        }

        //2번 선거구
        for (int i = 0; i <= x + d2; i++) {
            for (int j = y + 1; j < n; j++) {
                if (!mark[i][j]) two += arr[i][j];
            }
        }

        //3번 선거구
        for (int i = x + d1; i < n; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (!mark[i][j]) three += arr[i][j];
            }
        }

        //4번 선거구
        for (int i = x + d2 + 1; i < n; i++) {
            for (int j = y - d1 + d2; j < n; j++) {
                if (!mark[i][j]) four += arr[i][j];
            }
        }

        //5번 선거구
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mark[i][j]) five += arr[i][j];
            }
        }

        int min = Math.min(one, Math.min(two, Math.min(three, Math.min(four, five))));
        int max = Math.max(one, Math.max(two, Math.max(three, Math.max(four, five))));

        ans = Math.min(ans, (max - min));
    }
}
