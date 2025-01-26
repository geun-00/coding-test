class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] ans = new String[n];
        String format = "%" + n + "s";

        for (int i = 0; i < n; i++) {
            int temp = arr1[i] | arr2[i];
            String binary = String.format(format, Integer.toBinaryString(temp))
                                  .replace(" ", "0");
            ans[i] = binary.replace("1", "#")
                           .replace("0", " ");
        }


        return ans;
    }
}