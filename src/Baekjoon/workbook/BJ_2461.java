package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2461">백준 2461번 - 대표 선수</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2461%EB%B2%88-%EB%8C%80%ED%91%9C-%EC%84%A0%EC%88%98">velog</a>
 * @since 2025-04-23
 */
public class BJ_2461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        // 1
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr[i]);
        }

        // 2
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.value));
        int max = 0;
        for (int i = 0; i < n; i++) {
            pq.offer(new Node(arr[i][0], i, 0));
            max = Math.max(max, arr[i][0]);
        }

        // 3
        int ans = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int value = node.value; //능력치
            int num = node.num;     //학급 번호
            int index = node.index; //학생 순서

            ans = Math.min(ans, max - value);

            if (index + 1 == m) break;

            max = Math.max(max, arr[num][index + 1]);
            pq.offer(new Node(arr[num][index + 1], num, index + 1));
        }

        System.out.println(ans);
    }

    static class Node {
        int value, num, index;

        public Node(int value, int num, int index) {
            this.value = value;
            this.num = num;
            this.index = index;
        }
    }
}
