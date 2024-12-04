package Baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/9489">백준 9489번 - 트리 : 사촌</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-9489%EB%B2%88-%EC%82%AC%EC%B4%8C">velog</a>
 * @since 2024-11-26
 */
public class BJ_9489 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (n == 0 && k == 0) {
                break;
            }

            int target = 0;
            int[] arr = new int[n];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());

                if (arr[i] == k) {
                    target = i;
                }
            }

            //각 노드의 부모 인덱스 배열
            int[] parent = new int[n];

            //현재 부모 인덱스
            int p_idx = -1;
            parent[0] = p_idx;

            for (int i = 1; i < n; i++) {

                //연속된 값이 아니면 부모 인덱스 변경
                if (arr[i] != arr[i - 1] + 1) {
                    p_idx++;
                }

                parent[i] = p_idx;
            }

            int ans = 0;

            for (int i = 1; i < n; i++) {

                if (parent[i] != parent[target] && parent[parent[i]] == parent[parent[target]]) {
                    ans++;
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);

    }
}