package Baekjoon.dac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16974">백준 16974번 - 분할 정복 : 레벨 햄버거</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16974%EB%B2%88-%EB%A0%88%EB%B2%A8-%ED%96%84%EB%B2%84%EA%B1%B0">velog</a>
 * @since 2024-12-15
 */
public class BJ_16974 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        long[] layer = new long[51];
        long[] patties = new long[51];

        layer[0] = 1;
        patties[0] = 1;

        for (int i = 1; i <= n; i++) {
            layer[i] = (layer[i - 1] * 2) + 3;
            patties[i] = (patties[i - 1] * 2) + 1;
        }

        System.out.println(solve(n, x, layer, patties));
    }

    private static long solve(int level, long x, long[] layer, long[] patties) {

        //레벨-0 버거인 경우, 무조건 패티
        if (level == 0) {
            return 1;
        }

        long prevLayer = layer[level - 1];

        //첫번째에 있는 번만 먹은 경우
        if (x == 1) {
            return 0;
        }
        //햄버거번(B) + 이전 레이어
        else if (x <= 1 + prevLayer) {
            return solve(level - 1, x - 1, layer, patties);
        }
        //햄버거번(B) + 이전 레이어 + 중앙 패티(P)
        else if (x == 1 + prevLayer + 1) {
            return patties[level - 1] + 1;
        }
        //중간 이후
        else {
            return patties[level - 1] + 1 + solve(level - 1, x - 1 - prevLayer - 1, layer, patties);
        }
    }
}
