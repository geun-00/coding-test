import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        String W = br.readLine();
        String S = br.readLine();

        int[] arr = new int[52];
        for (char c : W.toCharArray()) {
            int index = getIndex(c);
            arr[index]++;
        }

        int[] window = new int[52];
        for (int i = 0; i < w; i++) {
            char c = S.charAt(i);
            int index = getIndex(c);
            window[index]++;
        }

        int ans = 0;
        if (equals(arr, window)) {
            ans++;
        }

        for (int i = w; i < s; i++) {
            char prev = S.charAt(i - w);
            char current = S.charAt(i);

            window[getIndex(prev)]--;
            window[getIndex(current)]++;

            if (equals(arr, window)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static boolean equals(int[] arr, int[] window) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != window[i]) {
                return false;
            }
        }
        return true;
    }

    public static int getIndex(char c) {
        return Character.isUpperCase(c) ? c - 'A' + 26 : c - 'a';
    }
}