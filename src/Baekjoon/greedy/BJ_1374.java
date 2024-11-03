package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1374">백준 1374번 - 그리디 : 강의실</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1374%EB%B2%88-%EA%B0%95%EC%9D%98%EC%8B%A4">velog</a>
 * @since 2024-10-30
 */
public class BJ_1374 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[num - 1][0] = start;
            arr[num - 1][1] = end;
        }

        //시작 시간 순 정렬
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<Integer> qu = new PriorityQueue<>();

        for (int[] c : arr) {

            //현재 강의 시작 전에 끝나는 강의가 있다면 해당 강의실을 사용하면 된다.
            if (!qu.isEmpty() && qu.peek() <= c[0]) {
                qu.poll();
            }

            //강의 종료 시간 저장
            qu.offer(c[1]);
        }

        System.out.println(qu.size());
    }
}
