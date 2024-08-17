package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2109">백준 2109번 - 그리디 : 순회강연</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2109%EB%B2%88-%EC%88%9C%ED%9A%8C%EA%B0%95%EC%97%B0">velog</a>
 * @since 2024-08-13
 */
public class BJ_2109 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            arr[i][0] = d;
            arr[i][1] = p;
        }

        //일수 오름차순, 일수가 같으면 강연료 내림차순 정렬
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        //큐의 사이즈 = 진행한 강연의 개수
        PriorityQueue<Integer> qu = new PriorityQueue<>();

        for (int[] lecture : arr) {

            int d = lecture[0];
            int p = lecture[1];

            qu.offer(p);

            //강연은 하루에 한 개만 할 수 있다.
            //강연의 개수가 일수를 넘어가면 가장 적은 강연료의 강연을 제거한다.
            if (qu.size() > d) {
                qu.poll();
            }
        }

        int sum = 0;

        while (!qu.isEmpty()) {
            sum += qu.poll();
        }

        System.out.println(sum);
    }
}