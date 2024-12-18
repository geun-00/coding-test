package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <a href = "https://www.acmicpc.net/problem/2660">백준 2660번 - 다익스트라 : 회장뽑기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2660%EB%B2%88-%ED%9A%8C%EC%9E%A5%EB%BD%91%EA%B8%B0">velog</a>
 * @since 2024-12-13
 */
public class BJ_2660 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], 10000);
            dist[i][i] = 0;
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) {
                break;
            }

            a--;
            b--;

            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                }
            }
        }

        //각 노드의 가장 먼 노드와의 거리 중 최솟값 찾기
        int min = Arrays.stream(dist)
                .mapToInt(row -> Arrays.stream(row).max().orElse(0))
                .min()
                .orElse(10000);

        //최솟값에 해당하는 노드 찾기
        List<Integer> list = IntStream.range(0, n)
                .filter(i -> Arrays.stream(dist[i]).max().orElse(0) == min)
                .map(i -> i + 1)
                .boxed()
                .collect(Collectors.toList());

        System.out.println(min + " " + list.size());

        list.forEach(i -> System.out.print(i + " "));

/*
        int[] arr = new int[n];
        int min = 10000;

        for (int i = 0; i < n; i++) {
            int max = 0;

            for (int j = 0; j < n; j++) {
                max = Math.max(max, dist[i][j]);
            }

            arr[i] = max;
            min = Math.min(min, arr[i]);
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (arr[i] == min) {
                list.add(i + 1);
            }
        }

        System.out.println(min + " " + list.size());

        for (int i : list) {
            System.out.print(i + " ");
        }
*/
    }
}