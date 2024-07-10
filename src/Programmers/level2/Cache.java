package Programmers.level2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/17680">프로그래머스 - Lv.2 : [1차] 캐시</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-1%EC%B0%A8-%EC%BA%90%EC%8B%9C">velog</a>
 * @since 2024-07-08
 */
public class Cache {
    public static void main(String[] args) {

        System.out.println(solution(3,
                new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        System.out.println(solution(3,
                new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
        System.out.println(solution(2,
                new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
        System.out.println(solution(5,
                new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
        System.out.println(solution(2,
                new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}));
        System.out.println(solution(0,
                new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));

    }

    private static int solution(int cacheSize, String[] cities) {

        if (cacheSize == 0) {
            return cities.length * 5;
        }

        Queue<String> qu = new ArrayDeque<>();

        int time = 0;

        for (String s : cities) {
            String city = s.toLowerCase();

            if (qu.remove(city)) { //캐시 히트
                qu.offer(city);
                time++;
            } else {               //캐시 미스
                if (qu.size() == cacheSize) {
                    qu.poll();
                }
                qu.offer(city);
                time += 5;
            }

        }

        return time;
    }
}
