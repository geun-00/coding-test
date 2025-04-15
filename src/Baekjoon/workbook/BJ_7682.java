package Baekjoon.workbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/7682">백준 7682번 - 틱택토</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-7682%EB%B2%88-%ED%8B%B1%ED%83%9D%ED%86%A0">velog</a>
 * @since 2025-03-25
 */
public class BJ_7682 {

    static int[][] bingo = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8},
        {0, 3, 6},
        {1, 4, 7},
        {2, 5, 8},
        {0, 4, 8},
        {2, 4, 6},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        String valid = "valid";
        String invalid = "invalid";

        String input;
        while (!(input = br.readLine()).equals("end")) {
            char[] arr = input.toCharArray();

            // 1
            int x = 0, o = 0, empty = 0;
            for (char c : arr) {
                if (c == 'X') x++;
                else if (c == 'O') o++;
                else if (c == '.') empty++;
            }

            // 2
            if (o > x || x - o > 1) {
                sb.append(invalid);
                continue;
            }

            // 3
            boolean XBingo = isBingo(arr, 'X');
            boolean OBingo = isBingo(arr, 'O');

            if (XBingo && OBingo) {
                sb.append(invalid);
            }
            else if (XBingo) {
                if (x - o == 1) sb.append(valid);
                else sb.append(invalid);
            }
            else if (OBingo) {
                if (x == o) sb.append(valid);
                else sb.append(invalid);
            }
            else {
                if (empty == 0) sb.append(valid);
                else sb.append(invalid);
            }

            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static boolean isBingo(char[] arr, char target) {
        for (int[] b : bingo) {
            if (arr[b[0]] == target && arr[b[1]] == target && arr[b[2]] == target) {
                return true;
            }
        }
        return false;
    }
}
