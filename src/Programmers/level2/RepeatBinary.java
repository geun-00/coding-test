package Programmers.level2;


import java.util.Arrays;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/70129">프로그래머스 Lv.2 : 이진 변환 반복하기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%9D%B4%EC%A7%84-%EB%B3%80%ED%99%98-%EB%B0%98%EB%B3%B5%ED%95%98%EA%B8%B0">velog</a>
 * @since 2024-06-19
 */
public class RepeatBinary {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("110010101001")));
        System.out.println(Arrays.toString(solution("01110")));
        System.out.println(Arrays.toString(solution("1111111")));
    }

    private static int[] solution(String s) {

        int trans = 0; //변환 반복 횟수
        int zero = 0; //제거한 0의 개수

        while (!s.equals("1")) {
            int prev = s.length(); //0을 제거하기 전 길이

            s = s.replaceAll("0", ""); //0 제거

            int before = s.length(); //0을 제거한 후 길이

            zero += prev - before;

            s = Integer.toBinaryString(before); //0 제거 후 길이를 이진 변환

            trans++;
        }

        return new int[]{trans, zero};
    }
}
