package Baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/3079">백준 3079번 - 이분 탐색 : 입국심사</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-3079%EB%B2%88-%EC%9E%85%EA%B5%AD%EC%8B%AC%EC%82%AC">velog</a>
 * @since 2024-09-25
 */
public class BJ_3079 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //심사대 수
        int m = Integer.parseInt(st.nextToken()); //사람 수

        int[] times = new int[n]; //각 심사대에서 심사 시간

        int max = 0; //최대 심사 시간

        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(br.readLine());
            times[i] = t;
            max = Math.max(max, t);
        }

        long s = 1;
        long e = (long) max * m;

        while (s <= e) {

            long mid = (s + e) / 2;
            long sum = 0;

            //현재 중간값으로 심사할 수 있는 사람 수 계산
            for (int time : times) {
                sum += mid / time;
                if (sum >= m) {
                    break;
                }
            }

            if (sum >= m) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        System.out.println(s);
    }
}
