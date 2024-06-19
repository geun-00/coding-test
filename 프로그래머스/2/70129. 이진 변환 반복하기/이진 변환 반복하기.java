class Solution {
    public int[] solution(String s) {
        int trans = 0;
        int zero = 0;

        while (!s.equals("1")) {
            int prev = s.length();

            s = s.replaceAll("0", "");

            zero += prev - s.length();

            s = Integer.toBinaryString(s.length());
            trans++;
        }

        return new int[]{trans, zero};
    }
}