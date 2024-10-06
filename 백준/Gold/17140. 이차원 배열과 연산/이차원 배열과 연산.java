import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static class Info implements Comparable<Info> {

        int num, count;

        public Info(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Info o) {
            if (this.count == o.count) {
                return this.num - o.num;
            }
            return this.count - o.count;
        }
    }

    static int[][] A = new int[101][101];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (A[r][c] != k) {

            //100초가 지나도 해결할 수 없을 때
            if (time > 100) {
                System.out.println(-1);
                return;
            }

            //행의 개수 >= 열의 개수인 경우 R 연산, 아닌 경우 C 연산
            if (countRows() >= countCols()) {
                oper_R();
            } else {
                oper_C();
            }

            time++;
        }

        System.out.println(time);
    }

    private static int countCols() {

        int cols = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (A[j][i] != 0) {
                    cols++;
                    break;
                }
            }
        }
        return cols;
    }

    private static int countRows() {

        int rows = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (A[i][j] != 0) {
                    rows++;
                    break;
                }
            }
        }
        return rows;
    }

    private static void oper_R() {

        for (int i = 1; i <= 100; i++) {

            HashMap<Integer, Integer> map = new HashMap<>();

            for (int j = 1; j <= 100; j++) {
                if (A[i][j] != 0) {
                    map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
                }
            }

            Info[] infos = new Info[map.keySet().size()];
            int idx = 0;
            for (int key : map.keySet()) {
                infos[idx++] = new Info(key, map.get(key));
            }
            Arrays.sort(infos);

//            ArrayList<Info> list = new ArrayList<>();
//            for (int key : map.keySet()) {
//                list.add(new Info(key, map.get(key)));
//            }
//            list.sort(null);

            idx = 1;

            for (Info info : infos) {
                if (idx > 100) {
                    break;
                }
                A[i][idx++] = info.num;

                if (idx > 100) {
                    break;
                }
                A[i][idx++] = info.count;
            }

            while (idx <= 100) {
                A[i][idx++] = 0;
            }
        }
    }

    private static void oper_C() {

        for (int i = 1; i <= 100; i++) {

            HashMap<Integer, Integer> map = new HashMap<>();

            for (int j = 1; j <= 100; j++) {
                if (A[j][i] != 0) {
                    map.put(A[j][i], map.getOrDefault(A[j][i], 0) + 1);
                }
            }

            Info[] infos = new Info[map.keySet().size()];
            int idx = 0;
            for (int key : map.keySet()) {
                infos[idx++] = new Info(key, map.get(key));
            }
            Arrays.sort(infos);

//            ArrayList<Info> list = new ArrayList<>();
//            for (int key : map.keySet()) {
//                list.add(new Info(key, map.get(key)));
//            }
//            list.sort(null);

            idx = 1;

            for (Info info : infos) {
                if (idx > 100) {
                    break;
                }
                A[idx++][i] = info.num;

                if (idx > 100) {
                    break;
                }
                A[idx++][i] = info.count;
            }

            while (idx <= 100) {
                A[idx++][i] = 0;
            }
        }
    }
}
