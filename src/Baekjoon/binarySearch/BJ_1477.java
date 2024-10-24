package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1477">백준 1477번 - 이분 탐색 : 휴게소 세우기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1477%EB%B2%88-%ED%9C%B4%EA%B2%8C%EC%86%8C-%EC%84%B8%EC%9A%B0%EA%B8%B0">velog</a>
 * @since 2024-10-23
 */
public class BJ_1477 {

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        arr = new int[n + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[n + 1] = l;

        Arrays.sort(arr);

        int left = 1;
        int right = l;

        int result = 0;

        while (left <= right) {

            int mid = (left + right) / 2;

            //최대 m개까지 세울 수 있으면 현재 mid를 정답 변수에 담아두고,
            //더 적은 거리를 구할 수 있는지 탐색하기 위해 범위 좁히기
            if (check(mid)) {
                result = mid;
                right = mid - 1;
            }
            //m개 넘게 세워야 한다면 더 넓은 거리를 탐색하기 위해 범위 좁히기
            else {
                left = mid + 1;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();

        bw.close();
        br.close();
    }

    private static boolean check(int val) {

        int count = 0;

        for (int i = 1; i <= n + 1; i++) {
            int dist = arr[i] - arr[i - 1]; //두 휴게소의 간격

            //두 휴게소 사이에 세울 수 있는 휴게소 계산
            //처음과 끝을 제외한 정확히 사이에 세울 수 있는 개수를 구하기 위해 1을 빼고 나누기
            count += (dist - 1) / val;
        }

        return count <= m;
    }
}
