package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2831">백준 2831번 - 댄스 파티</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2831%EB%B2%88-%EB%8C%84%EC%8A%A4-%ED%8C%8C%ED%8B%B0">velog</a>
 * @since 2025-04-11
 */
public class BJ_2831 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> lowerM = new ArrayList<>();   //키가 더 작은 여자를 원하는 남자
        List<Integer> higherM = new ArrayList<>();  //키가 더 큰 여자를 원하는 남자

        // 1 (남자)
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());
            
            if (height < 0) lowerM.add(Math.abs(height));
            else higherM.add(height);
        }
        
        st = new StringTokenizer(br.readLine());
        List<Integer> lowerW = new ArrayList<>();   //키가 더 작은 남자를 원하는 여자
        List<Integer> higherW = new ArrayList<>();  //키가 더 큰 남자를 원하는 여자

        // 1 (여자)
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());

            if (height < 0) lowerW.add(Math.abs(height));
            else higherW.add(height);
        }

        // 1
        Collections.sort(lowerM);
        Collections.sort(higherM);
        Collections.sort(lowerW);
        Collections.sort(higherW);

        // 2
        System.out.println(solve(lowerM, higherW) + solve(lowerW, higherM));
    }

    // 2
    private static int solve(List<Integer> h1, List<Integer> h2) {
        int left = 0 , right = 0;
        int ans = 0;
        while (left < h1.size() && right < h2.size()) {
            if (h1.get(left) > h2.get(right)) {
                ans++;
                left++;
                right++;
            } else {
                left++;
            }
        }
        return ans;
    }
}
