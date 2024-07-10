package Programmers.level2;

import java.util.Arrays;
import java.util.HashSet;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/64065">프로그래머스 - Lv.2 : 튜플</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%8A%9C%ED%94%8C">velog</a>
 * @since 2024-07-10
 */
public class Tuple {
    public static void main(String[] args) {

        String s1 = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        String s2 = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
        String s3 = "{{20,111},{111}}";
        String s4 = "{{123}}";
        String s5 = "{{4,2,3},{3},{2,3,4,1},{2,3}}";

        System.out.println(Arrays.toString(solution(s1)));
        System.out.println(Arrays.toString(solution(s2)));
        System.out.println(Arrays.toString(solution(s3)));
        System.out.println(Arrays.toString(solution(s4)));
        System.out.println(Arrays.toString(solution(s5)));
    }

    private static int[] solution(String s) {

        s = s.substring(2, s.length() - 2); //처음과 끝에 { } 제거

        String[] arr = s.split("},\\{");

        Arrays.sort(arr, (o1, o2) -> o1.length() - o2.length());

        HashSet<Integer> set = new HashSet<>();
        int[] result = new int[arr.length];

        int index = 0;
        for (String string : arr) {
            for (String str : string.split(",")) {
                int num = Integer.parseInt(str);
                if (set.add(num)) {
                    result[index++] = num;
                    break;
                }
            }
        }

        return result;
    }
}
