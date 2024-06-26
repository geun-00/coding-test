class Solution {
    public int[] solution(int brown, int yellow) {
        int half = (brown + 4) / 2;

        for (int height = 3; height < half; height++) {
            int width = half - height;

            if (width * height == brown + yellow) {
                return new int[]{width, height};
            }
        }

        return null;
    }
}