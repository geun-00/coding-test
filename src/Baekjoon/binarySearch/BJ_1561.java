package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1561">백준 1561번 - 이분 탐색 : 놀이 공원</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1561%EB%B2%88-%EB%86%80%EC%9D%B4-%EA%B3%B5%EC%9B%90">velog</a>
 * @since 2024-10-27
 */
public class BJ_1561 {

    static int[] time;
    static int max = 0;
    static long n;
    static int m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        time = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            time[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, time[i]);
        }

        //사람보다 놀이기구 수가 많으면 순서대로 타면 된다.
        if (n <= m) {
            System.out.println(n);
            return;
        }

        long minTime = binarySearch();
        long child = getChild(minTime - 1);

        long res = 0;

        for (int i = 0; i < m; i++) {
            if (minTime % time[i] == 0) {
                child++;
            }
            if (child == n) {
                res = i + 1;
                break;
            }
        }

        System.out.println(res);
    }

    private static long binarySearch() {

        long left = 0;
        long right = n * max;

        long ans = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            //현재 시간으로 탑승시킬 수 있는 아이들의 수 구하기
            long child = getChild(mid);

            //n명을 채우지 못하면 시간이 더 필요하므로 left 조정
            if (child < n) {
                left = mid + 1;
            }
            //최소 n명 이상 채울 수 있으면 mid 반환 값 저장
            //더 적은 시간을 보기 위해 right 조정
            else {
                ans = mid;
                right = mid - 1;
            }
        }

        return ans;
    }

    private static long getChild(long val) {
        long child = m;
        for (int i = 0; i < m; i++) {
            child += val / time[i];
        }
        return child;
    }
}
