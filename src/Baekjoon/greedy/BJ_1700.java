package Baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1700">백준 1700번 - 그리디 : 멀티탭 스케줄링</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1700%EB%B2%88-%EB%A9%80%ED%8B%B0%ED%83%AD-%EC%8A%A4%EC%BC%80%EC%A4%84%EB%A7%81">velog</a>
 * @since 2024-07-25
 */
public class BJ_1700 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] used = new int[k];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            used[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0; //플러그를 빼는 횟수

        HashSet<Integer> multiTap = new HashSet<>(); //멀티탭

        for (int i = 0; i < k; i++) {

            //멀티탭에 꽂혀 있지 않고, 멀티탭이 가득 찬 경우
            //멀티탭에 꽂혀있는 플러그 중 하나를 빼야 한다.
            if (!multiTap.contains(used[i]) && multiTap.size() >= n) {

                //멀티탭에 꽂혀있는 플러그 중 나중에 또 써야 할 플러그 목록(순서 중요)
                ArrayList<Integer> reuse = new ArrayList<>();

                //멀티탭에 꽂혀있는 플러그 중 나중에 쓰이지 않을 플러그 목록
                HashSet<Integer> remain = new HashSet<>(multiTap);

                //나중에 써야 할 전기 용품 목록 탐색
                for (int j = i; j < k; j++) {

                    //나중에 써야 할 플러그가 이미 멀티탭에 꽂혀 있다
                    if (multiTap.contains(used[j]) && !reuse.contains(used[j])) {

                        //나중에 또 써야 할 플러그 목록에 추가
                        reuse.add(used[j]);

                        //나중에 쓰이지 않을 플러그 목록만 남기기 위해 제거
                        remain.remove(used[j]);
                    }
                }

                //만약 나중에 또 써야 할 플러그 수가 멀티탭 구멍보다 많다면 가장 마지막에 쓰일 플러그를 제거한다.
                if (reuse.size() >= n) {
                    multiTap.remove(reuse.get(reuse.size() - 1));

                //그렇지 않다면 나중에 쓰이지 않을 플러그들 중 아무거나 빼도 상관없다.
                } else if (!remain.isEmpty()) {
                    multiTap.remove(remain.iterator().next());
                }

                count++;
            }

            multiTap.add(used[i]); //멀티탭에 플러그 꽂기
        }

        System.out.println(count);
    }
}