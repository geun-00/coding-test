package Programmers.level3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/42627">프로그래머스 - Lv.3 : 디스크 컨트롤러</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%94%94%EC%8A%A4%ED%81%AC-%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC">velog</a>
 *
 * @since 2024-08-05
 */
public class DiskController {
    public static void main(String[] args) {

        System.out.println(solution(new int[][]{
                {0, 3},
                {1, 9},
                {2, 6},
        }));
    }

    private static int solution(int[][] jobs) {

        //작업을 요청 시간 순으로 정렬
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));

        //소요시간이 적은 작업부터 처리하는 작업큐
        PriorityQueue<int[]> jobQu = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        int jobNums = jobs.length; //작업의 수

        int curTime = 0; //작업이 끝나고 현재 시간
        int jobIdx = 0;  //작업 인덱스
        int total = 0;   //걸린 시간
        int process = 0; //처리한 작업의 수

        while (process < jobNums) {

            //요청 가능한 작업 작업큐 추가
            while (jobIdx < jobNums && jobs[jobIdx][0] <= curTime) {
                jobQu.offer(jobs[jobIdx++]);
            }

            //처리할 수 있는 작업이 있으면 작업 처리
            if (!jobQu.isEmpty()) {
                int[] job = jobQu.poll();
                total += job[1] + curTime - job[0];
                curTime += job[1];
                process++;
            } else {
                //처리할 수 있는 작업이 없으면 다음 작업을 이어받을 수 있도록 시간 조정
                curTime = jobs[jobIdx][0];
            }
        }

        return total / jobNums;
    }
}
