import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int isRemember = (1 << 26) - 1;

        int[] wordBits = new int[n];
        for (int i = 0; i < n; i++) {
            wordBits[i] = getBit(br.readLine());
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int o = Integer.parseInt(st.nextToken());
            int x = st.nextToken().charAt(0) - 'a';

            if (o == 1) isRemember &= ~(1 << x);
            else isRemember |= (1 << x);

            int count = 0;
            for (int bit : wordBits) {
                if ((bit & isRemember) == bit) {
                    count++;
                }
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }

    private static int getBit(String word) {
        int bit = 0;
        for (char c : word.toCharArray()) {
            bit |= (1 << (c - 'a'));
        }
        return bit;
    }
}