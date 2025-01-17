import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        String num = st.nextToken();

        st = new StringTokenizer(br.readLine());

        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());

        long size = (long) Math.pow(2, d - 1);
        long[] pos = findPos(0, 0, size, num, 0);

        long nx = pos[0] - y;
        long ny = pos[1] + x;

        if (nx < 0 || ny < 0 || nx >= size * 2L || ny >= size * 2L) {
            System.out.println(-1);
            return;
        }

        System.out.println(findAns(nx, ny, size, d));
    }

    private static long[] findPos(long x, long y, long size, String num, int index) {
        if (index == num.length()) {
            return new long[]{x, y};
        }

        char c = num.charAt(index);
        long half = size / 2;

        switch (c) {
            case '1': return findPos(x, y + size, half, num, index + 1);
            case '2': return findPos(x, y, half, num, index + 1);
            case '3': return findPos(x + size, y, half, num, index + 1);
            case '4': return findPos(x + size, y + size, half, num, index + 1);
        }

        return new long[]{};
    }

    private static String findAns(long x, long y, long size, int d) {
        if (d == 0) {
            return "";
        }

        long half = size / 2;

        if (x < size && y >= size) return "1" + findAns(x, y - size, half, d - 1);
        else if (x < size && y < size) return "2" + findAns(x, y, half, d - 1);
        else if (x >= size && y < size) return "3" + findAns(x - size, y, half, d - 1);
        else return "4" + findAns(x - size, y - size, half, d - 1);
    }
}