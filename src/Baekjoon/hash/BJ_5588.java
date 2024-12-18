package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/5588">백준 5588번 - 해시 : 별자리 찾기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-5588%EB%B2%88-%EB%B3%84%EC%9E%90%EB%A6%AC-%EC%B0%BE%EA%B8%B0">velog</a>
 * @since 2024-12-17
 */
public class BJ_5588 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());

        int[][] stars = new int[m][2];

        for (int i = 0; i < m; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars[i][0] = x;
            stars[i][1] = y;
        }

        Map<String, Integer> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int j = 0; j < m; j++) {
                int nx = x - stars[j][0];
                int ny = y - stars[j][1];

                String key = nx + "," + ny;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            if (entry.getValue() == m) {
                String[] arr = entry.getKey().split(",");
                System.out.println(arr[0] + " " + arr[1]);
                break;
            }
        }
    }
}