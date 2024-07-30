package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/20166">백준 20166번 - 해시 : 문자열 지옥에 빠진 호석</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-20166%EB%B2%88-%EB%AC%B8%EC%9E%90%EC%97%B4-%EC%A7%80%EC%98%A5%EC%97%90-%EB%B9%A0%EC%A7%84-%ED%98%B8%EC%84%9D">velog</a>
 * @since 2024-07-29
 */
public class BJ_20166 {

    static HashMap<String, Integer> map = new HashMap<>();
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    static int n, m;
    static char[][] word;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        word = new char[n][m];

        for (int i = 0; i < n; i++) {
            word[i] = br.readLine().toCharArray();
        }

        //모든 곳에서 시작
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, 1, word[i][j] + "");
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < k; i++) {
            String s = br.readLine();
            sb.append(map.getOrDefault(s, 0)).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int x, int y, int len, String now) {

        map.put(now, map.getOrDefault(now, 0) + 1);

        if (len == 5) {
            return;
        }

        //대각선을 포함하여 환형 이동
        for (int i = 0; i < 8; i++) {
            int nx = (x + dx[i] + n) % n;
            int ny = (y + dy[i] + m) % m;

            dfs(nx, ny, len + 1, now + word[nx][ny]);
        }
    }
}
