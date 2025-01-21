package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/11509">백준 11509번 - 그리디 : 풍선 맞추기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-11509%EB%B2%88-%ED%92%8D%EC%84%A0-%EB%A7%9E%EC%B6%94%EA%B8%B0">velog</a>
 * @since 2025-01-07
 */
public class BJ_11509 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] arrows = new int[1_000_001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {

            //새로운 화살을 쓰지 않아도 되는 경우
            if (arrows[arr[i]] > 0) {
                arrows[arr[i]]--;
            }
            //새로운 화살을 써야 하는 경우
            else {
                ans++;
            }

            //현재 높이에서 풍선을 터트리고 새로운 화살이 필요 없을 때까지 이동
            while (i < n - 1 && arr[i] - 1 == arr[i + 1]) {
                i++;
            }

            //최대한 긴 범위의 풍선들을 터트리고 나서 화살이 위치할 높이
            arrows[arr[i] - 1]++;
        }

        System.out.println(ans);
    }
}
